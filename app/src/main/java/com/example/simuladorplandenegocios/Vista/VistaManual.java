package com.example.simuladorplandenegocios.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;

import com.example.simuladorplandenegocios.R;
import com.pdfview.PDFView;

import java.io.File;

public class VistaManual extends AppCompatActivity {
    private PDFView vista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_manual);
        vista = findViewById(R.id.vistaPdf);
        File file = new File("https://firebasestorage.googleapis.com/v0/b/simulador-plan-de-negocios.appspot.com/o/Proyecto%20Final%20RA%2001-2022.pdf?alt=media&token=284b0aca-c1bc-4389-b15b-22fe4e6acb14","ayuda.pdf");
        vista.fromFile(file);
        vista.isZoomEnabled();
        vista.show();
    }
}