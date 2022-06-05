package com.example.simuladorplandenegocios.Controlador;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DecimalFormat;

import com.example.simuladorplandenegocios.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GastosFijos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GastosFijos extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    //Servicios
    private EditText servicioLuz;
    private EditText servicioAgua;
    private EditText servicioTelefono;
    private EditText servicioCelular;
    //Mantenimientos
    private EditText mantenimiento1;
    private EditText mantenimiento2;
    private EditText mantenimiento3;
    private EditText mantenimiento4;
    //Otros
    private EditText salud;
    private EditText imprevistos;
    private TextView textoSalidaServicios;
    private TextView textoSalidaMantenimiento;
    private TextView textoSalidaGastoTotal;
    private double totalServicio;
    private double totalMantenimiento;
    private double totalGastos;
    //Boton Calculo valor servicio
    private Button calcular;
    private EditText impuestos;
    private EditText alimentacion;

    public GastosFijos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GastosFijos.
     */
    // TODO: Rename and change types and number of parameters
    public static GastosFijos newInstance(String param1, String param2) {
        GastosFijos fragment = new GastosFijos();
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
        View v = inflater.inflate(R.layout.fragment_gastos_fijos, container, false);

        calcular=(Button) v.findViewById(R.id.button2);

        //Servicios
        servicioLuz=(EditText) v.findViewById(R.id.servicioLuzInput);
        servicioAgua=(EditText) v.findViewById(R.id.servicioAguaInput);
        servicioTelefono=(EditText) v.findViewById(R.id.servicioTelefonoIput);
        servicioCelular=(EditText) v.findViewById(R.id.servicioCelularInput);
        //totales
        textoSalidaServicios=(TextView) v.findViewById(R.id.totalServiciosResultado);
        //Mantenimiento
        mantenimiento1=(EditText) v.findViewById(R.id.mm1Input);
        mantenimiento2=(EditText) v.findViewById(R.id.mm2Input);
        mantenimiento3=(EditText) v.findViewById(R.id.m3input);
        mantenimiento4=(EditText) v.findViewById(R.id.mm4put);
        //totales
        textoSalidaMantenimiento=(TextView) v.findViewById(R.id.totalMantenimientoResultado);
        //gastos aparte
        salud=(EditText) v.findViewById(R.id.eput);
        imprevistos=(EditText) v.findViewById((R.id.imprevistosinput));
        //Gastos iniciales
        impuestos=(EditText) v.findViewById(R.id.impuestoInput);
        alimentacion=(EditText) v.findViewById(R.id.alimentacionInput);
        //totales
        textoSalidaMantenimiento=(TextView) v.findViewById(R.id.totalMantenimientoResultado);
        textoSalidaGastoTotal = (TextView) v.findViewById(R.id.totalGastosResultado);
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularTotalServicio();
                calcularTotalMantenimiento();
                calcularTotalGastos();
            }
        });
        return v;
    }

    public void calcularTotalServicio(){
        DecimalFormat formato = new DecimalFormat("#.00");
        EditText[]arra = {servicioCelular,servicioLuz,servicioAgua,servicioTelefono};
        totalServicio = 0;
        for(EditText i : arra){
            if (!i.getText().toString().isEmpty())
                totalServicio = totalServicio+Double.parseDouble(i.getText().toString());
        }
        textoSalidaServicios.setText("Total en servicios es :  "+ formato.format(totalServicio)+"Bs");
    }

    public void calcularTotalMantenimiento(){
        DecimalFormat formato = new DecimalFormat("#.00");
        EditText[]arra = {mantenimiento1,mantenimiento2,mantenimiento3,mantenimiento4};
        totalMantenimiento = 0;
        for(EditText i : arra){
            if (!i.getText().toString().isEmpty())
                totalMantenimiento = totalMantenimiento+Double.parseDouble(i.getText().toString());
        }
        textoSalidaMantenimiento.setText("Total en mantenimiento es : "+ formato.format(totalMantenimiento)+"Bs");
    }

    public void calcularTotalGastos(){
        DecimalFormat formato = new DecimalFormat("#.00");
        EditText[]arra = {mantenimiento1,mantenimiento2,mantenimiento3,mantenimiento4,impuestos,alimentacion,servicioTelefono,servicioLuz,servicioAgua,servicioCelular,salud};
        totalGastos = 0;
        for(EditText i : arra){
            if (!i.getText().toString().isEmpty())
                totalGastos = totalGastos+Double.parseDouble(i.getText().toString());
        }
        textoSalidaGastoTotal.setText("Total en gastos es : "+ formato.format(totalGastos)+"Bs");
    }

}