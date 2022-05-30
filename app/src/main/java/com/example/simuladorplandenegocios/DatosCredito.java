package com.example.simuladorplandenegocios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class DatosCredito extends AppCompatActivity {
    private Spinner spinner1,spinner2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_credito);

        spinner1 = (Spinner) findViewById(R.id.actividadInput);

        String [] opciones = {"Productiva","Servicios"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,opciones);
        spinner1.setAdapter(adapter);

        spinner2 = (Spinner) findViewById(R.id.fomarpagoInput);

        String [] opciones2 = {"Mensual","Anual"};

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,opciones2);
        spinner2.setAdapter(adapter2);
    }

    public void siguiente(View view){
        Intent i = new Intent( this, DatosEmprendimiento1.class);
        startActivity(i);
    }

    public void volver(View view){
        Intent i = new Intent( this, DatosCodeudor.class);
        startActivity(i);
    }
}