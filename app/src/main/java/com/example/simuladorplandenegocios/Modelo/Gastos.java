package com.example.simuladorplandenegocios.Modelo;

public class Gastos {
    private Servicios servicios;
    private Mantenimiento mantenimiento1,mantenimiento2,mantenimiento3,mantenimiento4;
    private float totalServicios;
    private float totalMantenimiento;
    private float totalGastos;

    public Gastos(Servicios servicios,Mantenimiento mantenimiento1,Mantenimiento mantenimiento2,Mantenimiento mantenimiento3,Mantenimiento mantenimiento4,float totalServicios,float totalMantenimiento,float totalGastos){
        this.servicios = servicios;
        this.mantenimiento1 = mantenimiento1;
        this.mantenimiento2 = mantenimiento2;
        this.mantenimiento3 = mantenimiento3;
        this.mantenimiento4 = mantenimiento4;
        this.totalServicios = totalServicios;
        this.totalMantenimiento= totalMantenimiento;
        this.totalGastos = totalGastos;
    }

    public Servicios getServicios() {
        return this.servicios;
    }

    public Mantenimiento getMantenimiento1() {
        return this.mantenimiento1;
    }

    public Mantenimiento getMantenimiento2() {
        return this.mantenimiento2;
    }

    public Mantenimiento getMantenimiento3() {
        return this.mantenimiento3;
    }

    public Mantenimiento getMantenimiento4() {
        return this.mantenimiento4;
    }

    public float getTotalServicios() {
        return this.totalServicios;
    }

    public float getTotalMantenimiento() {
        return this.totalMantenimiento;
    }

    public float getTotalGastos() {
        return this.totalGastos;
    }
}
