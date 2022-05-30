package com.example.simuladorplandenegocios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DatosEmprendimiento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_emprendimiento);
    }

    public void siguiente(View view){
        Intent i = new Intent( this, Productos.class);
        startActivity(i);
    }

    public void volver(View view){
        Intent i = new Intent( this, DatosEmprendimiento1.class);
        startActivity(i);
    }
}