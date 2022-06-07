package com.example.simuladorplandenegocios.Modelo;

public class DeudorDatosCredito {
    private Deudor deudor;
    private Credito credito;

    public DeudorDatosCredito(Deudor deudor,Credito credito){
        this.deudor = deudor;
        this.credito = credito;
    }

    public Deudor getDeudor() {
        return this.deudor;
    }

    public Credito getCredito() {
        return this.credito;
    }
}
