package com.example.simuladorplandenegocios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.simuladorplandenegocios.Controlador.Triangular2;
import com.example.simuladorplandenegocios.Modelo.Producto;

public class simulacion extends AppCompatActivity {
    private ProgressBar pb;
    private EditText precioDeVentaPesimistaInput,precioDeVentaModeradoInput,precioDeVentaOptimistaInput, prestamoInput,tasaDeInteresInput,
            plazoInput,aportePropioInput,costosFijosInput, cantidadAVenderInput, costoDeProduccionInput;

    private float precioDeVentaPesimista,precioDeVentaModerado,precioDeVentaOptimista, prestamo,tasaDeInteres,
            aportePropio,costosFijos, costoDeProduccion;

    private int plazo,cantidadAVender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulacion);

        precioDeVentaPesimistaInput = (EditText) findViewById(R.id.PrecioDeVentaPesimistaInput);
        precioDeVentaModeradoInput = (EditText) findViewById(R.id.PrecioDeVentaModeradoInput);
        precioDeVentaOptimistaInput = (EditText) findViewById(R.id.PrecioDeVentaOptimistaInput);
        prestamoInput = (EditText) findViewById(R.id.PrestamoInput);
        tasaDeInteresInput = (EditText) findViewById(R.id.TasaInteresInput);
        plazoInput = (EditText) findViewById(R.id.PlazoInput);
        aportePropioInput = (EditText) findViewById(R.id.AportePropioInput);
        costosFijosInput = (EditText) findViewById(R.id.CostosFijosInput);
        cantidadAVenderInput = (EditText) findViewById(R.id.CantidadAVenderInput);
        costoDeProduccionInput = (EditText) findViewById(R.id.CostoDeProduccionInput);
        pb = findViewById(R.id.progressBar2);
        pb.setVisibility(View.GONE);
    }

    public void irReporte(View view) {
        if(validarCampos()){
            cambiar();
        }

    }
    public boolean validarCampos(){
        boolean res = true;
        if(precioDeVentaPesimistaInput.getText().toString().length()==0 || precioDeVentaModeradoInput.getText().toString().length()==0
                || precioDeVentaOptimistaInput.getText().toString().length()==0 || prestamoInput.getText().toString().length()==0
                || tasaDeInteresInput.getText().toString().length()==0 || plazoInput.getText().toString().length()==0 ||aportePropioInput.getText().toString().length()==0
                || costosFijosInput.getText().toString().length()==0 || cantidadAVenderInput.getText().toString().length()==0 || costoDeProduccionInput.getText().toString().length()==0){
            Toast.makeText(this, "LLENE TODOS LOS CAMPOS°", Toast.LENGTH_LONG).show();

            res = false;
        }

        return res;
    }

    public void cambiar(){

        precioDeVentaPesimista = Float.parseFloat(precioDeVentaPesimistaInput.getText().toString());
        precioDeVentaModerado = Float.parseFloat(precioDeVentaModeradoInput.getText().toString());
        precioDeVentaOptimista = Float.parseFloat(precioDeVentaOptimistaInput.getText().toString());
        prestamo = Float.parseFloat(prestamoInput.getText().toString());
        tasaDeInteres = Float.parseFloat(tasaDeInteresInput.getText().toString());
        plazo = Integer.parseInt(plazoInput.getText().toString());
        aportePropio = Float.parseFloat(aportePropioInput.getText().toString());
        costosFijos = Float.parseFloat(costosFijosInput.getText().toString());
        cantidadAVender = Integer.parseInt(cantidadAVenderInput.getText().toString());
        costoDeProduccion = Float.parseFloat(costoDeProduccionInput.getText().toString());


        pb.setVisibility(View.VISIBLE);
        Producto p1 = new Producto("App Web",costoDeProduccion,precioDeVentaPesimista,precioDeVentaModerado,precioDeVentaOptimista,cantidadAVender);
        Triangular2 t1 = new Triangular2(p1,prestamo,tasaDeInteres,plazo,aportePropio,costosFijos);

        Intent sig = new Intent(this, ResultadosPDF.class);
        Bundle bundle = new Bundle();
        //t1.estimarVan(10000);
        bundle.putSerializable("Triang",t1);
        sig.putExtras(bundle);
        startActivity(sig);
        //pb.setVisibility(View.GONE);
    }
}