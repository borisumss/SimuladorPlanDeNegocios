package com.example.simuladorplandenegocios.Modelo;

public class AportePropio {
    private float efectivo;
    private float manoObraEmprendedor;
    private float insumosMateriaPrima;
    private float requerimientosPromocionales;
    private float infraestructura;
    private float equiposVehiculosMaquinaria;
    private float requerimientosLegales;

    public AportePropio(float efectivo,float manoObraEmprendedor,float insumosMateriaPrima,float requerimientosPromocionales,float infraestructura,float equiposVehiculosMaquinaria,float requerimientosLegales){
        this.efectivo = efectivo;
        this.manoObraEmprendedor = manoObraEmprendedor;
        this.insumosMateriaPrima = insumosMateriaPrima;
        this.requerimientosPromocionales = requerimientosPromocionales;
        this.infraestructura = infraestructura;
        this.equiposVehiculosMaquinaria = equiposVehiculosMaquinaria;
        this.requerimientosLegales = requerimientosLegales;
    }

    public float getEfectivo() {
        return this.efectivo;
    }

    public float getManoObraEmprendedor() {
        return this.manoObraEmprendedor;
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
