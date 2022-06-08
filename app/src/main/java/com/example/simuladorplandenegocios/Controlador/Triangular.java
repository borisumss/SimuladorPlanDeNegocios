package com.example.simuladorplandenegocios.Controlador;



import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Triangular {

    private String nombrePlanNegocio;
    private FirebaseFirestore db;



    public Triangular(String nombrePlanNegocio){
        this.nombrePlanNegocio = nombrePlanNegocio;
        this.db = FirebaseFirestore.getInstance();
        //this.inflacion = (float)((0.000049f + 0.0147f + 0.0151f + 0.0271f + 0.04f)/5);
        //this.fca = new float[8];
    }

    public void ejecutarSimulacion(){


        this.db.collection(""+this.nombrePlanNegocio)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        double interesCredito = 0d;
                        long plazoCredito = 0l;
                        double montoSolicitadoCredito = 0d;
                        double cuota = 0d;
                        double inflacion = (double) ((0.000049d + 0.0147d + 0.0151d + 0.0271d + 0.04d)/5);
                        double aportePropio = 0d;
                        double costosFijos = 0d;
                        HashMap<String,Object> productos = new HashMap<>();


                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d(TAG, document.getId() + " => " + document.getData());
                                String nameDoc = document.getId();

                                if(nameDoc.equals("Deudor y Credito")){
                                    HashMap credito = (HashMap) document.get("Credito");
                                    interesCredito = ((double) credito.get("Interes"))/100;
                                    plazoCredito = (long) credito.get("Plazo");
                                    montoSolicitadoCredito = (double) credito.get("Monto Solicitado");
                                    cuota = (double) credito.get("Cuota");
                                }else if(nameDoc.equals("Presupuesto Resumen")){
                                    HashMap presupuestoAP = (HashMap) document.get("Aporte Propio");
                                    aportePropio = (double) presupuestoAP.get("Efectivo");
                                }else if(nameDoc.equals("Costos Productos")){
                                    productos.put("Producto 1",(HashMap)document.get("Producto 1"));
                                    productos.put("Producto 2",(HashMap)document.get("Producto 2"));
                                    productos.put("Producto 3",(HashMap)document.get("Producto 3"));
                                    productos.put("Producto 4",(HashMap)document.get("Producto 4"));
                                    //System.out.println(((HashMap)productos.get("Producto 1")).get("Nombre Producto"));
                                }else if(nameDoc.equals("Gastos Fijos")){
                                    costosFijos = (double) document.get("Total Gastos");
                                }

                            }
                        } else {
                            //Log.d(TAG, "Error getting documents: ", task.getException());
                        }

                        double TREMA = (double) (interesCredito + inflacion + interesCredito*inflacion);
                        long n = 10000;//CORRIDAS
                        double[] fcAnual = new double[8];
                        double viabilidadResultado = 0l;
                        long viabilidad = 0l;
                        long atractividad = 0l;

                        for(long i=1 ;i<=n; i++){
                            double VAN = 0d;
                            double TIR = 0d;
                            String viable="";
                            String atractivo="";
                            long plazoCuota = plazoCredito;
                            fcAnual[0]=montoSolicitadoCredito;//INVERSION INICIAL
                            double saldoInicial = montoSolicitadoCredito + aportePropio;//EN EL MES 1
                            double saldoVariable = saldoInicial;
                            double[] variables = new double[2];//0->SALDO  1->FLUJO
                            double cuotaVariable = cuota;
                            long contadorMeses = 0l;
                            int contadorAnios = 1;
                            double flujoMensual = 0d;

                            //CALCULO FLUJO CAJA MES
                            for(int j=1; j<=84 ;j++){
                                if(plazoCuota > 0){
                                    variables = flujoCajaMes(productos,cuotaVariable,saldoVariable,costosFijos,montoSolicitadoCredito);
                                    plazoCuota--;
                                    saldoVariable = variables [0];
                                    flujoMensual = flujoMensual + variables [1];
                                    contadorMeses++;
                                    if (contadorMeses == 12){
                                        fcAnual[contadorAnios] = flujoMensual;
                                        contadorAnios++;
                                        contadorMeses = 0l;
                                    }
                                }else {
                                    cuotaVariable = 0d;
                                    variables = flujoCajaMes(productos,cuotaVariable,saldoVariable,costosFijos,montoSolicitadoCredito);
                                    saldoVariable = variables [0];
                                    flujoMensual = flujoMensual + variables [1];
                                    contadorMeses++;
                                    if (contadorMeses == 12){
                                        fcAnual[contadorAnios] = flujoMensual;
                                        contadorAnios++;
                                        contadorMeses = 0l;
                                    }
                                }
                            }

                            for(int j=0 ;j<=7;j++){
                                if(j==0){
                                    VAN = fcAnual[j];
                                }else{
                                    VAN = VAN + (double) (fcAnual[j]/Math.pow(1+interesCredito, j));
                                }
                            }

                            if(VAN > 0.0d){
                                TIR = (double) estimarTIR(fcAnual,interesCredito);
                                if(TIR > TREMA){
                                    viabilidad++;
                                    viable = "SI";
                                }else{
                                    viable = "NO";
                                }

                                if(TIR > interesCredito){
                                    atractividad++;
                                    atractivo = "SI";
                                }else{
                                    atractivo = "NO";
                                }

                            }else{
                                TIR = 0f;
                                viable = "NO";
                                atractivo="NO";
                            }
                        }
                        String atractivoFinal = "";
                        double porcentajeAtractividad = (double) atractividad/n;
                        if(porcentajeAtractividad>0.50){
                            atractivoFinal = "ES ATRACTIVO";
                        }else{
                            atractivoFinal = "NO ES ATRACTIVO";
                        }
                        viabilidadResultado = 100*((double) viabilidad/n);

                        System.out.println(viabilidadResultado);
                        System.out.println(atractivoFinal);

                        guardarResultados(viabilidadResultado,atractivoFinal);

                    }
                });

        /*docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        credito = (HashMap) document.get("Credito");



                        //Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        //Log.d(TAG, "No such document");
                    }
                } else {
                    //Log.d(TAG, "get failed with ", task.getException());
                }
                /*System.out.println(credito.get("Interes"));
                double p = (double)credito.get("Interes");
                long plazo = (long) credito.get("Plazo");
                String mensual = (String) credito.get("Forma de Pago");
                prueba(p,plazo,mensual);*/
            //}
        //});

        //float interes = this.db.collection(""+this.nombrePlanNegocio).document();
        //float TREMA =
        //System.out.println(credito.get("Interes"));


    }

    public void guardarResultados(double viabilidadResultado,String atractivoFinal){
        Map<String, Object> resultadosSimulacion = new HashMap<>();
        resultadosSimulacion.put("Viabilidad",viabilidadResultado);
        resultadosSimulacion.put("Atractivo",atractivoFinal);
        this.db.collection(""+this.nombrePlanNegocio).document("Resultados")
                .set(resultadosSimulacion)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Log.w(TAG, "Error writing document", e);
                    }
                });
    }

    public double estimarTIR(double fcAnual[],double interes){
        double tasa1 = interes;
        double tasa2 = 0d;
        boolean bb = false;
        double VAN1 = 0d;
        double VAN2 = 0d;
        double TIR = 0d;

        while(bb!=true){
            VAN1 = fcAnual[0];
            for(int j=1;j<=7;j++){
                VAN1=VAN1 + (double) (fcAnual[j]/Math.pow(1+tasa1, j));
            }
            if(VAN1 > 0d){
                VAN2 = VAN1;
                tasa2 =tasa1;
                tasa1 = tasa1 + 0.01d;
            }else{
                TIR = tasa2-VAN2*((tasa2-tasa1)/(VAN2-VAN1));
                bb = true;
            }
        }
        return TIR;
    }

    public double[] flujoCajaMes(HashMap productos,double cuotaVariable,double saldoVariable,double costosFijos,double inversionInicial){
        double[] variables = new double[2];
        HashMap p1 = (HashMap) productos.get("Producto 1");
        HashMap p2 = (HashMap) productos.get("Producto 2");
        HashMap p3 = (HashMap) productos.get("Producto 3");
        HashMap p4 = (HashMap) productos.get("Producto 4");

        double flCaja = 0d;
        double utilidadNeta = 0d;
        double utilidadBruta = 0d;
        double[] ventas = new double[4];
        double r1 = (double)Math.random();
        double r2 = (double)Math.random();

        if(r1 < (((double)p1.get("Precio Venta Moderado")-(double)p1.get("Precio Venta Pesimista"))/((double)p1.get("Precio Venta Optimista")-(double)p1.get("Precio Venta Pesimista")))){
            ventas[0] = (double)p1.get("Precio Venta Pesimista") + ((double)p1.get("Precio Venta Moderado")-(double)p1.get("Precio Venta Pesimista"))*(double) Math.pow(r2, 0.5d);
        }else{
            ventas[0] = (double)p1.get("Precio Venta Optimista") - ((double)p1.get("Precio Venta Optimista")-(double)p1.get("Precio Venta Moderado"))*(double)Math.pow(1-r2, 0.5d);
        }

        if(r1 < (((double)p2.get("Precio Venta Moderado")-(double)p2.get("Precio Venta Pesimista"))/((double)p2.get("Precio Venta Optimista")-(double)p2.get("Precio Venta Pesimista")))){
            ventas[1] = (double)p2.get("Precio Venta Pesimista") + ((double)p2.get("Precio Venta Moderado")-(double)p2.get("Precio Venta Pesimista"))*(double) Math.pow(r2, 0.5d);
        }else{
            ventas[1] = (double)p2.get("Precio Venta Optimista") - ((double)p2.get("Precio Venta Optimista")-(double)p2.get("Precio Venta Moderado"))*(double)Math.pow(1-r2, 0.5d);
        }

        if(r1 < (((double)p3.get("Precio Venta Moderado")-(double)p3.get("Precio Venta Pesimista"))/((double)p3.get("Precio Venta Optimista")-(double)p3.get("Precio Venta Pesimista")))){
            ventas[2] = (double)p3.get("Precio Venta Pesimista") + ((double)p3.get("Precio Venta Moderado")-(double)p3.get("Precio Venta Pesimista"))*(double) Math.pow(r2, 0.5d);
        }else{
            ventas[2] = (double)p3.get("Precio Venta Optimista") - ((double)p3.get("Precio Venta Optimista")-(double)p3.get("Precio Venta Moderado"))*(double)Math.pow(1-r2, 0.5d);
        }

        if(r1 < (((double)p4.get("Precio Venta Moderado")-(double)p4.get("Precio Venta Pesimista"))/((double)p4.get("Precio Venta Optimista")-(double)p4.get("Precio Venta Pesimista")))){
            ventas[3] = (double)p4.get("Precio Venta Pesimista") + ((double)p4.get("Precio Venta Moderado")-(double)p4.get("Precio Venta Pesimista"))*(double) Math.pow(r2, 0.5d);
        }else{
            ventas[3] = (double)p4.get("Precio Venta Optimista") - ((double)p4.get("Precio Venta Optimista")-(double)p4.get("Precio Venta Moderado"))*(double)Math.pow(1-r2, 0.5d);
        }

        ///MARGENES BRUTOS DE PRODUCTOS
        double[] costos = new double[4];
        costos[0] = (double) p1.get("Costo Produccion Unidad");
        costos[1] = (double) p2.get("Costo Produccion Unidad");
        costos[2] = (double) p3.get("Costo Produccion Unidad");
        costos[3] = (double) p4.get("Costo Produccion Unidad");

        double[] margenesBrutosVentas = new double[4];
        for (int i= 0; i<4 ;i++){
            margenesBrutosVentas[i] = (double) ((ventas[i] - costos[i])/ventas[i]);
        }

        double[] cantidadesVendidas = new double[4];
        cantidadesVendidas[0] = (long) p1.get("Cantidad Vendida");
        cantidadesVendidas[1] = (long) p2.get("Cantidad Vendida");
        cantidadesVendidas[2] = (long) p3.get("Cantidad Vendida");
        cantidadesVendidas[3] = (long) p4.get("Cantidad Vendida");

        double[] totalPeriodos = new double[4];
        for(int i=0; i<4;i++){
            totalPeriodos[i] = (double) ventas[i]*cantidadesVendidas[i];
        }
        double totalVentas = 0d;
        for(int i=0; i<4;i++){
            totalVentas = totalVentas + totalPeriodos[i];
        }

        double costoProduccionVentas = 0d;
        for(int i=0; i<4;i++){
            costoProduccionVentas = costoProduccionVentas + totalPeriodos[i]*(1-margenesBrutosVentas[i]);
        }

        double margenBrutoProduccionVentas = (double) (totalVentas - costoProduccionVentas)/totalVentas;

        double ingresos = 0f;
        double costoProduccion = 0f;
        Random mes = new Random();
        if(mes.nextInt(2) ==1){
            ingresos = totalVentas;
            costoProduccion = (float)(ingresos*(1-margenBrutoProduccionVentas));
        }else{
            ingresos = 0f;
            costoProduccion = 0f;
        }

        utilidadBruta = (double) (ingresos - costoProduccion);
        utilidadNeta = (double) (utilidadBruta - costosFijos);
        flCaja = (float) (utilidadNeta - cuotaVariable - inversionInicial + saldoVariable);

        variables[0] = flCaja;
        variables[1] = flCaja;

        return variables;
    }
    /*public void prueba(double interes,long plazo,String mensual){
        System.out.println(mensual);
    }*/
}
