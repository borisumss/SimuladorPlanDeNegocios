package com.example.simuladorplandenegocios;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.simuladorplandenegocios.Controlador.Triangular;
import com.example.simuladorplandenegocios.Modelo.Producto;

public class simulacion extends AppCompatActivity {
    Button iniciarNuevaSimulacion;
    EditText precioDeVentaPesimistaInput;
    EditText precioDeVentaModeradoInput;
    EditText precioDeVentaOptimistaInput;
    EditText prestamoInput;
    EditText tasaDeInteresInput;
    EditText plazoInput;
    EditText aportePropioInput;
    EditText costosFijosInput;
    EditText cantidadAVenderInput;
    EditText costoDeProduccionInput;


    float precioDeVentaPesimista;
    float precioDeVentaModerado;
    float precioDeVentaOptimista;
    float prestamo;
    float tasaDeInteres;
    int plazo;
    float aportePropio;
    float costosFijos;
    int cantidadAVender;
    float costoDeProduccion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulacion);
        this.iniciarNuevaSimulacion = (Button) findViewById(R.id.IniciarSimulacionButton);

        this.precioDeVentaPesimistaInput = (EditText) findViewById(R.id.PrecioDeVentaPesimistaInput);
        this.precioDeVentaModeradoInput = (EditText) findViewById(R.id.PrecioDeVentaModeradoInput);
        this.precioDeVentaOptimistaInput = (EditText) findViewById(R.id.PrecioDeVentaOptimistaInput);
        this.prestamoInput = (EditText) findViewById(R.id.PrestamoInput);
        this.tasaDeInteresInput = (EditText) findViewById(R.id.TasaInteresInput);
        this.plazoInput = (EditText) findViewById(R.id.PlazoInput);
        this.aportePropioInput = (EditText) findViewById(R.id.AportePropioInput);
        this.costosFijosInput = (EditText) findViewById(R.id.CostosFijosInput);
        this.cantidadAVenderInput = (EditText) findViewById(R.id.CantidadAVenderInput);
        this.costoDeProduccionInput = (EditText) findViewById(R.id.CostoDeProduccionInput);

        this.precioDeVentaPesimista = Float.parseFloat(this.precioDeVentaPesimistaInput.getText().toString());
        this.precioDeVentaModerado = Float.parseFloat(this.precioDeVentaModeradoInput.getText().toString());
        this.precioDeVentaOptimista = Float.parseFloat(this.precioDeVentaOptimistaInput.getText().toString());
        this.prestamo = Float.parseFloat(this.prestamoInput.getText().toString());
        this.tasaDeInteres = Float.parseFloat(this.tasaDeInteresInput.getText().toString());
        this.plazo = Integer.parseInt(this.plazoInput.getText().toString());
        this.aportePropio = Float.parseFloat(this.aportePropioInput.getText().toString());
        this.costosFijos = Float.parseFloat(this.costosFijosInput.getText().toString());
        this.cantidadAVender = Integer.parseInt(this.cantidadAVenderInput.getText().toString());
        this.costoDeProduccion = Float.parseFloat(this.costoDeProduccionInput.getText().toString());
        Producto p1 = new Producto("App Web",this.costoDeProduccion,this.precioDeVentaPesimista,this.precioDeVentaModerado,this.precioDeVentaOptimista,this.cantidadAVender);
        Triangular t1 = new Triangular(p1,this.prestamo,this.tasaDeInteres,this.plazo,this.aportePropio,this.costosFijos);
        t1.estimarVAN(100);
    }
}