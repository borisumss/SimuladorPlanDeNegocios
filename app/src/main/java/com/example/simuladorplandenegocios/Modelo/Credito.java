package com.example.simuladorplandenegocios.Modelo;

public class Credito {
    private String actividad;
    private float montoSolicitado;
    private String formaPago;
    private int plazo;
    private float cuota;
    private float interes;

    public Credito(String actividad,float montoSolicitado,String formaPago,int plazo,float cuota,float interes){
        this.actividad = actividad;
        this.montoSolicitado = montoSolicitado;
        this.formaPago = formaPago;
        this.plazo = plazo;
        this.cuota = cuota;
        this.interes = interes;
    }

    public String getActividad() {
        return this.actividad;
    }

    public float getMontoSolicitado() {
        return this.montoSolicitado;
    }

    public String getFormaPago() {
        return this.formaPago;
    }

    public int getPlazo() {
        return this.plazo;
    }

    public float getCuota() {
        return this.cuota;
    }

    public float getInteres() {
        return this.interes;
    }
}
