package com.example.simuladorplandenegocios.Modelo;

import java.util.HashMap;

public class Ganancia {
    private int anio;
    private HashMap<String,Object> gananciaAnio;

    public Ganancia(int anio, HashMap gananciasAnio){
        this.anio = anio;
        this.gananciaAnio = gananciasAnio;
    }

    public int getAnio() {
        return this.anio;
    }

    public HashMap<String,Object> getGananciaAnio() {
        return this.gananciaAnio;
    }
}
