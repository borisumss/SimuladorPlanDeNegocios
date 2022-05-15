package com.example.simuladorplandenegocios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //metodo para ir a la siguiente pagina Reporte
    public void irReporte(View view){
        Intent sig = new Intent(this, ResultadosPDF.class);
        startActivity(sig);
    }


}