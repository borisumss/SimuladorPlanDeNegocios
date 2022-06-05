package com.example.simuladorplandenegocios.Controlador;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.simuladorplandenegocios.Modelo.Credito;
import com.example.simuladorplandenegocios.Modelo.Deudor;
import com.example.simuladorplandenegocios.Modelo.DeudorDatosCredito;
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

    private Button guardarFormulario;

    private EditText nombreInput,apellidoInput;
    private Spinner estadoCivilInput;
    private EditText CIInput,extensionInput,telefonoInput,edadInput;

    private Spinner actividadInput;
    private EditText montoSolicitadoInput;
    private Spinner formaPagoInput;
    private EditText plazoInput;
    private TextView cuotaResultado,interesResultado;

    private String nombreDeudor,apellidoDeudor,estadoCivilDeudor,CIDeudor,extensionCIDeudor,telefonoDeudor,edadDedudor;

    private String actividadCredito;
    private float montoSolicitadoCredito;
    private String formaPagoCredito;
    private int plazoCredito;
    private float cuotaCredito;
    private float interesCredito;

    private EditText nombrePlanInput;
    private String nombrePlan;
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
        guardarFormulario = (Button) v.findViewById(R.id.guardarFormularioButton);

            //AQUI ENTRA TODOS LOS GETS PARA LUEGO MANDARLO A 'FirebaseFormulario' Y QUE LO GUARDE
            //DATOS DEL DEUDOR Y CREDITO

        /*nombreInput = (EditText) getActivity().findViewById(R.id.nombreDeudorInput);
        apellidoInput = (EditText) getActivity().findViewById(R.id.apellidoDeudorInput);
        estadoCivilInput = (Spinner) getActivity().findViewById(R.id.civilDeudorInput);
        CIInput = (EditText) getActivity().findViewById(R.id.ciDeudorInput);
        extensionInput = (EditText) getActivity().findViewById(R.id.extDeudorInput);
        telefonoInput = (EditText) getActivity().findViewById(R.id.tefDeudorInput);
        edadInput = (EditText) getActivity().findViewById(R.id.edadDeudorInput);

        actividadInput = (Spinner) getActivity().findViewById(R.id.actividadInput);
        montoSolicitadoInput = (EditText) getActivity().findViewById(R.id.montoInput);
        formaPagoInput = (Spinner) getActivity().findViewById(R.id.formaPagoInput);
        plazoInput = (EditText) getActivity().findViewById(R.id.plazoInput);
        cuotaResultado = (TextView) getActivity().findViewById(R.id.cuotaInput);
        interesResultado = (TextView) getActivity().findViewById(R.id.interesInput);

        nombreDeudor = nombreInput.getText().toString();
        apellidoDeudor = apellidoInput.getText().toString();
        estadoCivilDeudor = estadoCivilInput.getSelectedItem().toString();
        CIDeudor = CIInput.getText().toString();
        extensionCIDeudor = extensionInput.getText().toString();
        telefonoDeudor = telefonoInput.getText().toString();
        edadDedudor = edadInput.getText().toString();

        actividadCredito = actividadInput.getSelectedItem().toString();
        montoSolicitadoCredito = Float.parseFloat(montoSolicitadoInput.getText().toString());
        formaPagoCredito = formaPagoInput.getSelectedItem().toString();
        plazoCredito = Integer.parseInt(plazoInput.getText().toString());
        cuotaCredito = Float.parseFloat(cuotaResultado.getText().toString());
        interesCredito = Float.parseFloat(interesResultado.getText().toString());

        Deudor deudor = new Deudor(nombreDeudor,apellidoDeudor,estadoCivilDeudor,CIDeudor,extensionCIDeudor,telefonoDeudor,edadDedudor);
        Credito credito = new Credito(actividadCredito,montoSolicitadoCredito,formaPagoCredito,plazoCredito,cuotaCredito,interesCredito);
        DeudorDatosCredito deudorDatosCredito = new DeudorDatosCredito(deudor,credito);*/

        nombrePlanInput = (EditText) getActivity().findViewById(R.id.nombrePlanInput);
        nombrePlan = nombrePlanInput.getText().toString();
        System.out.println(nombrePlan);
        guardarFormulario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //View viewtab1 = inflater.inflate(R.layout.fragment_deudor_credito, container, false);
                //DeudorDatosCredito deudorDatosCredito = //extraerDatosDeudorCredito();
                System.out.println(nombrePlan);
                //FirebaseFormulario firebaseFormulario = new FirebaseFormulario(nombrePlan,deudorDatosCredito);
                //firebaseFormulario.guardarFormularioFirebase();
            }
        });

        return v;
    }

    /*public DeudorDatosCredito extraerDatosDeudorCredito(){

        apellidoInput = (EditText) getActivity().findViewById(R.id.apellidoDeudorInput);
        estadoCivilInput = (Spinner) getActivity().findViewById(R.id.civilDeudorInput);
        CIInput = (EditText) getActivity().findViewById(R.id.ciDeudorInput);
        extensionInput = (EditText) getActivity().findViewById(R.id.extDeudorInput);
        telefonoInput = (EditText) getActivity().findViewById(R.id.tefDeudorInput);
        edadInput = (EditText) getActivity().findViewById(R.id.edadDeudorInput);

        actividadInput = (Spinner) getActivity().findViewById(R.id.actividadInput);
        montoSolicitadoInput = (EditText) getActivity().findViewById(R.id.montoInput);
        formaPagoInput = (Spinner) getActivity().findViewById(R.id.formaPagoInput);
        plazoInput = (EditText) getActivity().findViewById(R.id.plazoInput);
        cuotaResultado = (TextView) getActivity().findViewById(R.id.cuotaInput);
        interesResultado = (TextView) getActivity().findViewById(R.id.interesInput);

        nombreDeudor = nombreInput.getText().toString();
        apellidoDeudor = apellidoInput.getText().toString();
        estadoCivilDeudor = estadoCivilInput.getSelectedItem().toString();
        CIDeudor = CIInput.getText().toString();
        extensionCIDeudor = extensionInput.getText().toString();
        telefonoDeudor = telefonoInput.getText().toString();
        edadDedudor = edadInput.getText().toString();

        actividadCredito = actividadInput.getSelectedItem().toString();
        montoSolicitadoCredito = Float.parseFloat(montoSolicitadoInput.getText().toString());
        formaPagoCredito = formaPagoInput.getSelectedItem().toString();
        plazoCredito = Integer.parseInt(plazoInput.getText().toString());
        cuotaCredito = Float.parseFloat(cuotaResultado.getText().toString());
        interesCredito = Float.parseFloat(interesResultado.getText().toString());

        Deudor deudor = new Deudor(nombreDeudor,apellidoDeudor,estadoCivilDeudor,CIDeudor,extensionCIDeudor,telefonoDeudor,edadDedudor);
        Credito credito = new Credito(actividadCredito,montoSolicitadoCredito,formaPagoCredito,plazoCredito,cuotaCredito,interesCredito);
        DeudorDatosCredito deudorDatosCredito = new DeudorDatosCredito(deudor,credito);

        return deudorDatosCredito;
    }*/
}
