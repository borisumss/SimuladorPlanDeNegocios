package com.example.simuladorplandenegocios.Modelo;

public class Requerimiento {
    private float gastosOperativos;
    private float insumosMateriaPrima;
    private float requerimientosPromocionales;
    private float infraestructura;
    private float equiposVehiculosMaquinaria;
    private float requerimientosLegales;

    public Requerimiento(float gastosOperativos,float insumosMateriaPrima,float requerimientosPromocionales,float infraestructura,float equiposVehiculosMaquinaria,float requerimientosLegales){
        this.gastosOperativos = gastosOperativos;
        this.insumosMateriaPrima = insumosMateriaPrima;
        this.requerimientosPromocionales = requerimientosPromocionales;
        this.infraestructura = infraestructura;
        this.equiposVehiculosMaquinaria = equiposVehiculosMaquinaria;
        this.requerimientosLegales = requerimientosLegales;
    }

    public float getGastosOperativos() {
        return this.gastosOperativos;
    }

    public float getInsumosMateriaPrima() {
        return this.insumosMateriaPrima;
    }

    public float getRequerimientosPromocionales() {
        return this.requerimientosPromocionales;
    }

    public float getInfraestructura() {
        return this.infraestructura;
    }

    public float getEquiposVehiculosMaquinaria() {
        return this.equiposVehiculosMaquinaria;
    }

    public float getRequerimientosLegales() {
        return this.requerimientosLegales;
    }
}
