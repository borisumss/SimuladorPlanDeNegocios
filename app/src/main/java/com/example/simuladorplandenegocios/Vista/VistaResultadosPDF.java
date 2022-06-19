package com.example.simuladorplandenegocios.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;

import com.example.simuladorplandenegocios.R;
import com.pdfview.PDFView;

import java.io.File;

public class VistaResultadosPDF extends AppCompatActivity {
    private PDFView vista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_resultados_pdf);
        vista = findViewById(R.id.vistaPdf);
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Simulacion", "ResultadosSimulacion.pdf");
        vista.fromFile(file);
        vista.isZoomEnabled();
        vista.show();
    }
}