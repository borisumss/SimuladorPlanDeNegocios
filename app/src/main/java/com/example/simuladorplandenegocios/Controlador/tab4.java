package com.example.simuladorplandenegocios.Controlador;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.simuladorplandenegocios.R;

import java.text.DecimalFormat;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link tab4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class tab4 extends Fragment{

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
    public tab4() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment tab4.
     */
    // TODO: Rename and change types and number of parameters
    public static tab4 newInstance(String param1, String param2) {
        tab4 fragment = new tab4();
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
        View s = inflater.inflate(R.layout.fragment_tab4, container, false);
        calcular=(Button)s.findViewById(R.id.button2);

        //Servicios
        servicioLuz=(EditText) s.findViewById(R.id.sInput);
        servicioAgua=(EditText) s.findViewById(R.id.servicioAguaInput);
        servicioTelefono=(EditText) s.findViewById(R.id.ServicioTelefono);
        servicioCelular=(EditText) s.findViewById(R.id.servicioCelularInput);
        //totales
        textoSalidaServicios=(TextView) s.findViewById(R.id.totservices);
        //Mantenimiento
        mantenimiento1=(EditText) s.findViewById(R.id.mm1Input);
        mantenimiento2=(EditText) s.findViewById(R.id.mm2Input);
        mantenimiento3=(EditText) s.findViewById(R.id.m3input);
        mantenimiento4=(EditText) s.findViewById(R.id.mm4put);
        //totales
        textoSalidaMantenimiento=(TextView) s.findViewById(R.id.textView32);
       //gastos aparte
        salud=(EditText)s.findViewById(R.id.eput);
        imprevistos=(EditText) s.findViewById((R.id.imprevistosinput));
        //Gastos iniciales
        impuestos=(EditText)s.findViewById(R.id.impuestoInput);
        alimentacion=(EditText)s.findViewById(R.id.alimentacionInput);
        //totales
        textoSalidaMantenimiento=(TextView) s.findViewById(R.id.textView32);
        textoSalidaGastoTotal = (TextView) s.findViewById(R.id.textView328);
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularTotalServicio();
                calcularTotalMantenimiento();
                calcularTotalGastos();
            }
        });
     return s;
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