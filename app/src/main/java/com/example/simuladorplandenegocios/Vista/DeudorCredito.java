package com.example.simuladorplandenegocios.Vista;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simuladorplandenegocios.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DeudorCredito#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeudorCredito extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Spinner spinner1,spinner2,spinner3;
    // TODO: Rename and change types of parameters
    private TextView interes, interesLabel, cuota,cuotaLabel;
    private double monto,intereses,cuotaFinal;
    private int plazo;
    private Button calcular;
    EditText montoSolicitado, plazos;

    public DeudorCredito() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DeudorCredito.
     */
    // TODO: Rename and change types and number of parameters
    public static DeudorCredito newInstance(String param1, String param2) {
        DeudorCredito fragment = new DeudorCredito();
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
        View v = inflater.inflate(R.layout.fragment_deudor_credito, container, false);
        spinner1 = (Spinner) v.findViewById(R.id.civilDeudorInput);
        String [] opciones = {"Soltero","Casado","Divorciado"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,opciones);
        spinner1.setAdapter(adapter);


        spinner2 = (Spinner) v.findViewById(R.id.actividadInput);
        String [] opciones2 = {"Productiva","Servicios"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,opciones2);
        spinner2.setAdapter(adapter2);

        spinner3 = (Spinner) v.findViewById(R.id.formaPagoInput);
        String [] opciones3 = {"Mensual","Anual"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,opciones3);
        spinner3.setAdapter(adapter3);

        montoSolicitado = (EditText) v.findViewById(R.id.montoInput);

        plazos = (EditText) v.findViewById(R.id.plazoInput);

        interes = (TextView)  v.findViewById(R.id.interesInput);
        interes.setVisibility(View.INVISIBLE);

        interesLabel = (TextView)   v.findViewById(R.id.interesLabel);
        interesLabel.setVisibility(View.INVISIBLE);

        cuota = (TextView)   v.findViewById(R.id.cuotaInput);
        cuota.setVisibility(View.INVISIBLE);

        cuotaLabel = (TextView)   v.findViewById(R.id.cuotaLabel);
        cuotaLabel.setVisibility(View.INVISIBLE);

        calcular = (Button) v.findViewById(R.id.buttonCalc);
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    monto = Double.parseDouble(montoSolicitado.getText().toString());
                    plazo = Integer.parseInt(plazos.getText().toString());
                    String aux = spinner2.getSelectedItem().toString();
                    if(aux.equals("Productiva")){
                        interes.setText("7.00");
                    }else{
                        interes.setText("11.50");
                    }
                    intereses = Double.parseDouble(interes.getText().toString())/100.0;
                    intereses=Math.round(intereses*100.0)/100.0;
                    cuotaLabel.setVisibility(View.VISIBLE);
                    cuota.setVisibility(View.VISIBLE);
                    interesLabel.setVisibility(View.VISIBLE);
                    interes.setVisibility(View.VISIBLE);

                    cuotaFinal = (monto/(double)plazo)+(monto/(double)plazo)*intereses;
                    cuotaFinal=Math.round(cuotaFinal*100.0)/100.0;
                    cuota.setText(cuotaFinal+"");

                }catch (Exception e){
                    Toast.makeText(getContext(), "Asegurese de llenar todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return v;

    }

    public void change(View view){
        /*monto = Double.parseDouble(montoSolicitado.getText().toString());
        plazo = Integer.parseInt(plazos.getText().toString());*/
        String aux = spinner2.getSelectedItem().toString();
        if(aux.equals("Productiva")){
            interes.setText("5.00");
        }else{
            interes.setText("7.5");
        }
        /* intereses = Double.parseDouble(interes.getText().toString())/100.0;*/
        cuotaLabel.setVisibility(View.VISIBLE);
        cuota.setVisibility(View.VISIBLE);
        interesLabel.setVisibility(View.VISIBLE);
        interes.setVisibility(View.VISIBLE);

       /* cuotaFinal = (monto/(double)plazo)+(monto/(double)plazo)*intereses;
        cuota.setText(cuotaFinal+"");*/
    }


}