package com.example.simuladorplandenegocios;

import java.io.Serializable;

public class Triangular implements Serializable {
    float a,b,c;
    float interes,inversion,TREMA;
    float cuota,saldoInicial,costosFijos,costosProduccion,margenBrutoCostosProduccion;
    float[] fci;
    String atractivo="";
    float viabilidad=0.0f;
    public Triangular(float a,float b,float c,float interes,float inversion){
        this.a = a;
        this.b = b;
        this.c = c;
        this.interes = interes;
        this.inversion = -inversion;
        //this.cuota =(float)((inversion/plazo)+(inversion/plazo)*interes);//fijo
        //this.saldoInicial = inversion + efectivoPropio;//fijo
        //this.costosFijos = costosFijos;//fijo
        //this.costosProduccion = costosProduccion;
        //this.margenBrutoCostosProduccion = margenBrutoCostosProduccion;
        this.fci = new float [13];
        //this.TREMA = (float)(interes + inflacion + inflacion*interes);
    }

    public void estimarVan(int n){

        this.fci[0] = this.inversion;
        float VAN = 0f;
        int frecuenciaVAN = 0;
        int TIRAtractivo = 0;
        int TIRNoAtractivo = 0;
        float TIR = 0.0f;
        for(int i=0;i<=n;i++){
            for(int j=1;j<=12;j++){
                float r1 = (float) (Math.random());
                float r2 = (float) (Math.random());
                if(r1<(float)((b-a)/(c-a))){
                    this.fci[j]=(float)(a+(b-a)*Math.pow(r2,2));
                }else{
                    this.fci[j]=(float)(c-(c-b)*Math.pow(1-r2,2));
                }
            }
            for(int j=0;j<fci.length;j++){
                if(j == 0){
                    VAN=fci[j];
                }else{
                    VAN=VAN + (float)(this.fci[j]/Math.pow(1+this.interes, j));
                }
            }
            if(VAN > 0f){
                frecuenciaVAN++;
                TIR = (float)estimarTIR(fci);
                if(TIR>this.interes){
                    TIRAtractivo++;
                }else{
                    TIRNoAtractivo++;
                }
            }else{
                TIR = 0f;
            }
            System.out.println("CORRIDA: "+i+"  EL VAN ES: "+VAN+" Y SU TIR ES: "+TIR*100+"%");
        }

        if(TIRAtractivo>TIRNoAtractivo){
            atractivo = "ES ATRACTIVO";
        }else if(TIRAtractivo<TIRNoAtractivo){
            atractivo = "NO ES MUY ATRACTIVO";
        }else if(TIRAtractivo == TIRNoAtractivo){
            atractivo = "ESTA BIEN";
        }

        viabilidad= (((float)frecuenciaVAN/n)*100);
       // System.out.println("LA VIABILIDAD DEL PROYECTO ES DE: "+(((float)frecuenciaVAN/n)*100)+"% , Y "+atractivo);

    }

    public String getAtractivo(){
        return atractivo;
    }

    public float getViabilidad(){
        return viabilidad;
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
}
