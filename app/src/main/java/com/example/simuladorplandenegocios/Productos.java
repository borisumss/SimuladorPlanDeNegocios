package com.example.simuladorplandenegocios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Productos extends AppCompatActivity {
    private Spinner spinner1,spinner2;
    private TableLayout tabla;
    private TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        spinner1 = (Spinner) findViewById(R.id.metoInput);

        String [] opciones = {"WaterFall","Scrum","Iterativo-Incremental","XP","Spiral"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,opciones);
        spinner1.setAdapter(adapter);

        spinner2 = (Spinner) findViewById(R.id.plataInput);

        String [] opciones2 = {"App Móvil","Web","Mac Os","Windows","Multiplataforma"};

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,opciones2);
        spinner2.setAdapter(adapter2);

        tabla = (TableLayout) findViewById(R.id.tabla);

        name = (TextView) findViewById(R.id.nombreProducInput);
    }

    public void siguiente(View view){

    }

    public void añadir(View view){
    String nombre = name.getText().toString();
    String metodologia = spinner1.getSelectedItem().toString();
    String plataforma = spinner2.getSelectedItem().toString();
    TableRow row = new TableRow(this);

        TextView col = new TextView(this);
        col.setText(nombre);
        col.setGravity(Gravity.CENTER);

        TextView col2 = new TextView(this);
        col2.setText(metodologia);
        col2.setGravity(Gravity.CENTER);

        TextView col3 = new TextView(this);
        col3.setText(plataforma);
        col3.setGravity(Gravity.CENTER);


        TableRow.LayoutParams params = new TableRow.LayoutParams();
        //params.setMargins(0,0,2,0);
        params.weight=1;

        row.addView(col,params);
        row.addView(col2,params);
        row.addView(col3,params);

    tabla.addView(row);

    }

    public void volver(View view){
        Intent i = new Intent( this, DatosEmprendimiento.class);
        startActivity(i);
    }
}