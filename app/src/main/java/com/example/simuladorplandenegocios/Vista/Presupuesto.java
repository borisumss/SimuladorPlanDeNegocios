package com.example.simuladorplandenegocios.Vista;

import android.graphics.Color;
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

public class Presupuesto extends Fragment {

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
    private TextView totalAPResultado;

    //    requerimientos
    private EditText gastosR;
    private EditText materiaPrimaR;
    private EditText promocionR;
    private EditText infraestructuraR;
    private EditText maquinariaR;
    private EditText legalR;
    private double totalR;
    private TextView totalReq;
    private TextView totalRResultado;

    //    totales
    private TextView totalProyecto;
    private TextView totalAportePropio;
    private TextView porcentajeAportePropio;
    private TextView condicionAP;
    private TextView montoSolicitado;
    private double montoSol;
    private TextView montoFinanciar;
    private double montoFin;
    private TextView condicionMonto;
    private EditText montoOtro;

    private TextView totalProyectoResultado,totalAportePropioResultado;
    private TextView porcentajeAportePropioResultado,montoSolicitadoResultado,montoFinanciarResultado;


    Double totalProy;//totalAP+totalR-efectivo
    //totalAP es solo el aporte total
    //el porcentaje de aporte propio solo es totalAP/totalproyecto ojo con 0/0
    //condicion AP si porcentaje es menor a 0.1 "no cumple con aporte propio minimo del 10%"; si cumple con aporte propio
    //monto solicitado(de otra tabla)
    //monto a financiar = totalReq - efectivo
    //condicion monto a financiar correcto ->    si monto solicitado = monto a financiar: "monto a fnianciar correcto"; monto a financiar distinto al solicitado

    public Presupuesto() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Presupuesto.
     */
    // TODO: Rename and change types and number of parameters
    public static Presupuesto newInstance(String param1, String param2) {
        Presupuesto fragment = new Presupuesto();
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
        View v = inflater.inflate(R.layout.fragment_presupuesto, container, false);

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
        totalAPResultado = (TextView) v.findViewById(R.id.totalAPResultado);
        //Requerimiento
        gastosR = (EditText) v.findViewById(R.id.gastosR);
        materiaPrimaR = (EditText) v.findViewById(R.id.numMateriaR);
        promocionR = (EditText) v.findViewById(R.id.numReqR);
        infraestructuraR = (EditText) v.findViewById(R.id.numInfraR);
        maquinariaR = (EditText) v.findViewById(R.id.numMaquiR);
        legalR = (EditText) v.findViewById(R.id.numLegalR);
        //total R
        totalReq = (TextView) v.findViewById(R.id.totalR);
        totalRResultado = (TextView) v.findViewById(R.id.totalRResultado);
        //totales
        totalProyecto = (TextView) v.findViewById(R.id.totalProyecto);
        totalAportePropio = (TextView) v.findViewById(R.id.aportePropio);
        porcentajeAportePropio = (TextView) v.findViewById(R.id.aportePropioPorc);
        condicionAP = (TextView) v.findViewById(R.id.condicionAP);
        montoSolicitado = (TextView) v.findViewById(R.id.montoSolicitado);
        montoFinanciar = (TextView) v.findViewById(R.id.montoFinanciar);
        condicionMonto = (TextView) v.findViewById(R.id.condicionMonto);
        DecimalFormat formato = new DecimalFormat("#.00");

        totalProyectoResultado = (TextView) v.findViewById(R.id.totalProyectoResultado);
        totalAportePropioResultado = (TextView) v.findViewById(R.id.totalAportePropioResultado);
        porcentajeAportePropioResultado = (TextView) v.findViewById(R.id.porcentajeAportePropioResultado);
        montoSolicitadoResultado = (TextView) v.findViewById(R.id.montoSolicitadoResultado);
        montoFinanciarResultado = (TextView) v.findViewById(R.id.montoFinanciarResultado);

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcularTotalAP();
                calcularTotalR();
                calcularTotales();
                //no funciona obtener montoSolicitado dessde DeudorCredito
                View viewtab1 = inflater.inflate(R.layout.fragment_deudor_credito, container, false);
                montoOtro = (EditText) getActivity().findViewById(R.id.montoInput);

                if (!montoOtro.getText().toString().isEmpty()) {
                    montoSol = Double.parseDouble(montoOtro.getText().toString());
                    montoSolicitado.setText("MONTO SOLICITADO ");//extraer de otra tabla
                    montoSolicitadoResultado.setText(formato.format(montoSol));
                }
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
        totalA.setText("TOTAL Aporte Propio ");
        totalAPResultado.setText(formato.format(totalAP));
    }

    private void calcularTotalR() {
        DecimalFormat formato = new DecimalFormat("#.00");
        EditText[] array = {gastosR, materiaPrimaR, promocionR, infraestructuraR, maquinariaR, legalR};
        totalR = 0;
        for (EditText i : array) {
            if (!i.getText().toString().isEmpty())
                totalR += Double.parseDouble(i.getText().toString());
        }
        totalReq.setText("TOTAL Requerimiento ");
        totalRResultado.setText(formato.format(totalR));
    }

    private void calcularTotales() {

        DecimalFormat formato = new DecimalFormat("#.00");

        if (efectivoAP.getText().toString().isEmpty())
            totalProy = totalAP + totalR;
        else
            totalProy = totalAP + totalR - Double.parseDouble(efectivoAP.getText().toString());
        totalProyecto.setText("TOTAL PROYECTO ");
        totalProyectoResultado.setText(formato.format(totalProy));




        totalAportePropio.setText("TOTAL APORTE PROPIO ");
        totalAportePropioResultado.setText(formato.format(totalAP));

        if (totalProy != 0) {
            porcentajeAportePropio.setText("% APORTE PROPIO ");
            porcentajeAportePropioResultado.setText(formato.format(((totalAP / totalProy)) * 100));
            if (totalAP / totalProy < 0.1) {
                condicionAP.setText("NO CUMPLE con aporte propio minimo del 10%");
                condicionAP.setTextColor(Color.RED);
            }else {
                condicionAP.setText("SI CUMPLE con aporte propio");
                condicionAP.setTextColor(Color.GREEN);
            }
        } else {
            porcentajeAportePropio.setText("% APORTE PROPIO ");
            porcentajeAportePropioResultado.setText(formato.format(0));
            condicionAP.setText("NO CUMPLE con aporte propio minimo del 10%");
            condicionAP.setTextColor(Color.RED);
        }

        if (!efectivoAP.getText().toString().isEmpty()) {
            montoFin = totalR - Double.parseDouble(efectivoAP.getText().toString());
            montoFinanciar.setText("MONTO A FINANCIAR ");
            montoFinanciarResultado.setText(formato.format(montoFin));
        }else {
            montoFin = 0;
            montoFinanciar.setText("MONTO A FINANCIAR ");
            montoFinanciarResultado.setText(formato.format(totalR));
        }


        if (montoFin==montoSol) {
            condicionMonto.setText("MONTO A FINANCIAR CORRECTO");
            condicionMonto.setTextColor(Color.GREEN);
        } else {
            condicionMonto.setText("MONTO A FINANCIAR DISTINTO AL SOLICITADO");
            condicionMonto.setTextColor(Color.RED);
        }
    }

}