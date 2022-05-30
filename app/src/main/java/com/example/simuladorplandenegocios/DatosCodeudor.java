package com.example.simuladorplandenegocios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.simuladorplandenegocios.Vista.CostoTotalOperacion;

public class DatosCodeudor extends AppCompatActivity {
    private Spinner spinner1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_codeudor);

        spinner1 = (Spinner) findViewById(R.id.civilCoDeudorInput);

        String [] opciones = {"Soltero","Casado","Divorciado"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,opciones);
        spinner1.setAdapter(adapter);
    }

    public void volver(View view){
        Intent i = new Intent( this, DatosDeudor.class);
        startActivity(i);
    }

    public void Siguiente(View view){
        Intent i = new Intent( this, DatosCredito.class);
        startActivity(i);
    }
}


