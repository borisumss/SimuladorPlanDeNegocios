package com.example.simuladorplandenegocios.Controlador;
import com.example.simuladorplandenegocios.Modelo.Producto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Triangular2 implements Serializable {
    float inversionInicial;
    float interes,TREMA;
    float cuota,saldoInicial,costosFijos,inflacion;
    float[] fci;
    Producto producto;
    float viabilidad=0.0f;
    String atractivoFinal="";
    ArrayList<String[]> resultados;

    public Triangular2 (Producto producto,float inversionInicial,float interes,int plazo,float aportePropio,float costosFijos){
        this.producto = producto;
        this.inversionInicial = -inversionInicial;
        this.interes = interes;
        this.cuota = (float)((inversionInicial/plazo)+(inversionInicial/plazo)*interes);
        this.saldoInicial = inversionInicial + aportePropio;
        this.costosFijos = costosFijos;
        this.inflacion = (float)((0.000049f + 0.0147f + 0.0151f + 0.0271f + 0.04f)/5);
        this.TREMA = (float)(interes + inflacion + interes*inflacion);
        this.fci = new float[13];

        resultados = new ArrayList<String[]>();
    }

    public void estimarVAN(int n){
        int viabilidadI = 0;
        int atractividad = 0;
        for(int i=1 ;i<=n; i++){
            float VAN = 0f;
            float TIR = 0f;
            String viable="";
            String atractivo="";
            this.fci[0]=this.inversionInicial;
            /** CALCULO FLUJO CAJA **/
            for(int j=1; j<=12 ;j++){
                this.fci[j] = flujoCajaMes();
            }
            for(int j=0 ;j<=12;j++){
                if(j==0){
                    VAN = fci[j];
                }else{
                    VAN = VAN + (float)(this.fci[j]/Math.pow(1+this.interes, j));
                }
            }

            if(VAN>0.0f){
                TIR = (float)estimarTIR(fci);
                if(TIR>this.TREMA){
                    viabilidadI++;
                    viable = "SI";
                }else{
                    viable = "NO";
                }

                if(TIR>this.interes){
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
            String [] aux = {""+(i),""+VAN,""+(float)TIR*100+"%",""+(float)this.TREMA*100+"%",""+viable,atractivo};
            resultados.add(aux);
            System.out.println("CORRIDA: "+i+" VAN: "+VAN+" TIR:"+(float)TIR*100+"% TREMA:"+(float)this.TREMA*100+"% VIABILIDAD?: "+viable+" ATRACTIVO?:"+atractivo);
        }
        float porcentajeAtractividad = (float) atractividad/n;
        if(porcentajeAtractividad>0.50){
            atractivoFinal = "ES ATRACTIVO";
        }else{
            atractivoFinal = "NO ES ATRACTIVO";
        }

        viabilidad= 100*((float) viabilidadI/n);
        System.out.println("LA VIABILIDAD DEL PROYECTO ES DE: "+viabilidad+"% Y EL PLAN DE NEGOCIOS"+atractivoFinal);
    }

    public float flujoCajaMes(){
        float flCaja = 0f;
        float utilidadNeta = 0f;
        float utilidadBruta = 0f;
        float venta = 0f;
        float r1 = (float)Math.random();
        float r2 = (float)Math.random();
        if(r1 < ((this.producto.getPrecioVentaModerado()-this.producto.getPrecioVentaPesimista())/(this.producto.getPrecioVentaOptimista()-this.producto.getPrecioVentaPesimista()))){
            venta = this.producto.getPrecioVentaPesimista() + (this.producto.getPrecioVentaModerado()-this.producto.getPrecioVentaPesimista())*(float)Math.pow(r2, 0.5f);
        }else{
            venta = this.producto.getPrecioVentaOptimista() - (this.producto.getPrecioVentaOptimista()-this.producto.getPrecioVentaModerado())*(float)Math.pow(1-r2, 0.5f);
        }
        float margenBrutoVenta = (float)((venta - this.producto.getCostoDeProduccion())/venta);
        int cantidadVendida = this.producto.getCantidadVendida(); /// CUANTO SE QUIRE VENDER
        float totalPeriodo = (float)(venta*cantidadVendida);
        float totalVentas = totalPeriodo;
        float costoProduccionVentas = totalPeriodo*(1-margenBrutoVenta);
        float margenBrutoProduccionVentas = (float)((totalVentas-costoProduccionVentas)/totalVentas);

        float ingresos = 0f;
        float costoProduccion = 0f;
        Random mes = new Random();
        if(mes.nextInt(2) ==1){
            ingresos = totalVentas;
            costoProduccion = (float)(ingresos*(1-margenBrutoProduccionVentas));
        }else{
            ingresos = 0f;
            costoProduccion = 0f;
        }
        utilidadBruta = (float)(ingresos - costoProduccion);
        utilidadNeta = (float) (utilidadBruta - this.costosFijos);
        flCaja = (float) (utilidadNeta - this.cuota + this.inversionInicial + this.saldoInicial);
        return flCaja;
    }

    public float estimarTIR(float fci[]){
        float tasa1 =this.interes;
        float tasa2 =0f;
        boolean bb=false;
        float VAN1=0f;
        float VAN2=0f;
        float TIR = 0f;

        while(bb!=true){
            VAN1=this.fci[0];
            for(int j=1;j<=12;j++){
                VAN1=VAN1 + (float)(this.fci[j]/Math.pow(1+tasa1, j));
            }
            if(VAN1>0f){
                VAN2 = VAN1;
                tasa2 =tasa1;
                tasa1 = tasa1 + 0.01f;
            }else{
                TIR = tasa2-VAN2*((tasa2-tasa1)/(VAN2-VAN1));
                bb = true;
            }
        }
        return TIR;
    }

    public ArrayList<String[]> getResultados(){
        return resultados;
    }

    public float getViabilidad(){
        return viabilidad;
    }

    public String getAtractivoFinal(){
        return atractivoFinal;
    }
}