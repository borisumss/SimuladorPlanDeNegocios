package com.example.simuladorplandenegocios.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.simuladorplandenegocios.DatosDeudor;
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

    public void ayuda(View view){
<<<<<<< HEAD
        Intent i = new Intent( this, gastosfijos2.class);
=======
        Intent i = new Intent( this, DatosDeudor.class);
>>>>>>> 72af0a08fa5ff3913608dd9eb9844affd1c5b19d
        startActivity(i);
    }
}
