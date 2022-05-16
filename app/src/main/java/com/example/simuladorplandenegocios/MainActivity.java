package com.example.simuladorplandenegocios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ProgressBar pb;
    private TextView cargando;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb = findViewById(R.id.progressBar2);
        pb.setVisibility(View.GONE);
        cargando = findViewById(R.id.cargando);
        cargando.setVisibility(View.INVISIBLE);
    }

    //metodo para ir a la siguiente pagina Reporte
    public void irReporte(View view) {


            cambiar();

    }

    public void cambiar(){
        Triangular t2 = new Triangular(12017, 22103, 34207, 0.07f, 74000);
        pb.setVisibility(View.VISIBLE);
        cargando.setVisibility(View.VISIBLE);
        Intent sig = new Intent(this, ResultadosPDF.class);
        Bundle bundle = new Bundle();
        //t2.estimarVan(10000);
        bundle.putSerializable("Triang",t2);
        sig.putExtras(bundle);
        startActivity(sig);




    }
}
