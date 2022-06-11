package com.example.simuladorplandenegocios.Modelo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simuladorplandenegocios.Controlador.Triangular;
import com.example.simuladorplandenegocios.R;
import com.google.android.material.textfield.TextInputEditText;

import java.io.Serializable;

public class Simulacion extends Fragment implements View.OnClickListener {

    private EditText t1;
    private TextInputEditText vista1;
    private Button botonCorrerSimulacion;
    private TextView atrac, via;
    private ProgressBar progressBar;

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.progressBar);
        t1 = view.findViewById(R.id.nombreSimuInput);
        botonCorrerSimulacion = view.findViewById(R.id.correrSimu);
        botonCorrerSimulacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String resultado = t1.getText().toString();
                    Bundle bundle= new Bundle();
                    bundle.putString("NombreProyecto",String.valueOf(resultado));
                    getParentFragmentManager().setFragmentResult("nombre",bundle);

                    progressBar.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), "Generando Resultados", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), "Generando Resultados", Toast.LENGTH_LONG).show();

                    Triangular triangular = new Triangular(resultado);
                    atrac =(TextView) getActivity().findViewById(R.id.atractivoInput);
                    via =(TextView) getActivity().findViewById(R.id.viabilidadInput);
                    triangular.ejecutarSimulacion(atrac,via,progressBar);


                    Bundle bundle2= new Bundle();
                    bundle2.putSerializable("Triangular",triangular);
                    getParentFragmentManager().setFragmentResult("resultados",bundle2);

                }catch (Exception e){
                    Toast.makeText(getContext(), "Algo salio mal, intente nuevamente", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override

    public void onClick(View v) {

    }

}