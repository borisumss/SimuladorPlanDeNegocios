package com.example.simuladorplandenegocios.Modelo;

import java.util.HashMap;

public class Anio {
    private HashMap<String,Object> mesesGanancia;

    public Anio(){
        this.mesesGanancia = new HashMap<>();
    }

    public void asignarGananciaMensual(long mes,double ganancia){
        if ( mes == 1L ){
            this.mesesGanancia.put("1",ganancia);
        }else if (mes == 2L){
            this.mesesGanancia.put("2",ganancia);
        }else if ( mes == 3L ){
            this.mesesGanancia.put("3",ganancia);
        }else if ( mes == 4L ){
            this.mesesGanancia.put("4",ganancia);
        }else if ( mes == 5L ){
            this.mesesGanancia.put("5",ganancia);
        }else if ( mes == 6L ){
            this.mesesGanancia.put("6",ganancia);
        }else if ( mes == 7L ){
            this.mesesGanancia.put("7",ganancia);
        }else if ( mes == 8L ){
            this.mesesGanancia.put("8",ganancia);
        }else if ( mes == 9L ){
            this.mesesGanancia.put("9",ganancia);
        }else if ( mes == 10L ){
            this.mesesGanancia.put("10",ganancia);
        }else if ( mes == 11L ){
            this.mesesGanancia.put("11",ganancia);
        }else if ( mes == 12L ){
            this.mesesGanancia.put("12",ganancia);
        }
    }

    public void vaciarAnio(){
        this.mesesGanancia = new HashMap<>();
    }

    public void setMesesGanancia(HashMap<String, Object> mesesGanancia) {
        this.mesesGanancia = mesesGanancia;
    }

    public HashMap<String, Object> getMesesGanancia() {
        return this.mesesGanancia;
    }
}
