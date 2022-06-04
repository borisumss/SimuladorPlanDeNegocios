package com.example.simuladorplandenegocios.Modelo;

public class Producto {
    private String nombre;
    private float costoProduccion;
    private float precioVentaPesimista;
    private float precioVentaModerado;
    private float precioVentaOptimista;
    private float margenBrutoVenta;
    private int cantidadVendida;
    private float totalPeriodo;

    public Producto(){
        this.nombre = "";
        this.costoProduccion = 0f;
        this.precioVentaPesimista = 0f;
        this.precioVentaModerado = 0f;
        this.precioVentaOptimista = 0f;
        this.margenBrutoVenta = 0f;
        this.cantidadVendida = 0;
        this.totalPeriodo = 0f;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setCostoProduccion(float costoProduccion) {
        this.costoProduccion = costoProduccion;
    }

    public float getCostoProduccion() {
        return this.costoProduccion;
    }

    public void setPrecioVentaPesimista(float precioVentaPesimista) {
        this.precioVentaPesimista = precioVentaPesimista;
    }

    public float getPrecioVentaPesimista() {
        return this.precioVentaPesimista;
    }

    public void setPrecioVentaModerado(float precioVentaModerado) {
        this.precioVentaModerado = precioVentaModerado;
    }

    public float getPrecioVentaModerado() {
        return this.precioVentaModerado;
    }

    public void setPrecioVentaOptimista(float precioVentaOptimista) {
        this.precioVentaOptimista = precioVentaOptimista;
    }

    public float getPrecioVentaOptimista() {
        return this.precioVentaOptimista;
    }

    public void setMargenBrutoVenta(float margenBrutoVenta) {
        this.margenBrutoVenta = margenBrutoVenta;
    }

    public float getMargenBrutoVenta() {
        return this.margenBrutoVenta;
    }

    public void setCantidadVendida(int cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    public int getCantidadVendida() {
        return this.cantidadVendida;
    }

    public void setTotalPeriodo(float totalPeriodo) {
        this.totalPeriodo = totalPeriodo;
    }

    public float getTotalPeriodo() {
        return this.totalPeriodo;
    }
}
