package com.example.simuladorplandenegocios.Modelo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.simuladorplandenegocios.R;
import com.example.simuladorplandenegocios.Vista.MainActivity;
import com.example.simuladorplandenegocios.Vista.MenuSimulacion2;
import com.example.simuladorplandenegocios.Vista.VistaPlanNegocioPDF;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Resultados#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Resultados extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button inf;
    public Resultados() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Resultados.
     */
    // TODO: Rename and change types and number of parameters
    public static Resultados newInstance(String param1, String param2) {
        Resultados fragment = new Resultados();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_resultados, container, false);
        if(checkPermission()){
            // Toast.makeText(this,"Permiso aceptado", Toast.LENGTH_LONG).show();
        }else{
            requestPermission();
        }

        inf = (Button) v.findViewById(R.id.informe);
        inf.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                generarPdf(view);
            }
        });
        return v;
    }

    public void generarPdf(View view){
        try{
            String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Simulacion";

            File dir = new File(path);
            if(!dir.exists()){
                dir.mkdir();
            }

            File file = new File(dir, "PlanDeNegocio.pdf");
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            Document documento = new Document();
            PdfWriter.getInstance(documento,fileOutputStream);

            documento.open();
            //Inicio de ingreso de datos en el PDF

            clienteCredito(documento);
            presupuesto(documento);
            analisisFinanciero(documento);
            ventas(documento);
            operacion(documento);
            utilidad(documento);

            //fin ingreso datos en el PDF
            documento.close();

            Intent i = new Intent( getContext(), VistaPlanNegocioPDF.class);
            startActivity(i);

        }catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
    }

    public void clienteCredito(Document documento){
        try {
            Paragraph titulo = new Paragraph("PLAN DE NEGOCIOS\n\n",
                    FontFactory.getFont("arial",30, Font.BOLD, BaseColor.BLUE));
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);

            Paragraph cliente = new Paragraph("DATOS DEL CLIENTE\n\n",
                    FontFactory.getFont("arial",18, Font.BOLD, BaseColor.BLACK));
            documento.add(cliente);

            PdfPTable tablaCliente = new PdfPTable(5);
            tablaCliente.setWidthPercentage(100);
            String [] headerCliente = {"Nombres y Apellidos","Doc. Identidad","Edad","Estado civil","Telefono"};
            for(int i = 0; i<headerCliente.length;i++){
                tablaCliente.addCell(headerCliente[i]);
            }
            //Rescatar de la BD
            String [] datosCliente = {"Juan Perez Galindo","7777777","20","Divorciado","66666666"};
            for(int i = 0; i<datosCliente.length;i++){
                tablaCliente.addCell(datosCliente[i]);
            }
            tablaCliente.setSpacingAfter(20);
            documento.add(tablaCliente);

            Paragraph credito = new Paragraph("DATOS DEL CREDITO\n\n",
                    FontFactory.getFont("arial",18, Font.BOLD, BaseColor.BLACK));
            documento.add(credito);
            credito.setSpacingBefore(20);

            PdfPTable tablaCredito = new PdfPTable(5);
            tablaCredito.setWidthPercentage(100);
            String [] headerCredito = {"Monto Solicitado","Actividad","Plazo","Interes","Forma de pago"};
            for(int i = 0; i<headerCredito.length;i++){
                tablaCredito.addCell(headerCredito[i]);
            }
            //Rescatar de la BD
            String [] datosCredito = {"20.000Bs","Servicios","84","11.5%","Mensual"};
            for(int i = 0; i<datosCredito.length;i++){
                tablaCredito.addCell(datosCredito[i]);
            }
            tablaCredito.setSpacingAfter(20);
            documento.add(tablaCredito);

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public void presupuesto(Document documento){
        try {
            Paragraph cliente = new Paragraph("PRESUPUESTO RESUMEN\n\n",
                    FontFactory.getFont("arial",18, Font.BOLD, BaseColor.BLACK));
            documento.add(cliente);
            // cliente.setSpacingBefore(200);

            PdfPTable tabla = new PdfPTable(3);
            //tabla.setSpacingBefore(10);

            PdfPCell cell = new PdfPCell(new Phrase("APORTE PROPIO"));
            cell.setColspan(2);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(cell);
            tabla.addCell("MONTO TOTAL (Bs)");

            Paragraph ko = new Paragraph("K.O.",
                    FontFactory.getFont("arial",25, Font.BOLD, BaseColor.BLACK));
            PdfPCell kocell = new PdfPCell(new Phrase(ko));
            kocell.setRowspan(4);
            kocell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            kocell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tabla.addCell(kocell);
            tabla.addCell("Efectivo");
            tabla.addCell("20000");
            tabla.addCell("Mano de obra del Emprendedor");
            tabla.addCell("2000");
            tabla.addCell("Materia prima,insumas");
            tabla.addCell("0");
            tabla.addCell("Requerimientos promocionales");
            tabla.addCell("10000");

            Paragraph ki = new Paragraph("K.I.",
                    FontFactory.getFont("arial",25, Font.BOLD, BaseColor.BLACK));
            PdfPCell kicell = new PdfPCell(new Phrase(ki));
            kicell.setRowspan(3);
            kicell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            kicell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tabla.addCell(kicell);
            tabla.addCell(new PdfPCell(new Phrase("Infraestructura y/o terrenos y/o plantines")));
            tabla.addCell("0");
            tabla.addCell("Maquinaria, Equipos, Vehículos");
            tabla.addCell("17000");
            tabla.addCell("Requerimientos legales");
            tabla.addCell("0");

            PdfPCell totalCell = new PdfPCell(new Phrase("TOTAL"));
            totalCell.setColspan(2);
            totalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tabla.addCell(totalCell);
            tabla.addCell("49000");

            documento.add(tabla);


            PdfPTable tabla2 = new PdfPTable(3);
            //tabla.setSpacingBefore(10);

            PdfPCell cell2 = new PdfPCell(new Phrase("REQUERIMIENTO"));
            cell2.setColspan(2);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla2.addCell(cell2);
            tabla2.addCell("MONTO TOTAL (Bs)");

            kocell.setRowspan(3);
            tabla2.addCell(kocell);

            tabla2.addCell("Gastos Operativos");
            tabla2.addCell("0");
            tabla2.addCell("Materia prima,insumas");
            tabla2.addCell("0");
            tabla2.addCell("Requerimientos promocionales");
            tabla2.addCell("0");

            kicell.setRowspan(3);
            tabla2.addCell(kicell);
            tabla2.addCell(new PdfPCell(new Phrase("Infraestructura y/o terrenos y/o plantines")));
            tabla2.addCell("0");
            tabla2.addCell("Maquinaria, Equipos, Vehículos");
            tabla2.addCell("294000");
            tabla2.addCell("Requerimientos legales");
            tabla2.addCell("10000");

            tabla2.addCell(totalCell);
            tabla.addCell("304000");

            tabla2.setSpacingBefore(20);
            tabla2.setSpacingAfter(20);
            documento.add(tabla2);


            PdfPTable tabla3 = new PdfPTable(2);
            tabla3.addCell("TOTAL PROYECTO");
            tabla3.addCell("333000");
            tabla3.addCell("APORTE PROPIO");
            tabla3.addCell("49000");
            tabla3.addCell("APORTE PROPIO (%)");
            tabla3.addCell("0.1472");

            Paragraph cumple = new Paragraph("SI CUMPLE CON APORTE PROPIO",
                    FontFactory.getFont("arial",15, Font.BOLD, BaseColor.BLACK));
            PdfPCell cellCumple = new PdfPCell(new Phrase(cumple));
            cellCumple.setColspan(2);
            cellCumple.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellCumple.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tabla3.addCell(cellCumple);

            tabla3.addCell("MONTO SOLICITADO");
            tabla3.addCell("284000");
            tabla3.addCell("MONTO A FINANCIAR");
            tabla3.addCell("284000");

            Paragraph monto = new Paragraph("MONTO A FINANCIAR CORRECTO",
                    FontFactory.getFont("arial",15, Font.BOLD, BaseColor.BLACK));
            PdfPCell cellMonto = new PdfPCell(new Phrase(monto));
            cellMonto.setColspan(2);
            cellMonto.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellMonto.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tabla3.addCell(cellMonto);

            tabla3.setSpacingAfter(80);
            documento.add(tabla3);


            /*tabla.addCell(new PdfPCell(new Phrase("3,3")));*/
        } catch (DocumentException e){
            e.printStackTrace();
        }
    }

    public void utilidad(Document documento){
        try {
            Paragraph cliente = new Paragraph("TABLA DE UTILIDAD NETA\n\n",
                    FontFactory.getFont("arial",15, Font.BOLD, BaseColor.ORANGE));
            cliente.setAlignment(Element.ALIGN_CENTER);
            documento.add(cliente);

            PdfPTable tabla = new PdfPTable(2);

            PdfPCell cell = new PdfPCell(new Phrase("UTILIDAD"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(cell);

            PdfPCell cell2 = new PdfPCell(new Phrase("TOTAL EN Bs"));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(cell2);

            tabla.addCell("(+) INGRESOS TOTALES");
            tabla.addCell("160000");
            tabla.addCell("(-) COSTO VARIABLE");
            tabla.addCell("141120");
            tabla.addCell("(=) UTILIDAD BRUTA");
            tabla.addCell("18880");
            tabla.addCell("(-) COSTO FIJO");
            tabla.addCell("11300");
            tabla.addCell("(=) UTILIDAD NETA");
            tabla.addCell("7580");

            tabla.setSpacingAfter(20);
            documento.add(tabla);

        } catch (DocumentException e){
            e.printStackTrace();
        }
    }

    public void operacion(Document documento){
        try {
            Paragraph cliente = new Paragraph("TABLA TOTAL DE LA OPERACION\n\n",
                    FontFactory.getFont("arial",15, Font.BOLD, BaseColor.ORANGE));
            cliente.setAlignment(Element.ALIGN_CENTER);
            documento.add(cliente);

            PdfPTable tabla = new PdfPTable(2);


            PdfPCell cell = new PdfPCell(new Phrase("TIPO DE COSTO"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(cell);

            PdfPCell cell2 = new PdfPCell(new Phrase("TOTAL EN Bs"));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(cell2);

            tabla.addCell("Costo Fijo");
            tabla.addCell("11300");
            tabla.addCell("Costo variable total");
            tabla.addCell("141120");
            tabla.addCell("COSTO TOTAL DE OPERACION MENSUAL");
            tabla.addCell("152420");

            tabla.setSpacingAfter(40);
            documento.add(tabla);

        } catch (DocumentException e){
            e.printStackTrace();
        }
    }
    public void analisisFinanciero(Document documento){
        try {
            Paragraph cliente = new Paragraph("ANALISIS FINANCIERO\n\n",
                    FontFactory.getFont("arial",18, Font.BOLD, BaseColor.BLACK));
            documento.add(cliente);

            PdfPTable tabla = new PdfPTable(6);
            tabla.setWidthPercentage(100);

            PdfPCell cell = new PdfPCell(new Phrase("PRODUCTOS"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(cell);

            PdfPCell cell2 = new PdfPCell(new Phrase("CANTIDAD"));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(cell2);

            PdfPCell cell3 = new PdfPCell(new Phrase("COSTO POR PRODUCTO"));
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(cell3);

            PdfPCell cell4 = new PdfPCell(new Phrase("PRECIO DE VENTA"));
            cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(cell4);

            PdfPCell cell5 = new PdfPCell(new Phrase("MARGEN BRUTO"));
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(cell5);

            PdfPCell cell6 = new PdfPCell(new Phrase("TOTAL DEL PERIODO"));
            cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(cell6);

            for(int i =0 ; i<4;i++){
                tabla.addCell("Nombre del producto "+(i+1));
                tabla.addCell("4");
                tabla.addCell("35280");
                tabla.addCell("40000");
                tabla.addCell("0.118");
                tabla.addCell("160000");
            }

            tabla.setSpacingAfter(20);
            documento.add(tabla);
        } catch (DocumentException e){
            e.printStackTrace();
        }
    }


    public void ventas(Document documento){
        try {
            Paragraph cliente = new Paragraph("DETERMINACION DE LAS VENTAS\n\n",
                    FontFactory.getFont("arial",15, Font.BOLD, BaseColor.ORANGE));
            cliente.setAlignment(Element.ALIGN_CENTER);
            documento.add(cliente);
            PdfPTable tabla = new PdfPTable(3);
            tabla.setWidthPercentage(100);

            PdfPCell cell = new PdfPCell(new Phrase("PRODUCTOS"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(cell);

            PdfPCell cell2 = new PdfPCell(new Phrase("TOTAL DEL PERIODO"));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(cell2);

            PdfPCell cell3 = new PdfPCell(new Phrase("MARGEN BRUTO"));
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(cell3);

            for(int i =0 ; i<4;i++){
                tabla.addCell("Nombre del producto "+(i+1));
                tabla.addCell("16000");
                tabla.addCell("0.15");
            }

            Paragraph total = new Paragraph("TOTAL VENTAS:",
                    FontFactory.getFont("arial",10, Font.BOLD, BaseColor.BLACK));

            PdfPCell totalCell = new PdfPCell(new Phrase(total));
            totalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tabla.addCell(totalCell);
            tabla.addCell("160000");
            PdfPCell totalCell3 = new PdfPCell(new Phrase(""));
            totalCell3.setBackgroundColor(BaseColor.GRAY);
            tabla.addCell(totalCell3);


            Paragraph ventas = new Paragraph("COSTO DE PRODUCCION VENTAS:",
                    FontFactory.getFont("arial",10, Font.BOLD, BaseColor.BLACK));

            PdfPCell ventasCell = new PdfPCell(new Phrase(ventas));
            ventasCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tabla.addCell(ventasCell);
            tabla.addCell("141120");
            tabla.addCell("0.118");
            tabla.addCell(totalCell3);

            tabla.setSpacingAfter(20);
            documento.add(tabla);
        } catch (DocumentException e){
            e.printStackTrace();
        }
    }

    private boolean checkPermission(){
        boolean res;
        int permiso1 = ContextCompat.checkSelfPermission(getContext(), WRITE_EXTERNAL_STORAGE);
        int permiso2 = ContextCompat.checkSelfPermission(getContext(), READ_EXTERNAL_STORAGE);
       /* if(permiso1== PackageManager.PERMISSION_GRANTED && permiso2 == PackageManager.PERMISSION_GRANTED){
            res = true;
        }else{
            res = false;
        }*/
        res = permiso1== PackageManager.PERMISSION_GRANTED && permiso2==PackageManager.PERMISSION_GRANTED;
        return res;
    }
    private void requestPermission(){
        ActivityCompat.requestPermissions(getActivity(),new String[]{WRITE_EXTERNAL_STORAGE,READ_EXTERNAL_STORAGE},200);
    }
    @SuppressWarnings("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permisos, @NonNull int [] resultados){
        if(requestCode==200){
            if(resultados.length>0){
                boolean escritura = resultados[0]== PackageManager.PERMISSION_GRANTED;
                boolean lectura = resultados[1]== PackageManager.PERMISSION_GRANTED;
                if(escritura && lectura){
                    Toast.makeText(getContext(),"Permiso Concedido", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getContext(),"Permiso Denegado", Toast.LENGTH_LONG).show();
                    getActivity().finish();
                }
            }
        }
    }

}