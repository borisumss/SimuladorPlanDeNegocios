package com.example.simuladorplandenegocios.Controlador;

import com.example.simuladorplandenegocios.Modelo.Anio;
import com.example.simuladorplandenegocios.Modelo.Ganancia;

import java.util.ArrayList;
import java.util.HashMap;

public class RegresionLineal {
    private long n;
    private HashMap<String,Object> aniosCorridas;
    private String nombrePlanNegocio;
    private double[] pendientes;
    //private ArrayList<> pendientes;

    public RegresionLineal(long n,HashMap aniosCorridas,String nombrePlanNegocio){
        this.n = n;
        this.aniosCorridas = aniosCorridas;
        this.nombrePlanNegocio = nombrePlanNegocio;
        this.pendientes = new double[7];
    }

    public void ejecutarRegresion(){
        ArrayList<Ganancia> ganaciasRegresion = new ArrayList<>();
        for (int i=1 ; i<=this.n ;i++){
            ArrayList<Anio> anios = (ArrayList<Anio>) this.aniosCorridas.get(""+i);
            for (int j=0 ; i<anios.size() ; j++){
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
            }else if (g.getAnio() == 2){
                //calcular
                sumatoriaXY[1] = sumatoriaXY[1] + calcularSumatoriaXY(g.getGananciaAnio());
                sumatoriaXCuadrada[1] = sumatoriaXCuadrada[1] + calcularSumatoriaXCuadrada();
                xMedia[1] = xMedia[1] + calcularSumatoriaX();
                xContador[1] = xContador[1] + 1L;
                yMedia[1] = yMedia[1] + calcularSumatoriaY(g.getGananciaAnio());
                yContador[1] = yContador[1] + 1L;
            }else if (g.getAnio() == 3){
                //calcular
                sumatoriaXY[2] = sumatoriaXY[2] + calcularSumatoriaXY(g.getGananciaAnio());
                sumatoriaXCuadrada[2] = sumatoriaXCuadrada[2] + calcularSumatoriaXCuadrada();
                xMedia[2] = xMedia[2] + calcularSumatoriaX();
                xContador[2] = xContador[2] + 1L;
                yMedia[2] = yMedia[2] + calcularSumatoriaY(g.getGananciaAnio());
                yContador[2] = yContador[2] + 1L;
            }else if (g.getAnio() == 4){
                //calcular
                sumatoriaXY[3] = sumatoriaXY[3] + calcularSumatoriaXY(g.getGananciaAnio());
                sumatoriaXCuadrada[3] = sumatoriaXCuadrada[3] + calcularSumatoriaXCuadrada();
                xMedia[3] = xMedia[3] + calcularSumatoriaX();
                xContador[3] = xContador[3] + 1L;
                yMedia[3] = yMedia[3] + calcularSumatoriaY(g.getGananciaAnio());
                yContador[3] = yContador[3] + 1L;
            }else if (g.getAnio() == 5){
                //calcular
                sumatoriaXY[4] = sumatoriaXY[4] + calcularSumatoriaXY(g.getGananciaAnio());
                sumatoriaXCuadrada[4] = sumatoriaXCuadrada[4] + calcularSumatoriaXCuadrada();
                xMedia[4] = xMedia[4] + calcularSumatoriaX();
                xContador[4] = xContador[4] + 1L;
                yMedia[4] = yMedia[4] + calcularSumatoriaY(g.getGananciaAnio());
                yContador[4] = yContador[4] + 1L;
            }else if (g.getAnio() == 6){
                //calcular
                sumatoriaXY[5] = sumatoriaXY[5] + calcularSumatoriaXY(g.getGananciaAnio());
                sumatoriaXCuadrada[5] = sumatoriaXCuadrada[5] + calcularSumatoriaXCuadrada();
                xMedia[5] = xMedia[5] + calcularSumatoriaX();
                xContador[5] = xContador[5] + 1L;
                yMedia[5] = yMedia[5] + calcularSumatoriaY(g.getGananciaAnio());
                yContador[5] = yContador[5] + 1L;
            }else if (g.getAnio() == 7){
                //calcular
                sumatoriaXY[6] = sumatoriaXY[6] + calcularSumatoriaXY(g.getGananciaAnio());
                sumatoriaXCuadrada[6] = sumatoriaXCuadrada[6] + calcularSumatoriaXCuadrada();
                xMedia[6] = xMedia[6] + calcularSumatoriaX();
                xContador[6] = xContador[6] + 1L;
                yMedia[6] = yMedia[6] + calcularSumatoriaY(g.getGananciaAnio());
                yContador[6] = yContador[6] + 1L;
            }
        }


    }

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

