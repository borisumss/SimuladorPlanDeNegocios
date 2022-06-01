package com.example.simuladorplandenegocios.Controlador;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.simuladorplandenegocios.R;

import java.text.DecimalFormat;

public class tab2 extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    //    boton calcular
    private Button calcular;
    //    aporte propio
    private EditText efectivoAP;
    private EditText manoObraAP;
    private EditText materiaPrimaAP;
    private EditText promocionAP;
    private EditText infraestructuraAP;
    private EditText maquinariaAP;
    private EditText legalAP;
    private double totalAP;
    private TextView totalA;
    //    requerimientos
    private EditText gastosR;
    private EditText materiaPrimaR;
    private EditText promocionR;
    private EditText infraestructuraR;
    private EditText maquinariaR;
    private EditText legalR;
    private double totalR;
    private TextView totalReq;

    //    totales
    private TextView totalProyecto;
    private TextView totalAportePropio;
    private TextView porcentajeAportePropio;
    private TextView condicionAP;
    private TextView montoSolicitado;
    private TextView montoFinanciar;
    private TextView condicionMonto;
    Double totalProy;//totalAP+totalR-efectivo
    //totalAP es solo el aporte total
    //el porcentaje de aporte propio solo es totalAP/totalproyecto ojo con 0/0
    //condicion AP si porcentaje es menor a 0.1 "no cumple con aporte propio minimo del 10%"; si cumple con aporte propio
    //monto solicitado(de otra tabla)
    //monto a financiar = totalReq - efectivo
    //condicion monto a financiar correcto ->    si monto solicitado = monto a financiar: "monto a fnianciar correcto"; monto a financiar distinto al solicitado

    public tab2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment tab2.
     */
    // TODO: Rename and change types and number of parameters
    public static tab2 newInstance(String param1, String param2) {
        tab2 fragment = new tab2();
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
        View v = inflater.inflate(R.layout.fragment_tab2, container, false);

        calcular = (Button) v.findViewById(R.id.calcular);

        //Aporte propio
        efectivoAP = (EditText) v.findViewById(R.id.numEfectivoAP);
        manoObraAP = (EditText) v.findViewById(R.id.numManoAP);
        materiaPrimaAP = (EditText) v.findViewById(R.id.numMateriaAP);
        promocionAP = (EditText) v.findViewById(R.id.numReqAP);
        infraestructuraAP = (EditText) v.findViewById(R.id.numInfraAP);
        maquinariaAP = (EditText) v.findViewById(R.id.numMaquiAP);
        legalAP = (EditText) v.findViewById(R.id.numLegalAP);
        //total ap
        totalA = (TextView) v.findViewById(R.id.totalAP);
        //Requerimiento
        gastosR = (EditText) v.findViewById(R.id.gastosR);
        materiaPrimaR = (EditText) v.findViewById(R.id.numMateriaR);
        promocionR = (EditText) v.findViewById(R.id.numReqR);
        infraestructuraR = (EditText) v.findViewById(R.id.numInfraR);
        maquinariaR = (EditText) v.findViewById(R.id.numMaquiR);
        legalR = (EditText) v.findViewById(R.id.numLegalR);
        //total R
        totalReq = (TextView) v.findViewById(R.id.totalR);
        //totales
        totalProyecto = (TextView) v.findViewById(R.id.totalProyecto);
        totalAportePropio = (TextView) v.findViewById(R.id.aportePropio);
        porcentajeAportePropio = (TextView) v.findViewById(R.id.aportePropioPorc);
        condicionAP = (TextView) v.findViewById(R.id.condicionAP);
        montoSolicitado = (TextView) v.findViewById(R.id.montoSolicitado);
        montoFinanciar = (TextView) v.findViewById(R.id.montoFinanciar);
        condicionMonto = (TextView) v.findViewById(R.id.condicionMonto);

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcularTotalAP();
                calcularTotalR();
                calcularTotales();
            }
        });

        return v;
    }

    private void calcularTotalAP() {
        DecimalFormat formato = new DecimalFormat("#.00");
        EditText[] array = {efectivoAP, manoObraAP, materiaPrimaAP, promocionAP, infraestructuraAP, maquinariaAP, legalAP};
        totalAP = 0;
        for (EditText i : array) {
            if (!i.getText().toString().isEmpty())
                totalAP += Double.parseDouble(i.getText().toString());
        }
        totalA.setText("TOTAL Aporte Propio " + formato.format(totalAP) + " Bs");
    }

    private void calcularTotalR() {
        DecimalFormat formato = new DecimalFormat("#.00");
        EditText[] array = {gastosR, materiaPrimaR, promocionR, infraestructuraR, maquinariaR, legalR};
        totalR = 0;
        for (EditText i : array) {
            if (!i.getText().toString().isEmpty())
                totalR += Double.parseDouble(i.getText().toString());
        }
        totalReq.setText("TOTAL Requerimiento " + formato.format(totalR) + " Bs");
    }

    private void calcularTotales() {

        DecimalFormat formato = new DecimalFormat("#.00");

        if (efectivoAP.getText().toString().isEmpty())
            totalProy = totalAP + totalR;
        else
            totalProy = totalAP + totalR - Double.parseDouble(efectivoAP.getText().toString());
        totalProyecto.setText("TOTAL PROYECTO " + formato.format(totalProy) + " Bs");

        totalAportePropio.setText("TOTAL APORTE PROPIO " + formato.format(totalAP) + " Bs");

        if (totalProy != 0) {
            porcentajeAportePropio.setText("% APORTE PROPIO " + formato.format(((totalAP / totalProy)) * 100) + "%");
            if (totalAP / totalProy < 0.1)
                condicionAP.setText("NO CUMPLE con aporte propio minimo del 10%");
            else {
                condicionAP.setText("SI CUMPLE con aporte propio");
            }
        } else {
            porcentajeAportePropio.setText("% APORTE PROPIO " + formato.format(0) + "%");
            condicionAP.setText("NO CUMPLE con aporte propio minimo del 10%");
        }

        montoSolicitado.setText("MONTO SOLICITADO " + formato.format(0) + " Bs");//extraer de otra tabla


        if (!efectivoAP.getText().toString().isEmpty())
            montoFinanciar.setText("MONTO A FINANCIAR " + formato.format(totalR - Double.parseDouble(efectivoAP.getText().toString())) + "Bs");
        else
            montoFinanciar.setText("MONTO A FINANCIAR " + formato.format(totalR) + "Bs");


        if (montoSolicitado.getText().toString().equals(montoFinanciar.getText().toString()))
            condicionMonto.setText("MONTO A FINANCIAR CORRECTO");
        else
            condicionMonto.setText("MONTO A FINANCIAR DISTINTO AL SOLICITADO");

    }

}