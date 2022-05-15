package com.example.simuladorplandenegocios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.ref.WeakReference;

public class ResultadosPDF<pubic> extends AppCompatActivity {

    Button generarPDF;

    String titulo="Resultados de la Simulacion";
    String descripcionText="Aqui tendria que ir la matriz";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados_pdf);

        if(checkPermission()){
            Toast.makeText(this,"Permiso aceptado", Toast.LENGTH_LONG).show();
        }else{
            requestPermission();
        }

        generarPDF = findViewById(R.id.btnCrearPDF);

        /*generarPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pdfGenerado();
            }
        });*/
    }

    public void volver(View view){
        Intent sig = new Intent(this, MainActivity.class);
        startActivity(sig);
    }

    public void pdfGenerado(View view){
        PdfDocument pdf= new PdfDocument();
        Paint paint = new Paint();
        TextPaint titutlo = new TextPaint();
        TextPaint descripcion = new TextPaint();

        Bitmap bitmap, bitmapEscala;
        PdfDocument.PageInfo pageInfo= new PdfDocument.PageInfo.Builder(816,1054,1).create();
        PdfDocument.Page pagina1 = pdf.startPage(pageInfo);

        Canvas canvas = pagina1.getCanvas();

        /*bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
        bitmapEscala=Bitmap.createScaledBitmap(bitmap, 80,80,false);
        canvas.drawBitmap(bitmapEscala,368,20,paint);*/

        titutlo.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
        titutlo.setTextSize(20);
        canvas.drawText(titulo,10,150,titutlo);

        descripcion.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        descripcion.setTextSize(14);

        String [][] matriz = {
                {"Corrida","VAM","TIR","TREMA","VIABILIDAD"},
                {"1","200","550","5","NO"},
                {"2","300","50","5","NO"},
                {"3","500","50","5","NO"},
                {"4","400","50","5","NO"},
                {"5","600","50","5","NO"},
                {"6","700","50","5","NO"},
                {"7","800","500","5","NO"},
                {"8","900","50554","5","NO"},
                {"9","100","50","5","NO"},
                {"10","850","50","5","NO"}
        };
        int y = 200;
        int x=10;
        for (int i = 0; i<matriz.length;i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                canvas.drawText(matriz[i][j], x, y, descripcion);
                x+=100;
            }
            y += 15;
            x=10;
        }

        pdf.finishPage(pagina1);
        File file = new File(Environment.getExternalStorageDirectory(),"Resultados.pdf");

        try {

            pdf.writeTo(new FileOutputStream(file));
            Toast.makeText(this, "Se creo el pdf correctamente", Toast.LENGTH_LONG).show();

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "ERROR!!!!"+e, Toast.LENGTH_LONG).show();
        }

        pdf.close();
    }


    private boolean checkPermission(){
        boolean res;
        int permiso1 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int permiso2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);

       /* if(permiso1== PackageManager.PERMISSION_GRANTED && permiso2 == PackageManager.PERMISSION_GRANTED){
            res = true;
        }else{
            res = false;
        }*/

        res = permiso1==PackageManager.PERMISSION_GRANTED && permiso2==PackageManager.PERMISSION_GRANTED;

        return res;
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(this,new String[]{WRITE_EXTERNAL_STORAGE,READ_EXTERNAL_STORAGE},200);
    }

    @SuppressWarnings("MissingSuperCall")

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permisos,@NonNull int [] resultados){
        if(requestCode==200){
            if(resultados.length>0){
                boolean escritura = resultados[0]== PackageManager.PERMISSION_GRANTED;
                boolean lectura = resultados[1]== PackageManager.PERMISSION_GRANTED;

                if(escritura && lectura){
                    Toast.makeText(this,"Permiso Concedido", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this,"Permiso Denegado", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        }
    }


}