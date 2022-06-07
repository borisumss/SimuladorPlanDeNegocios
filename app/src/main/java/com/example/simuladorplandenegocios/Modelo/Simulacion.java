package com.example.simuladorplandenegocios.Modelo;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.simuladorplandenegocios.R;
import com.google.android.material.textfield.TextInputEditText;

public class Simulacion extends Fragment implements View.OnClickListener{

    private EditText t1;
    private TextInputEditText vista1;
    private Button botonCorrerSimulacion;


    public Simulacion() {
        // Required empty public constructor
    }


    public static Simulacion newInstance(String param1, String param2) {
        Simulacion fragment = new Simulacion();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_simulacion, container, false);

    }



    @Override
    public void onClick(View v) {

    }
}