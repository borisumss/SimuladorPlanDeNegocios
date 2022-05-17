package com.example.simuladorplandenegocios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class simulacion extends AppCompatActivity {
    private ProgressBar pb;
    private EditText a,b,c, prestamo,interes, plazo,aporte,costosFiojos, cantidad, costoProduccion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulacion);

        a = (EditText) findViewById(R.id.aa);
        b = (EditText) findViewById(R.id.bb);
        c = (EditText) findViewById(R.id.cc);
        prestamo = (EditText) findViewById(R.id.prest);
        interes = (EditText) findViewById(R.id.tasaInteres);
        plazo = (EditText) findViewById(R.id.plazoText);
        aporte = (EditText) findViewById(R.id.aportePropio);
        costosFiojos = (EditText) findViewById(R.id.costosFijos);
        cantidad = (EditText) findViewById(R.id.cantidadVender);
        costoProduccion = (EditText) findViewById(R.id.costosProduccion);
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
        if(a.getText().toString().length()==0 || b.getText().toString().length()==0 || c.getText().toString().length()==0 || prestamo.getText().toString().length()==0
        || interes.getText().toString().length()==0 || plazo.getText().toString().length()==0 ||aporte.getText().toString().length()==0
        || costosFiojos.getText().toString().length()==0 || cantidad.getText().toString().length()==0 || costoProduccion.getText().toString().length()==0){
            Toast.makeText(this, "LLENE TODOS LOS CAMPOS°", Toast.LENGTH_LONG).show();

            res = false;
        }

        return res;
    }

    public void cambiar(){
        float af,bf,cf,prestf,interesf,plazof,aportef,costosFijosf, cantidadf,costoProducf;

        af = Float.parseFloat(a.getText().toString());
        bf = Float.parseFloat(b.getText().toString());
        cf = Float.parseFloat(c.getText().toString());
        prestf = Float.parseFloat(prestamo.getText().toString());
        interesf = Float.parseFloat(interes.getText().toString());
        plazof = Float.parseFloat(plazo.getText().toString());
        aportef = Float.parseFloat(aporte.getText().toString());
        costosFijosf = Float.parseFloat(costosFiojos.getText().toString());
        cantidadf = Float.parseFloat(cantidad.getText().toString());
        costoProducf = Float.parseFloat(costoProduccion.getText().toString());

        //Triangular t2 = new Triangular(12017, 22103, 34207, 0.07f, 74000);
        pb.setVisibility(View.VISIBLE);
        Triangular t2 = new Triangular(af, bf, cf, interesf, prestf);

        Intent sig = new Intent(this, ResultadosPDF.class);
        Bundle bundle = new Bundle();
        //t2.estimarVan(10000);
        bundle.putSerializable("Triang",t2);
        sig.putExtras(bundle);
        startActivity(sig);
        //pb.setVisibility(View.GONE);
    }
}