package com.example.simuladorplandenegocios.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;

import com.example.simuladorplandenegocios.R;
import com.pdfview.PDFView;

import java.io.File;

public class VistaPlanNegocioPDF extends AppCompatActivity {
    private PDFView vista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_plan_negocio_pdf);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        vista = findViewById(R.id.vistaPdf);
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Simulacion", "PlanDeNegocio.pdf");
        //File file = new File(Environment.getExternalStorageDirectory(), "Resultados.pdf");
        vista.fromFile(file);
        vista.isZoomEnabled();

        vista.show();
    }
}