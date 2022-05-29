package com.example.simuladorplandenegocios.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.simuladorplandenegocios.R;


public class MainActivity extends AppCompatActivity {
    private Button iniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        iniciar=(Button)findViewById(R.id.iniciar);

        iniciar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent( MainActivity.this, simulacion.class);
                startActivity(i);
            }
        });


    }

    public void salir(View view){
        finish();
        System.exit(0);
    }
}
