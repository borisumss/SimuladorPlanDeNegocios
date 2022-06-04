package com.example.simuladorplandenegocios.Modelo;

public class Deudor {
    private String nombre;
    private String apellido;
    private String estadoCivil;
    private String CI;
    private String extension;
    private String telefono;
    private String edad;

    public Deudor(String nombre,String apellido,String estadoCivil,String CI,String extension,String telefono,String edad){
        this.nombre = nombre;
        this.apellido = apellido;
        this.estadoCivil= estadoCivil;
        this.CI = CI;
        this.extension = extension;
        this.telefono = telefono;
        this.edad = edad;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellido(){
        return this.apellido;
    }

    public String getEstadoCivil() {
        return this.estadoCivil;
    }

    public String getCI() {
        return this.CI;
    }

    public String getExtension() {
        return this.extension;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public String getEdad() {
        return this.edad;
    }
}
