package com.example.simuladorplandenegocios.Vista;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

import static java.lang.Thread.sleep;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.simuladorplandenegocios.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;


public class MainActivity extends AppCompatActivity {
    private Button iniciar;
    private Button simulacion,ayuda;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main
        );

        //seekBar = (SeekBar) findViewById(R.id.seekbar);
        iniciar=(Button)findViewById(R.id.formularioButton);
        simulacion=(Button)findViewById(R.id.simulacionButton);
        ayuda =(Button)findViewById(R.id.ayudaButton);

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

        ayuda.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
               //descarga automatica
                Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/simulador-plan-de-negocios.appspot.com/o/Proyecto%20Final%20RA%2001-2022.pdf?alt=media&token=284b0aca-c1bc-4389-b15b-22fe4e6acb14");
                Intent i = new Intent( Intent.ACTION_VIEW, uri);
                startActivity(i);
            }
        });


    }

    public void descargarManual(){
         File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Simulacion", "Manual-de-Usuario.pdf");
                if(!file.exists()){
                    FirebaseStorage fb = FirebaseStorage.getInstance();
                    StorageReference ref = fb.getReference().child("Proyecto Final RA 01-2022.pdf");
                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String url = uri.toString();
                            descargar(MainActivity.this,"Manual-de-Usuario.pdf",DIRECTORY_DOWNLOADS,url);
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                          /* try {
                                sleep(4000);
                                Intent i = new Intent( MainActivity.this, VistaManual.class);
                                System.out.println("Comienza el sueño");
                                startActivity(i);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }*/
                        }
                    }) ;
                }else{
                    //Intent i = new Intent( MainActivity.this, VistaManual.class);
                   //startActivity(i);
                    file.delete();
                }
    }

    public void salir(View view){
        finish();
        System.exit(0);
    }

    public void descargar(Context context, String nombreArchivo, String destino, String uri2){
        DownloadManager downloadManager=(DownloadManager) context.
                getSystemService(Context.DOWNLOAD_SERVICE);

        Uri uri = Uri.parse(uri2);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION);
        request.setDestinationInExternalFilesDir(context,destino,nombreArchivo);
        request.setDestinationInExternalPublicDir("/Simulacion",nombreArchivo);
        downloadManager.enqueue(request);

    }

}
