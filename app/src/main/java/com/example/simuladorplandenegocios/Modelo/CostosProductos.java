package com.example.simuladorplandenegocios.Modelo;

public class CostosProductos {
    Producto producto1;
    Producto producto2;
    Producto producto3;
    Producto producto4;

    public CostosProductos(Producto producto1,Producto producto2,Producto producto3,Producto producto4){
        this.producto1 = producto1;
        this.producto2 = producto2;
        this.producto3 = producto3;
        this.producto4 = producto4;
    }

    public Producto getProducto1() {
        return this.producto1;
    }

    public Producto getProducto2() {
        return this.producto2;
    }

    public Producto getProducto3() {
        return this.producto3;
    }

    public Producto getProducto4() {
        return this.producto4;
    }

}
