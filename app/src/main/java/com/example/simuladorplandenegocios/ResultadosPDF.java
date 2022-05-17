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
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class ResultadosPDF<pubic> extends AppCompatActivity {

    Button generarPDF;
    EditText via, atra;
    String titulo="Resultados de la Simulacion";
    private int nroCorrida=0;
    private String[] header={"Corrida","VAN","TIR","VIABILIDAD"};
    Triangular tr;
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
        via = findViewById(R.id.viabilidad);
        atra = findViewById(R.id.atratividad);


        Bundle enviado = getIntent().getExtras();
        tr = (Triangular) enviado.getSerializable("Triang");
        nroCorrida=10000;
        tr.estimarVan(nroCorrida);
        String atractiv = tr.getAtractivo();
        atra.setText(atractiv);


        via.setText(tr.getViabilidad()+"%");
        via.setTextColor(Color.parseColor("BLUE"));

        if(tr.getViabilidad()>50.0){
            via.setTextColor(Color.parseColor("GREEN"));
        }else{
            via.setTextColor(Color.parseColor("RED"));
        }

        if(atractiv.equals("ES ATRACTIVO")){
            atra.setTextColor(Color.parseColor("GREEN"));
        }else{
            atra.setTextColor(Color.parseColor("RED"));
        }
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

        PdfDocument.PageInfo pageInfo= new PdfDocument.PageInfo.Builder(816,1054,nroCorrida/50).create();;
        ArrayList<String[]> matriz =  tr.getResultados();
        int init = 0;
        int end = 50;
        for(int h=0;h<nroCorrida/50;h++){
            PdfDocument.Page pagina = pdf.startPage(pageInfo);
            Canvas canvas = pagina.getCanvas();

            titutlo.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
            titutlo.setTextSize(20);
            canvas.drawText(titulo,250,85,titutlo);


            int y = 150;
            int x=200;
            for (int i = init-1; i<end;i++) {
                for (int j = 0; j < matriz.get(0).length; j++) {
                    if(i<init){
                        canvas.drawText(header[j], x, y, descripcion);
                        x+=100;
                    }else{
                        canvas.drawText(matriz.get(i)[j], x, y, descripcion);
                        x+=100;
                    }

                }
                y += 15;
                x=200;
            }
            init+=50;
            end+=50;
            pdf.finishPage(pagina);
        }
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