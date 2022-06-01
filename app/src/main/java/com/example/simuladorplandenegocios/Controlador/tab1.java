package com.example.simuladorplandenegocios.Controlador;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.simuladorplandenegocios.R;
import com.example.simuladorplandenegocios.Vista.MainActivity;
import com.example.simuladorplandenegocios.Vista.MenuSimulacion;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link tab1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class tab1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Spinner spinner1,spinner2,spinner3;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText montoSolicitado;

    public tab1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment tab1.
     */
    // TODO: Rename and change types and number of parameters
    public static tab1 newInstance(String param1, String param2) {
        tab1 fragment = new tab1();
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
       View v = inflater.inflate(R.layout.fragment_tab1, container, false);
        spinner1 = (Spinner) v.findViewById(R.id.civilDeudorInput);
        String [] opciones = {"Soltero","Casado","Divorciado"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,opciones);
        spinner1.setAdapter(adapter);

        spinner2 = (Spinner) v.findViewById(R.id.actividadInput);
        String [] opciones2 = {"Productiva","Servicios"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,opciones2);
        spinner2.setAdapter(adapter2);

        spinner3 = (Spinner) v.findViewById(R.id.fomarpagoInput);
        String [] opciones3 = {"Mensual","Anual"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,opciones3);
        spinner3.setAdapter(adapter3);

        montoSolicitado = v.findViewById(R.id.montoInput);

        return v;

    }


}