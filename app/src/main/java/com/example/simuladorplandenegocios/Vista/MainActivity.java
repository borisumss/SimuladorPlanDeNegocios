package com.example.simuladorplandenegocios.Vista;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

        iniciar.setOnClickListener(view -> {
            Intent i = new Intent( MainActivity.this, MenuSimulacion.class);
            startActivity(i);
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

    @RequiresApi(api = Build.VERSION_CODES.R)
    public void ayuda(View view){
        Toast.makeText(this, Environment.getStorageDirectory().getAbsolutePath(), Toast.LENGTH_LONG).show();
    }
}
