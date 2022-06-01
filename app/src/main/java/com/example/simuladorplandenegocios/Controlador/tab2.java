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
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                condicionMonto = (TextView)v.findViewById(R.id.condicionMonto);
                condicionMonto.setText("holaaaa");
            }
        });

        return v;
    }

}