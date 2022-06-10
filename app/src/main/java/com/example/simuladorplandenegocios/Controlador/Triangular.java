package com.example.simuladorplandenegocios.Controlador;



import android.graphics.Color;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.simuladorplandenegocios.Modelo.Anio;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Triangular {

    private String nombrePlanNegocio;
    private FirebaseFirestore db;
    private String atract;
    private double viable;

    public double getViable() {
        return viable;
    }

    public String getAtract() {
        return atract;
    }

    public Triangular(String nombrePlanNegocio){
        this.nombrePlanNegocio = nombrePlanNegocio;
        this.db = FirebaseFirestore.getInstance();
        //this.inflacion = (float)((0.000049f + 0.0147f + 0.0151f + 0.0271f + 0.04f)/5);
        //this.fca = new float[8];
    }

    public void ejecutarSimulacion(TextView atrac, TextView via, ProgressBar progressBar){


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
                        progressBar.setProgress(10);

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
                                progressBar.setProgress(30);
                            }
                        } else {
                            //Log.d(TAG, "Error getting documents: ", task.getException());
                        }

                        double TREMA = (double) (interesCredito + inflacion + interesCredito*inflacion);
                        long n = 10000;//CORRIDAS
                        double viabilidadResultado = 0l;
                        long viabilidad = 0l;
                        long atractividad = 0l;
                        //REGRESION
                        HashMap<String,Object> aniosCorridas = new HashMap<>();

                        for(long i=1 ;i<=n; i++){
                            double VAN = 0d;
                            double TIR = 0d;
                            String viable="";
                            String atractivo="";
                            long plazoCuota = plazoCredito;
                            double[] fcAnual = new double[8];
                            fcAnual[0]=-montoSolicitadoCredito;//INVERSION INICIAL
                            double saldoInicial = montoSolicitadoCredito + aportePropio;//EN EL MES 1
                            double saldoVariable = saldoInicial;
                            double[] variables = new double[2];//0->SALDO  1->FLUJO
                            double cuotaVariable = cuota;
                            long contadorMeses = 0l;
                            int contadorAnios = 1;
                            double flujoMensual = 0d;
                            //REGRESION
                            ArrayList<Anio> anios = new ArrayList<>();
                            Anio anio = new Anio();

                            //CALCULO FLUJO CAJA MES
                            for(int j=1; j<=84 ;j++){
                                if(plazoCuota > 0){
                                    variables = flujoCajaMes(productos,cuotaVariable,saldoVariable,costosFijos,montoSolicitadoCredito,j);
                                    plazoCuota--;
                                    saldoVariable = variables [0];
                                    flujoMensual = flujoMensual + variables [1];
                                    contadorMeses++;

                                    if (contadorMeses == 12){
                                        fcAnual[contadorAnios] = flujoMensual;
                                        contadorAnios++;
                                        anio.asignarGananciaMensual(contadorMeses,flujoMensual);//REGRESION
                                        anios.add(anio);//REGRESION
                                        anio.vaciarAnio();//REGRESION
                                        contadorMeses = 0L;
                                    }else {
                                        anio.asignarGananciaMensual(contadorMeses,flujoMensual);//REGRESION
                                    }

                                }else {
                                    cuotaVariable = 0d;
                                    variables = flujoCajaMes(productos,cuotaVariable,saldoVariable,costosFijos,montoSolicitadoCredito,j);
                                    saldoVariable = variables [0];
                                    flujoMensual = flujoMensual + variables [1];
                                    contadorMeses++;

                                    if (contadorMeses == 12){
                                        fcAnual[contadorAnios] = flujoMensual;
                                        contadorAnios++;
                                        anio.asignarGananciaMensual(contadorMeses,flujoMensual);//REGRESION
                                        anios.add(anio);//REGRESION
                                        anio.vaciarAnio();//REGRESION
                                        contadorMeses = 0L;
                                    }else {
                                        anio.asignarGananciaMensual(contadorMeses,flujoMensual);//REGRESION
                                    }

                                }
                            }


                            //REGRESION
                            aniosCorridas.put(""+i,anios);

                            for(int j=0 ;j<=7;j++){
                                if(j==0){
                                    VAN =  fcAnual[j];
                                }else{
                                    VAN = VAN + (double) (fcAnual[j]/Math.pow(1+interesCredito, j));
                                }
                            }
                            progressBar.setProgress(60);

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
                            progressBar.setProgress(70);
                            System.out.println("ES EL TIR"+TIR);
                            System.out.println(i);
                        }
                        //REGRESION
                        RegresionLineal regresionLineal = new RegresionLineal(n,aniosCorridas,nombrePlanNegocio);
                        regresionLineal.ejecutarRegresion();

                        String atractivoFinal = "";
                        double porcentajeAtractividad = (double) atractividad/n;
                        if(porcentajeAtractividad>0.50){
                            atractivoFinal = "ES ATRACTIVO";
                            atrac.setText(atractivoFinal);
                            atrac.setTextColor(Color.GREEN);
                        }else{
                            atractivoFinal = "NO ES ATRACTIVO";
                            atrac.setText(atractivoFinal);
                            atrac.setTextColor(Color.RED);
                        }
                        progressBar.setProgress(80);
                        System.out.println(atractividad);
                        System.out.println(porcentajeAtractividad);
                        System.out.println(viabilidad);
                        viabilidadResultado = 100*((double) viabilidad/n);

                        System.out.println(viabilidadResultado);
                        System.out.println(atractivoFinal);

                        via.setText(""+viabilidadResultado);
                        if(viabilidadResultado<50.0){
                            via.setTextColor(Color.RED);
                        }else{
                            via.setTextColor(Color.GREEN);
                        }
                        progressBar.setProgress(90);
                        guardarResultados(viabilidadResultado,atractivoFinal,progressBar);
                    }
                });

    }

    public void guardarResultados(double viabilidadResultado,String atractivoFinal,ProgressBar progressBar){
        viable = viabilidadResultado;
        atract = atractivoFinal;
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
        progressBar.setVisibility(View.INVISIBLE);
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

    public double[] flujoCajaMes(HashMap productos,double cuotaVariable,double saldoVariable,double costosFijos,double inversionInicial,int j){
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

        if(!p1.get("Nombre Producto").equals("NINGUNO")){
            if(r1 < (((double)p1.get("Precio Venta Moderado")-(double)p1.get("Precio Venta Pesimista"))/((double)p1.get("Precio Venta Optimista")-(double)p1.get("Precio Venta Pesimista")))){
                ventas[0] = (double)p1.get("Precio Venta Pesimista") + ((double)p1.get("Precio Venta Moderado")-(double)p1.get("Precio Venta Pesimista"))*(double) Math.pow(r2, 0.5d);
            }else{
                ventas[0] = (double)p1.get("Precio Venta Optimista") - ((double)p1.get("Precio Venta Optimista")-(double)p1.get("Precio Venta Moderado"))*(double)Math.pow(1-r2, 0.5d);
            }
        }else {
            ventas[0] = 0d;
        }
        if(!p2.get("Nombre Producto").equals("NINGUNO")){
            if(r1 < (((double)p2.get("Precio Venta Moderado")-(double)p2.get("Precio Venta Pesimista"))/((double)p2.get("Precio Venta Optimista")-(double)p2.get("Precio Venta Pesimista")))){
                ventas[1] = (double)p2.get("Precio Venta Pesimista") + ((double)p2.get("Precio Venta Moderado")-(double)p2.get("Precio Venta Pesimista"))*(double) Math.pow(r2, 0.5d);
            }else{
                ventas[1] = (double)p2.get("Precio Venta Optimista") - ((double)p2.get("Precio Venta Optimista")-(double)p2.get("Precio Venta Moderado"))*(double)Math.pow(1-r2, 0.5d);
            }
        }else {
            ventas[1] = 0d;
        }
        if(!p3.get("Nombre Producto").equals("NINGUNO")){
            if(r1 < (((double)p3.get("Precio Venta Moderado")-(double)p3.get("Precio Venta Pesimista"))/((double)p3.get("Precio Venta Optimista")-(double)p3.get("Precio Venta Pesimista")))){
                ventas[2] = (double)p3.get("Precio Venta Pesimista") + ((double)p3.get("Precio Venta Moderado")-(double)p3.get("Precio Venta Pesimista"))*(double) Math.pow(r2, 0.5d);
            }else{
                ventas[2] = (double)p3.get("Precio Venta Optimista") - ((double)p3.get("Precio Venta Optimista")-(double)p3.get("Precio Venta Moderado"))*(double)Math.pow(1-r2, 0.5d);
            }
        }else {
            ventas[2] = 0d;
        }
        if(!p4.get("Nombre Producto").equals("NINGUNO")){
            if(r1 < (((double)p4.get("Precio Venta Moderado")-(double)p4.get("Precio Venta Pesimista"))/((double)p4.get("Precio Venta Optimista")-(double)p4.get("Precio Venta Pesimista")))){
                ventas[3] = (double)p4.get("Precio Venta Pesimista") + ((double)p4.get("Precio Venta Moderado")-(double)p4.get("Precio Venta Pesimista"))*(double) Math.pow(r2, 0.5d);
            }else{
                ventas[3] = (double)p4.get("Precio Venta Optimista") - ((double)p4.get("Precio Venta Optimista")-(double)p4.get("Precio Venta Moderado"))*(double)Math.pow(1-r2, 0.5d);
            }
        }else {
            ventas[3] = 0d;
        }

        ///MARGENES BRUTOS DE PRODUCTOS
        double[] costos = new double[4];
        if(!p1.get("Nombre Producto").equals("NINGUNO")){
            costos[0] = (double) p1.get("Costo Produccion Unidad");
        }else {
            costos[0] = 0d;
        }
        if(!p2.get("Nombre Producto").equals("NINGUNO")){
            costos[1] = (double) p2.get("Costo Produccion Unidad");
        }else {
            costos[1] = 0d;
        }
        if(!p3.get("Nombre Producto").equals("NINGUNO")){
            costos[2] = (double) p3.get("Costo Produccion Unidad");
        }else {
            costos[2] = 0d;
        }
        if(!p4.get("Nombre Producto").equals("NINGUNO")){
            costos[3] = (double) p4.get("Costo Produccion Unidad");
        }else {
            costos[3] = 0d;
        }

        double[] margenesBrutosVentas = new double[4];
        for (int i= 0; i<ventas.length ;i++){
            if(ventas[i] != 0){
                margenesBrutosVentas[i] = (ventas[i] - costos[i])/ventas[i];
            }else {
                margenesBrutosVentas[i] = 0;
            }

        }

        long[] cantidadesVendidas = new long[4];
        if(!p1.get("Nombre Producto").equals("NINGUNO")){
            cantidadesVendidas[0] = (long) p1.get("Cantidad Vendida");
        }else {
            cantidadesVendidas[0] = 0l;
        }
        if(!p2.get("Nombre Producto").equals("NINGUNO")){
            cantidadesVendidas[1] = (long) p2.get("Cantidad Vendida");
        }else {
            cantidadesVendidas[1] = 0l;
        }
        if(!p3.get("Nombre Producto").equals("NINGUNO")){
            cantidadesVendidas[2] = (long) p3.get("Cantidad Vendida");
        }else {
            cantidadesVendidas[2] = 0l;
        }
        if(!p4.get("Nombre Producto").equals("NINGUNO")){
            cantidadesVendidas[3] = (long) p4.get("Cantidad Vendida");
        }else {
            cantidadesVendidas[3] = 0l;
        }


        double[] totalPeriodos = new double[4];
        for(int i=0; i<ventas.length;i++){
            totalPeriodos[i] = ventas[i] *cantidadesVendidas[i];
        }
        double totalVentas = 0d;
        for(int i=0; i<totalPeriodos.length;i++){
            totalVentas = totalVentas + totalPeriodos[i];
        }

        double costoProduccionVentas = 0d;
        for(int i=0; i<margenesBrutosVentas.length;i++){
            double dif = 1.0d - margenesBrutosVentas[i];
            if(!(totalPeriodos[i] != 0d)){
                costoProduccionVentas = costoProduccionVentas + 0d;
            }else {
                costoProduccionVentas = costoProduccionVentas + (totalPeriodos[i]*dif);
            }

        }

        double margenBrutoProduccionVentas = (double) (totalVentas - costoProduccionVentas)/totalVentas;

        double ingresos = 0d;
        double costoProduccion = 0d;
        Random mes = new Random();
        int hayVenta = mes.nextInt(2);
        if( hayVenta ==1){
            ingresos = totalVentas;
            costoProduccion = (double) (ingresos*(double) (1.0d-margenBrutoProduccionVentas));
        }else{
            ingresos = 0f;
            costoProduccion = 0f;
        }

        utilidadBruta = (double) (ingresos - costoProduccion);
        utilidadNeta = (double) (utilidadBruta - costosFijos);
        if (j == 1){
            flCaja = (double) (utilidadNeta - cuotaVariable - inversionInicial + saldoVariable);
        }else {
            flCaja = (double) (utilidadNeta - cuotaVariable + saldoVariable);
        }

        variables[0] = flCaja;
        variables[1] = flCaja;

        return variables;
    }
    /*public void prueba(double interes,long plazo,String mensual){
        System.out.println(mensual);
    }*/
}
