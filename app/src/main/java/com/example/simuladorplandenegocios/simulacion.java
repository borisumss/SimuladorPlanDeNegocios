package com.example.simuladorplandenegocios;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class simulacion extends AppCompatActivity {
    Button iniciarNuevaSimulacion;
    EditText precioDeVentaPesimista;
    EditText precioDeVentaModerado;
    EditText precioDeVentaOptimista;
    EditText prestamo;
    EditText tasaDeInteres;
    EditText plazo;
    EditText aportePropio;
    EditText costosFijos;
    EditText cantidadAVender;
    EditText costoDeProduccion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulacion);
        this.iniciarNuevaSimulacion = (Button) findViewById(R.id.IniciarSimulacionButton);

        this.precioDeVentaPesimista = (EditText) findViewById(R.id.PrecioDeVentaPesimistaInput);
        this.precioDeVentaModerado = (EditText) findViewById(R.id.PrecioDeVentaModeradoInput);
        this.precioDeVentaOptimista = (EditText) findViewById(R.id.PrecioDeVentaOptimistaInput);
        this.prestamo = (EditText) findViewById(R.id.PrestamoInput);
        this.tasaDeInteres = (EditText) findViewById(R.id.TasaInteresInput);
        this.plazo = (EditText) findViewById(R.id.PlazoInput);
        this.aportePropio = (EditText) findViewById(R.id.AportePropioInput);
        this.costosFijos = (EditText) findViewById(R.id.CostosFijosInput);
        this.cantidadAVender = (EditText) findViewById(R.id.CantidadAVenderInput);
        this.costoDeProduccion = (EditText) findViewById(R.id.CostoDeProduccionInput);

        this.costoDeProduccion.getText();
    }
}