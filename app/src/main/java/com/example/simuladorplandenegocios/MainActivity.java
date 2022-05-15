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
        Triangular t2 = new Triangular(12017, 22103, 34207, 0.07f, 74000);
        t2.estimarVan(100000);

        Intent sig = new Intent(this, ResultadosPDF.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("Triang",t2);
        sig.putExtras(bundle);

        startActivity(sig);

    }



}