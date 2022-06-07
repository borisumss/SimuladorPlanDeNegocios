package com.example.simuladorplandenegocios.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.simuladorplandenegocios.R;


public class MainActivity extends AppCompatActivity {
    private Button iniciar;
    private Button simulacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main
        );


        iniciar=(Button)findViewById(R.id.formularioButton);
        simulacion=(Button)findViewById(R.id.simulacionButton);

        iniciar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent( MainActivity.this, MenuSimulacion.class);
                startActivity(i);
            }
        });

        simulacion.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent( MainActivity.this, MenuSimulacion2.class);
                startActivity(i);
            }
        });


    }

    public void salir(View view){
        finish();
        System.exit(0);
    }
}
