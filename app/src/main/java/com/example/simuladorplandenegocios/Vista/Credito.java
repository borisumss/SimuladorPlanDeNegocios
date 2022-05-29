package com.example.simuladorplandenegocios.Vista;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.simuladorplandenegocios.R;

public class Credito extends AppCompatActivity {
    private Spinner spinner1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credito);

        spinner1 = (Spinner) findViewById(R.id.tipoCuotaInput);

        String [] opciones = {"Cuota Fija","Cuota Variable"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,opciones);
        spinner1.setAdapter(adapter);
    }
}