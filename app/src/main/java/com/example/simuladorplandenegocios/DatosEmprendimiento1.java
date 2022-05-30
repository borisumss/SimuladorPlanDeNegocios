package com.example.simuladorplandenegocios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.Toast;

public class DatosEmprendimiento1 extends AppCompatActivity {
    private Spinner spinner1,spinner2;
    private CheckBox check;
    private EditText time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_emprendimiento1);

        spinner1 = (Spinner) findViewById(R.id.deptoInput);

        String [] opciones = {"Cochabamba"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,opciones);
        spinner1.setAdapter(adapter);

        spinner2 = (Spinner) findViewById(R.id.provinInput);

        String [] opciones2 = {"Arani","Arque","Ayopaya","Bolivar","Campero","Capinota","Carrasco","Cercado","Chapare"
        ,"Esteban Arce","Germán Jordan","Mizque","Punata","Quillacollo","Tapacarí","Tiraque"};

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,opciones2);
        spinner2.setAdapter(adapter2);
        check = (CheckBox) findViewById(R.id.funcionamiento);
        time = (EditText) findViewById(R.id.tiempoInput);
        check2();
    }

    public void check(View view){
        if(check.isChecked()){
            time.setEnabled(true);
           // Toast.makeText(this,"ON", Toast.LENGTH_LONG).show();
        }else{
            time.setEnabled(false);
            //Toast.makeText(this,"OFF", Toast.LENGTH_LONG).show();
        }
    }

    public void check2(){
        if(check.onCheckIsTextEditor()){
            time.setEnabled(true);
        }else{
            time.setEnabled(false);
        }
    }

    public void siguiente(View view){
        Intent i = new Intent( this, DatosEmprendimiento.class);
        startActivity(i);
    }

    public void volver(View view){
        Intent i = new Intent( this, DatosCredito.class);
        startActivity(i);
    }
}