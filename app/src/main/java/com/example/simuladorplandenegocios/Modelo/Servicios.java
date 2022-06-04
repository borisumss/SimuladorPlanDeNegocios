package com.example.simuladorplandenegocios.Modelo;

public class Servicios {
    private float impuestos;
    private float alimentacion;
    private float servicioLuz;
    private float servicioAgua;
    private float servicioTelefono;
    private float servicioCelular;
    private float salud;
    private float otrosImprevistos;

    public Servicios(){
        this.impuestos = 0f;
        this.alimentacion = 0f;
        this.servicioLuz = 0f;
        this.servicioAgua = 0f;
        this.servicioTelefono = 0f;
        this.servicioCelular = 0f;
        this.salud = 0f;
        this.otrosImprevistos = 0f;
    }

    public void setImpuestos(float impuestos) {
        this.impuestos = impuestos;
    }

    public float getImpuestos() {
        return this.impuestos;
    }

    public void setAlimentacion(float alimentacion) {
        this.alimentacion = alimentacion;
    }

    public float getAlimentacion() {
        return this.alimentacion;
    }

    public void setServicioLuz(float servicioLuz) {
        this.servicioLuz = servicioLuz;
    }

    public float getServicioLuz() {
        return servicioLuz;
    }

    public void setServicioAgua(float servicioAgua) {
        this.servicioAgua = servicioAgua;
    }

    public float getServicioAgua() {
        return this.servicioAgua;
    }

    public void setServicioTelefono(float servicioTelefono) {
        this.servicioTelefono = servicioTelefono;
    }

    public float getServicioTelefono() {
        return this.servicioTelefono;
    }

    public void setServicioCelular(float servicioCelular) {
        this.servicioCelular = servicioCelular;
    }

    public float getServicioCelular() {
        return this.servicioCelular;
    }

    public void setSalud(float salud) {
        this.salud = salud;
    }

    public float getSalud() {
        return this.salud;
    }

    public void setOtrosImprevistos(float otrosImprevistos) {
        this.otrosImprevistos = otrosImprevistos;
    }

    public float getOtrosImprevistos() {
        return this.otrosImprevistos;
    }
}
