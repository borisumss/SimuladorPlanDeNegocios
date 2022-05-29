package com.example.simuladorplandenegocios;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class DatosDeudor extends AppCompatActivity {
    private Spinner spinner1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_deudor);

        spinner1 = (Spinner) findViewById(R.id.civilDeudorInput);

        String [] opciones = {"Soltero","Casado","Divorciado"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,opciones);
        spinner1.setAdapter(adapter);
    }
}