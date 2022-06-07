package com.example.simuladorplandenegocios.Modelo;

public class Mantenimiento {
    private String nombre;
    private float costoMantenimiento;

    public Mantenimiento(){
        this.nombre = "";
        this.costoMantenimiento = 0f;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setCostoMantenimiento(float costoMantenimiento) {
        this.costoMantenimiento = costoMantenimiento;
    }

    public float getCostoMantenimiento() {
        return this.costoMantenimiento;
    }
}
