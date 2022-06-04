package com.example.simuladorplandenegocios.Modelo;

public class Presupuesto {
    private AportePropio aportePropio;
    private Requerimiento requerimiento;
    private float totalAportePropio;
    private float totalRequerimiento;
    private float totalProyecto;
    private float porcentajeAportePropio;
    private String mensajeCumplimientoAporte;
    private float montoSolicitado;
    private float montoFinanciado;
    private String mensajeFinanciamiento;

    public Presupuesto(AportePropio aportePropio,Requerimiento requerimiento,String mensajeCumplimientoAporte,String mensajeFinanciamiento){
        this.aportePropio = aportePropio;
        this.requerimiento = requerimiento;
        this.mensajeCumplimientoAporte = mensajeCumplimientoAporte;
        this.mensajeFinanciamiento = mensajeFinanciamiento;
        this.totalAportePropio = 0f;
        this.totalRequerimiento = 0f;
        this.totalProyecto = 0f;
        this.porcentajeAportePropio = 0f;
        this.montoSolicitado = 0f;
        this.montoFinanciado = 0f;
    }

    public AportePropio getAportePropio() {
        return this.aportePropio;
    }

    public Requerimiento getRequerimiento() {
        return this.requerimiento;
    }

    public String getMensajeCumplimientoAporte() {
        return this.mensajeCumplimientoAporte;
    }

    public String getMensajeFinanciamiento() {
        return this.mensajeFinanciamiento;
    }

    public void setTotalAportePropio(float totalAportePropio) {
        this.totalAportePropio = totalAportePropio;
    }

    public float getTotalAportePropio() {
        return this.totalAportePropio;
    }

    public void setTotalRequerimiento(float totalRequerimiento) {
        this.totalRequerimiento = totalRequerimiento;
    }

    public float getTotalRequerimiento() {
        return this.totalRequerimiento;
    }

    public void setTotalProyecto(float totalProyecto) {
        this.totalProyecto = totalProyecto;
    }

    public float getTotalProyecto() {
        return this.totalProyecto;
    }

    public void setPorcentajeAportePropio(float porcentajeAportePropio) {
        this.porcentajeAportePropio = porcentajeAportePropio;
    }

    public float getPorcentajeAportePropio() {
        return this.porcentajeAportePropio;
    }

    public void setMontoSolicitado(float montoSolicitado) {
        this.montoSolicitado = montoSolicitado;
    }

    public float getMontoSolicitado() {
        return this.montoSolicitado;
    }

    public void setMontoFinanciado(float montoFinanciado) {
        this.montoFinanciado = montoFinanciado;
    }

    public float getMontoFinanciado() {
        return this.montoFinanciado;
    }
}
