package com.example.simuladorplandenegocios.Vista;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;
import com.example.simuladorplandenegocios.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.pdfview.PDFView;

import java.io.File;

public class VistaManual extends AppCompatActivity {
    public final static long ONE_MEGABYTE = 1024*1024*5;
    private PDFView vista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_manual);
        vista = findViewById(R.id.vistaPdf);

        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Simulacion", "Manual.pdf");
        vista.fromFile(file);
        vista.isZoomEnabled();
        vista.show();
    }
}