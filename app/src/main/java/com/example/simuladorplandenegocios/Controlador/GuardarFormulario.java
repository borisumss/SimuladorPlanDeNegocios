package com.example.simuladorplandenegocios.Controlador;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.simuladorplandenegocios.R;

public class GuardarFormulario extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Spinner spinner1,spinner2,spinner3;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GuardarFormulario() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GuardarFormulario.
     */
    // TODO: Rename and change types and number of parameters
    public static GuardarFormulario newInstance(String param1, String param2) {
        GuardarFormulario fragment = new GuardarFormulario();
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
        View v = inflater.inflate(R.layout.fragment_guardar_formulario, container, false);

            //AQUI ENTRA TODOS LOS GETS PARA LUEGO MANDARLO A 'FirebaseFormulario' Y QUE LO GUARDE

        return v;
    }
}
