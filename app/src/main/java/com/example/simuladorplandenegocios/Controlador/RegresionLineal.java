package com.example.simuladorplandenegocios.Controlador;

import androidx.annotation.NonNull;

import com.example.simuladorplandenegocios.Modelo.Anio;
import com.example.simuladorplandenegocios.Modelo.Ganancia;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RegresionLineal {
    private long n;
    private HashMap<String,Object> aniosCorridas;
    private String nombrePlanNegocio;
    private double[] pendientes;
    private double interesCredito;
    private double credito;
    //private ArrayList<> pendientes;

    public RegresionLineal(long n,HashMap aniosCorridas,String nombrePlanNegocio,double interesCredito,double credito){
        this.n = n;
        this.aniosCorridas = aniosCorridas;
        this.nombrePlanNegocio = nombrePlanNegocio;
        this.pendientes = new double[7];
        this.interesCredito = interesCredito;
        this.credito = credito;
    }

    public void ejecutarRegresion(){
        ArrayList<Ganancia> ganaciasRegresion = new ArrayList<>();
        for (int i=1 ; i<=this.n ;i++){
            ArrayList<Anio> anios = (ArrayList<Anio>) this.aniosCorridas.get(""+i);
            for (int j=0 ; j<anios.size() ; j++){
                Anio anio = anios.get(j);
                HashMap<String,Object> ganaciasMensuales = anio.getMesesGanancia();
                Ganancia g1 = new Ganancia(j+1,ganaciasMensuales);
                ganaciasRegresion.add(g1);
            }
        }

        ///CALCULAR REGRESION
        double sumatoriaXY[] = new double[7];
        double sumatoriaXCuadrada[] = new double[7];
        double xMedia[] = new double[7];
        double yMedia[] = new double[7];
        long xContador[] = new long[7];
        long yContador[] = new long[7];
        long contadorN[] = new long[7];

        for (int i=0; i<ganaciasRegresion.size();i++){
            Ganancia g = ganaciasRegresion.get(i);
            if (g.getAnio() == 1){
                //calcular
                sumatoriaXY[0] = sumatoriaXY[0] + calcularSumatoriaXY(g.getGananciaAnio());
                sumatoriaXCuadrada[0] = sumatoriaXCuadrada[0] + calcularSumatoriaXCuadrada();
                xMedia[0] = xMedia[0] + calcularSumatoriaX();
                xContador[0] = xContador[0] + 1L;
                yMedia[0] = yMedia[0] + calcularSumatoriaY(g.getGananciaAnio());
                yContador[0] = yContador[0] + 1L;
                contadorN[0] = contadorN[0] + 1L;
            }else if (g.getAnio() == 2){
                //calcular
                sumatoriaXY[1] = sumatoriaXY[1] + calcularSumatoriaXY(g.getGananciaAnio());
                sumatoriaXCuadrada[1] = sumatoriaXCuadrada[1] + calcularSumatoriaXCuadrada();
                xMedia[1] = xMedia[1] + calcularSumatoriaX();
                xContador[1] = xContador[1] + 1L;
                yMedia[1] = yMedia[1] + calcularSumatoriaY(g.getGananciaAnio());
                yContador[1] = yContador[1] + 1L;
                contadorN[1] = contadorN[1] + 1L;
            }else if (g.getAnio() == 3){
                //calcular
                sumatoriaXY[2] = sumatoriaXY[2] + calcularSumatoriaXY(g.getGananciaAnio());
                sumatoriaXCuadrada[2] = sumatoriaXCuadrada[2] + calcularSumatoriaXCuadrada();
                xMedia[2] = xMedia[2] + calcularSumatoriaX();
                xContador[2] = xContador[2] + 1L;
                yMedia[2] = yMedia[2] + calcularSumatoriaY(g.getGananciaAnio());
                yContador[2] = yContador[2] + 1L;
                contadorN[2] = contadorN[2] + 1L;
            }else if (g.getAnio() == 4){
                //calcular
                sumatoriaXY[3] = sumatoriaXY[3] + calcularSumatoriaXY(g.getGananciaAnio());
                sumatoriaXCuadrada[3] = sumatoriaXCuadrada[3] + calcularSumatoriaXCuadrada();
                xMedia[3] = xMedia[3] + calcularSumatoriaX();
                xContador[3] = xContador[3] + 1L;
                yMedia[3] = yMedia[3] + calcularSumatoriaY(g.getGananciaAnio());
                yContador[3] = yContador[3] + 1L;
                contadorN[3] = contadorN[3] + 1L;
            }else if (g.getAnio() == 5){
                //calcular
                sumatoriaXY[4] = sumatoriaXY[4] + calcularSumatoriaXY(g.getGananciaAnio());
                sumatoriaXCuadrada[4] = sumatoriaXCuadrada[4] + calcularSumatoriaXCuadrada();
                xMedia[4] = xMedia[4] + calcularSumatoriaX();
                xContador[4] = xContador[4] + 1L;
                yMedia[4] = yMedia[4] + calcularSumatoriaY(g.getGananciaAnio());
                yContador[4] = yContador[4] + 1L;
                contadorN[4] = contadorN[4] + 1L;
            }else if (g.getAnio() == 6){
                //calcular
                sumatoriaXY[5] = sumatoriaXY[5] + calcularSumatoriaXY(g.getGananciaAnio());
                sumatoriaXCuadrada[5] = sumatoriaXCuadrada[5] + calcularSumatoriaXCuadrada();
                xMedia[5] = xMedia[5] + calcularSumatoriaX();
                xContador[5] = xContador[5] + 1L;
                yMedia[5] = yMedia[5] + calcularSumatoriaY(g.getGananciaAnio());
                yContador[5] = yContador[5] + 1L;
                contadorN[5] = contadorN[5] + 1L;
            }else if (g.getAnio() == 7){
                //calcular
                sumatoriaXY[6] = sumatoriaXY[6] + calcularSumatoriaXY(g.getGananciaAnio());
                sumatoriaXCuadrada[6] = sumatoriaXCuadrada[6] + calcularSumatoriaXCuadrada();
                xMedia[6] = xMedia[6] + calcularSumatoriaX();
                xContador[6] = xContador[6] + 1L;
                yMedia[6] = yMedia[6] + calcularSumatoriaY(g.getGananciaAnio());
                yContador[6] = yContador[6] + 1L;
                contadorN[6] = contadorN[6] + 1L;
            }

        }

        for (int i=0;i<xMedia.length;i++){
            xMedia[i] = (double) xMedia[i]/xContador[i];
            yMedia[i] = (double) yMedia[i]/yContador[i];
        }

        double xMediaCuadrada[] = new double[7];

        for (int i=0;i<xMediaCuadrada.length;i++){
            xMediaCuadrada[i] = (double) Math.pow(xMedia[i],2);
        }

        Map<String, Object> grafica = new HashMap<>();

        for (int i=0;i<7;i++){
            this.pendientes[i] = (sumatoriaXY[i] - ((double) contadorN[i])*xMedia[i]*yMedia[i])/(xMediaCuadrada[i] - ((double) contadorN[i])*xMediaCuadrada[i]);
        }

        double flujoAnual[] = new double[7];
        for (int i=0;i<flujoAnual.length;i++){
            double res = 0D;
            if (i==0){
                res = 0D + this.pendientes[i]*12;
                flujoAnual[i] = res;
            }else {
                res = flujoAnual[i-1] + this.pendientes[i]*12;
                flujoAnual[i] = res;
            }
        }

        grafica.put("year1",flujoAnual[0]);
        grafica.put("year2",flujoAnual[1]);
        grafica.put("year3",flujoAnual[2]);
        grafica.put("year4",flujoAnual[3]);
        grafica.put("year5",flujoAnual[4]);
        grafica.put("year6",flujoAnual[5]);
        grafica.put("year7",flujoAnual[6]);

        double VAN = this.credito;
        for(int j=0 ;j<flujoAnual.length;j++){
            VAN = VAN + (double) (flujoAnual[j]/Math.pow(1+this.interesCredito, j+1));
        }
        //double TIR = 0D;
        //double inflacion = (double) ((0.000049d + 0.0147d + 0.0151d + 0.0271d + 0.04d)/5);
        //double TREMA = (double) (interesCredito + inflacion + interesCredito*inflacion);
        String recomendacion = "";
        //String atractivo = "";

        if(VAN > 0.0d){
            //TIR = (double) estimarTIR(flujoAnual,interesCredito,this.credito);
            /*if(TIR > TREMA){
                recomendacion = "SU PLAN ES VIABLE PERO PODRIA INTENTAR RECORTAR GASTOS INECESARIOS";
            }/*else{
                recomendacion = "DEBE INTENTAR RECORTAS LOS GASTOS DE SU PLAN Y SI PUEDE VENDER SUS PRODUCTOS A UN PRECIO MEJOR";
            }*/
            recomendacion = "SU PLAN ES VIABLE PERO PODRIA INTENTAR RECORTAR GASTOS INECESARIOS";
            /*if(TIR > interesCredito){
                atractivo = "SU PLAN ES ATRACTIVO";
            }else{
                atractivo = "SU PLAN NO ES ATRACTIVO";
            }*/

        }else{
            //TIR = 0f;
            recomendacion = "DEBE INTENTAR GASTAR MENOS Y VENDER MAS PUES ESTA TENIENDO PERDIDAS";
            //atractivo = "SU PLAN NO ES ATRACTIVO";
        }

        grafica.put("VAN",VAN);
        //grafica.put("TIR",TIR);
        grafica.put("Recomendacion",recomendacion);
        //grafica.put("Atractivo",atractivo);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(""+this.nombrePlanNegocio).document("Grafica")
                .set(grafica)
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

    /*public double estimarTIR(double fcAnual[],double interes,double creditoSolicitado){
        double tasa1 = interes;
        double tasa2 = 0d;
        boolean bb = false;
        double VAN1 = 0d;
        double VAN2 = 0d;
        double TIR = 0d;

        while(bb!=true){
            //VAN1 = 0D;
            VAN1 = creditoSolicitado;
            for(int j=0;j<fcAnual.length;j++){
                VAN1 = VAN1 + (double) (fcAnual[j]/Math.pow(1+tasa1, j+1));
            }
            if(VAN1 > 0d){
                VAN2 = VAN1;
                tasa2 = tasa1;
                tasa1 = tasa1 + 0.01d;
            }else{
                TIR = tasa2-VAN2*((tasa2-tasa1)/(VAN2-VAN1));
                bb = true;
            }
        }
        return TIR;
    }*/

    public double calcularSumatoriaY(HashMap<String,Object> ganancias){
        double sumatoria = 0D;
        for(int i=1;i<=12;i++) {
            sumatoria = sumatoria + ((double) ganancias.get(""+i));
        }
        return sumatoria;
    }

    public double calcularSumatoriaX(){
        double sumatoria = 0D;
        for (int i=1;i<=12;i++){
            sumatoria = sumatoria + (double) i;
        }
        return sumatoria;
    }

    public double calcularSumatoriaXCuadrada(){
        double sumatoriaCuadrada = 0D;
        for (int i=1;i<=12;i++){
            sumatoriaCuadrada = sumatoriaCuadrada + Math.pow(i,2);
        }
        return sumatoriaCuadrada;
    }

    public double calcularSumatoriaXY(HashMap<String,Object> ganancias){
        double sumatoria = 0D;

        for(int i=1;i<=12;i++) {
            sumatoria = sumatoria + ((double)i*(double)ganancias.get(""+i));
        }

        return sumatoria;
    }
}

