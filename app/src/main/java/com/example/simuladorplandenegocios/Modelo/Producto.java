package com.example.simuladorplandenegocios.Modelo;

public class Producto {
    String nombre;
    float costoDeProduccion;
    float precioVentaPesimista,precioVentaModerado,precioVentaOptimista;
    int cantidadVendida;
    public Producto(String nombre,float costoDeProduccion,float precioVentaPesimista,float precioVentaModerado,float precioVentaOptimista,int cantidadVendida){
        this.nombre = nombre;
        this.costoDeProduccion = costoDeProduccion;
        this.precioVentaPesimista = precioVentaPesimista;
        this.precioVentaModerado = precioVentaModerado;
        this.precioVentaOptimista = precioVentaOptimista;
        this.cantidadVendida = cantidadVendida;
    }

    public String getNombre(){
        return this.nombre;
    }

    public float getCostoDeProduccion(){
        return this.costoDeProduccion;
    }

    public float getPrecioVentaPesimista(){
        return this.precioVentaPesimista;
    }

    public float getPrecioVentaModerado(){
        return this.precioVentaModerado;
    }

    public float getPrecioVentaOptimista(){
        return this.precioVentaOptimista;
    }

    public int getCantidadVendida(){
        return this.cantidadVendida;
    }
}
