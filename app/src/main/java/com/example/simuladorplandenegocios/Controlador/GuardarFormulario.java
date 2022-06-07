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

import com.example.simuladorplandenegocios.Modelo.AportePropio;
import com.example.simuladorplandenegocios.Modelo.CostosProductos;
import com.example.simuladorplandenegocios.Modelo.Credito;
import com.example.simuladorplandenegocios.Modelo.Deudor;
import com.example.simuladorplandenegocios.Modelo.DeudorDatosCredito;
import com.example.simuladorplandenegocios.Modelo.Gastos;
import com.example.simuladorplandenegocios.Modelo.Mantenimiento;
import com.example.simuladorplandenegocios.Modelo.PresupuestoResumen;
import com.example.simuladorplandenegocios.Modelo.Producto;
import com.example.simuladorplandenegocios.Modelo.Requerimiento;
import com.example.simuladorplandenegocios.Modelo.Servicios;
import com.example.simuladorplandenegocios.R;



public class GuardarFormulario extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    //private Spinner spinner1,spinner2,spinner3;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button guardarDatosPersonaCredito;
    private Button guadarPresupuesto;
    private Button guardarProductos;
    private Button guardarGastos;

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


    private EditText efectivoAPInput,manoObraAPInput,materiaInsumosAPInput,equiposAPInput,infraestructuraAPInput,reqPromocionalesAPInput,reqLegalesAPInput;
    private EditText gastosOperativosRInput,materiaInsumosRInput,equiposRInput,infraestructuraRInput,reqPromocionalesRInput,reqLegalesRInput;
    private TextView totalAPResultado,totalRResultado;
    private TextView totalProyectoResultado,aportePropioResultado,porcentajeAportePropioResultado,montoSolicitadoResultado,montoFinanciarResultado;
    private TextView mensajeAportePropio,mensajeFinanciamiento;

    private float efectivoAP,manoObraAP,materiaInsumosAP,equiposAP,infraestructuraAP,reqPromocionalesAP,reqLegalesAP;
    private float gastosOperativosR,materiaInsumosR,equiposR,infraestructuraR,reqPromocionalesR,reqLegalesR;
    private float totalAP,totalR;
    private float totalProyecto,aportePropio,porcentajeAportePropio,montoSolicitado,montoFinanciar;
    private String msAportePropio,msFinanciamiento;

    private EditText p1NombreInput,p1TotalCostoProduccionUnidadInput,p1PrecioDeVentaPesimistaUnidadInput,p1PrecioDeVentaModeradoUnidadInput,p1PrecioDeVentaOptimistaUnidadInput,p1CantidadVendidaInput;
    private TextView p1MargenBrutoVentaUnidadResultado,p1TotalPeriodoResultado;

    private EditText p2NombreInput,p2TotalCostoProduccionUnidadInput,p2PrecioDeVentaPesimistaUnidadInput,p2PrecioDeVentaModeradoUnidadInput,p2PrecioDeVentaOptimistaUnidadInput,p2CantidadVendidaInput;
    private TextView p2MargenBrutoVentaUnidadResultado,p2TotalPeriodoResultado;

    private EditText p3NombreInput,p3TotalCostoProduccionUnidadInput,p3PrecioDeVentaPesimistaUnidadInput,p3PrecioDeVentaModeradoUnidadInput,p3PrecioDeVentaOptimistaUnidadInput,p3CantidadVendidaInput;
    private TextView p3MargenBrutoVentaUnidadResultado,p3TotalPeriodoResultado;

    private EditText p4NombreInput,p4TotalCostoProduccionUnidadInput,p4PrecioDeVentaPesimistaUnidadInput,p4PrecioDeVentaModeradoUnidadInput,p4PrecioDeVentaOptimistaUnidadInput,p4CantidadVendidaInput;
    private TextView p4MargenBrutoVentaUnidadResultado,p4TotalPeriodoResultado;

    private EditText impuestosInput,alimentacionInput,servicioLuzInput,servicioAguaInput,servicioTelefonoInput,servicioCelularInput,mantenimiento1Input,mantenimiento2Input,mantenimiento3Input,mantenimiento4Input,saludInput,imprevistosInput;
    private EditText mm1NombreInput,mm2NombreInput,mm3NombreInput,mm4NombreInput;
    private TextView totalServiciosResultado,totalMantenimientoResultado,totalGastosResultado;

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
        guardarDatosPersonaCredito = (Button) v.findViewById(R.id.guardarDatosPersonalesYCreditoButton);
        guadarPresupuesto = (Button) v.findViewById(R.id.guardarPresupuestoButton);
        guardarProductos = (Button) v.findViewById(R.id.guardarProductosButton);
        guardarGastos = (Button) v.findViewById(R.id.guardarGastosButton);

        //AQUI ENTRA TODOS LOS GETS PARA LUEGO MANDARLO A 'FirebaseFormulario' Y QUE LO GUARDE

        //DATOS DEL DEUDOR Y CREDITO
        nombreInput = (EditText) getActivity().findViewById(R.id.nombreDeudorInput);
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

        guardarDatosPersonaCredito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vista) {
                nombrePlanInput = (EditText) v.findViewById(R.id.nombrePlanInput);
                nombrePlan = nombrePlanInput.getText().toString();

                nombreDeudor = nombreInput.getText().toString();
                apellidoDeudor = apellidoInput.getText().toString();
                estadoCivilDeudor = estadoCivilInput.getSelectedItem().toString();
                CIDeudor = CIInput.getText().toString();
                extensionCIDeudor = extensionInput.getText().toString();
                telefonoDeudor = telefonoInput.getText().toString();
                edadDedudor = edadInput.getText().toString();

                actividadCredito = actividadInput.getSelectedItem().toString();
                System.out.println(actividadCredito);
                montoSolicitadoCredito = Float.parseFloat(montoSolicitadoInput.getText().toString());
                System.out.println(montoSolicitadoCredito);
                formaPagoCredito = formaPagoInput.getSelectedItem().toString();
                System.out.println(formaPagoCredito);
                plazoCredito = Integer.parseInt(plazoInput.getText().toString());
                System.out.println(plazoCredito);
                cuotaCredito = Float.parseFloat(cuotaResultado.getText().toString());
                System.out.println(cuotaCredito);
                interesCredito = Float.parseFloat(interesResultado.getText().toString());
                System.out.println(interesCredito);

                Deudor deudor = new Deudor(nombreDeudor,apellidoDeudor,estadoCivilDeudor,CIDeudor,extensionCIDeudor,telefonoDeudor,edadDedudor);
                Credito credito = new Credito(actividadCredito,montoSolicitadoCredito,formaPagoCredito,plazoCredito,cuotaCredito,interesCredito);
                DeudorDatosCredito deudorDatosCredito = new DeudorDatosCredito(deudor,credito);

                FirebaseFormulario firebaseFormulario = new FirebaseFormulario(nombrePlan);
                firebaseFormulario.guardarDeudorYCredito(deudorDatosCredito);
            }
        });


        //DATOS DEL PRESUPUESTO
        //APORTE PROPIO
        efectivoAPInput = (EditText) getActivity().findViewById(R.id.numEfectivoAP);
        manoObraAPInput = (EditText) getActivity().findViewById(R.id.numManoAP);
        materiaInsumosAPInput = (EditText) getActivity().findViewById(R.id.numMateriaAP);
        equiposAPInput = (EditText) getActivity().findViewById(R.id.numMaquiAP);
        infraestructuraAPInput = (EditText) getActivity().findViewById(R.id.numInfraAP);
        reqPromocionalesAPInput = (EditText) getActivity().findViewById(R.id.numReqAP);
        reqLegalesAPInput = (EditText) getActivity().findViewById(R.id.numLegalAP);
        //REQUERIMIENTO
        gastosOperativosRInput = (EditText) getActivity().findViewById(R.id.gastosR);
        materiaInsumosRInput = (EditText) getActivity().findViewById(R.id.numMateriaR);
        equiposRInput = (EditText) getActivity().findViewById(R.id.numMaquiR);
        infraestructuraRInput = (EditText) getActivity().findViewById(R.id.numInfraR);
        reqPromocionalesRInput = (EditText) getActivity().findViewById(R.id.numReqR);
        reqLegalesRInput = (EditText) getActivity().findViewById(R.id.numLegalR);
        //TOTALES PRESUPUESTO
        totalAPResultado = (TextView) getActivity().findViewById(R.id.totalAPResultado);
        totalRResultado = (TextView) getActivity().findViewById(R.id.totalRResultado);
        totalProyectoResultado = (TextView) getActivity().findViewById(R.id.totalProyectoResultado);
        aportePropioResultado = (TextView) getActivity().findViewById(R.id.totalAportePropioResultado);
        porcentajeAportePropioResultado = (TextView) getActivity().findViewById(R.id.porcentajeAportePropioResultado);
        montoSolicitadoResultado = (TextView) getActivity().findViewById(R.id.montoSolicitadoResultado);
        montoFinanciarResultado = (TextView) getActivity().findViewById(R.id.montoFinanciarResultado);
        //MENSAJES PRESUPUESTO
        mensajeAportePropio = (TextView) getActivity().findViewById(R.id.condicionAP);
        mensajeFinanciamiento = (TextView) getActivity().findViewById(R.id.condicionMonto);

        guadarPresupuesto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vista) {
                nombrePlanInput = (EditText) v.findViewById(R.id.nombrePlanInput);
                nombrePlan = nombrePlanInput.getText().toString();

                efectivoAP = Float.parseFloat(efectivoAPInput.getText().toString());
                manoObraAP = Float.parseFloat(manoObraAPInput.getText().toString());
                materiaInsumosAP = Float.parseFloat(materiaInsumosAPInput.getText().toString());
                equiposAP = Float.parseFloat(equiposAPInput.getText().toString());
                infraestructuraAP = Float.parseFloat(infraestructuraAPInput.getText().toString());
                reqPromocionalesAP = Float.parseFloat(reqPromocionalesAPInput.getText().toString());
                reqLegalesAP = Float.parseFloat(reqLegalesAPInput.getText().toString());
                //REQUERIMIENTO
                gastosOperativosR = Float.parseFloat(gastosOperativosRInput.getText().toString());
                materiaInsumosR = Float.parseFloat(materiaInsumosRInput.getText().toString());
                equiposR = Float.parseFloat(equiposRInput.getText().toString());
                infraestructuraR = Float.parseFloat(infraestructuraRInput.getText().toString());
                reqPromocionalesR = Float.parseFloat(reqPromocionalesRInput.getText().toString());
                reqLegalesR = Float.parseFloat(reqLegalesRInput.getText().toString());
                //TOTALES
                totalAP = Float.parseFloat(totalAPResultado.getText().toString().replace(',','.'));
                totalR = Float.parseFloat(totalRResultado.getText().toString().replace(',','.'));
                totalProyecto = Float.parseFloat(totalProyectoResultado.getText().toString().replace(',','.'));
                aportePropio = Float.parseFloat(aportePropioResultado.getText().toString().replace(',','.'));
                porcentajeAportePropio = Float.parseFloat(porcentajeAportePropioResultado.getText().toString().replace(',','.'));
                montoSolicitado = Float.parseFloat(montoSolicitadoResultado.getText().toString().replace(',','.'));
                montoFinanciar = Float.parseFloat(montoFinanciarResultado.getText().toString().replace(',','.'));
                //MENSAJES
                msAportePropio = mensajeAportePropio.getText().toString();
                msFinanciamiento = mensajeFinanciamiento.getText().toString();

                AportePropio AP = new AportePropio(efectivoAP,manoObraAP,materiaInsumosAP,reqPromocionalesAP,infraestructuraAP,equiposAP,reqLegalesAP);
                Requerimiento R = new Requerimiento(gastosOperativosR,materiaInsumosR,reqPromocionalesR,infraestructuraR,equiposR,reqLegalesR);
                PresupuestoResumen presupuestoResumen = new PresupuestoResumen(AP,R,msAportePropio,msFinanciamiento);
                presupuestoResumen.setTotalAportePropio(totalAP);
                presupuestoResumen.setTotalRequerimiento(totalR);
                presupuestoResumen.setTotalProyecto(totalProyecto);
                presupuestoResumen.setAportePropioCalculado(aportePropio);
                presupuestoResumen.setPorcentajeAportePropio(porcentajeAportePropio);
                presupuestoResumen.setMontoSolicitado(montoSolicitado);
                presupuestoResumen.setMontoFinanciado(montoFinanciar);

                FirebaseFormulario firebaseFormulario = new FirebaseFormulario(nombrePlan);
                firebaseFormulario.guardarPresupuestoResumen(presupuestoResumen);
            }
        });

        //COSTSO PRODUCTOS
        //PRODUCTO 1
        p1NombreInput = (EditText) getActivity().findViewById(R.id.producto1NombreInput);
        p1TotalCostoProduccionUnidadInput = (EditText) getActivity().findViewById(R.id.producto1TotalCostoProduccionUnidadInput);
        p1PrecioDeVentaPesimistaUnidadInput = (EditText) getActivity().findViewById(R.id.producto1PrecioDeVentaPesimistaUnidadInput);
        p1PrecioDeVentaModeradoUnidadInput = (EditText) getActivity().findViewById(R.id.producto1PrecioDeVentaModeradoUnidadInput);
        p1PrecioDeVentaOptimistaUnidadInput = (EditText) getActivity().findViewById(R.id.producto1PrecioDeVentaOptimistaUnidadInput);
        p1CantidadVendidaInput = (EditText) getActivity().findViewById(R.id.producto1CantidadVendidaInput);

        p1MargenBrutoVentaUnidadResultado = (TextView) getActivity().findViewById(R.id.producto1MargenBrutoVentaUnidadResultado);
        p1TotalPeriodoResultado = (TextView) getActivity().findViewById(R.id.producto1TotalPeriodoResultado);
        //PRODUCTO 2
        p2NombreInput = (EditText) getActivity().findViewById(R.id.producto2NombreInput);
        p2TotalCostoProduccionUnidadInput = (EditText) getActivity().findViewById(R.id.producto2TotalCostoProduccionUnidadInput);
        p2PrecioDeVentaPesimistaUnidadInput = (EditText) getActivity().findViewById(R.id.producto2PrecioDeVentaPesimistaUnidadInput);
        p2PrecioDeVentaModeradoUnidadInput = (EditText) getActivity().findViewById(R.id.producto2PrecioDeVentaModeradoUnidadInput);
        p2PrecioDeVentaOptimistaUnidadInput = (EditText) getActivity().findViewById(R.id.producto2PrecioDeVentaOptimistaUnidadInput);
        p2CantidadVendidaInput = (EditText) getActivity().findViewById(R.id.producto2CantidadVendidaInput);

        p2MargenBrutoVentaUnidadResultado = (TextView) getActivity().findViewById(R.id.producto2MargenBrutoVentaUnidadResultado);
        p2TotalPeriodoResultado = (TextView) getActivity().findViewById(R.id.producto2TotalPeriodoResultado);
        //PRODUCTO 3
        p3NombreInput = (EditText) getActivity().findViewById(R.id.producto3NombreInput);
        p3TotalCostoProduccionUnidadInput = (EditText) getActivity().findViewById(R.id.producto3TotalCostoProduccionUnidadInput);
        p3PrecioDeVentaPesimistaUnidadInput = (EditText) getActivity().findViewById(R.id.producto3PrecioDeVentaPesimistaUnidadInput);
        p3PrecioDeVentaModeradoUnidadInput = (EditText) getActivity().findViewById(R.id.producto3PrecioDeVentaModeradoUnidadInput);
        p3PrecioDeVentaOptimistaUnidadInput = (EditText) getActivity().findViewById(R.id.producto3PrecioDeVentaOptimistaUnidadInput);
        p3CantidadVendidaInput = (EditText) getActivity().findViewById(R.id.producto3CantidadVendidaInput);

        p3MargenBrutoVentaUnidadResultado = (TextView) getActivity().findViewById(R.id.producto3MargenBrutoVentaUnidadResultado);
        p3TotalPeriodoResultado = (TextView) getActivity().findViewById(R.id.producto3TotalPeriodoResultado);
        //PRODUCTO 4
        p4NombreInput = (EditText) getActivity().findViewById(R.id.producto4NombreInput);
        p4TotalCostoProduccionUnidadInput = (EditText) getActivity().findViewById(R.id.producto4TotalCostoProduccionUnidadInput);
        p4PrecioDeVentaPesimistaUnidadInput = (EditText) getActivity().findViewById(R.id.producto4PrecioDeVentaPesimistaUnidadInput);
        p4PrecioDeVentaModeradoUnidadInput = (EditText) getActivity().findViewById(R.id.producto4PrecioDeVentaModeradoUnidadInput);
        p4PrecioDeVentaOptimistaUnidadInput = (EditText) getActivity().findViewById(R.id.producto4PrecioDeVentaOptimistaUnidadInput);
        p4CantidadVendidaInput = (EditText) getActivity().findViewById(R.id.producto4CantidadVendidaInput);

        p4MargenBrutoVentaUnidadResultado = (TextView) getActivity().findViewById(R.id.producto4MargenBrutoVentaUnidadResultado);
        p4TotalPeriodoResultado = (TextView) getActivity().findViewById(R.id.producto4TotalPeriodoResultado);

        guardarProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vista) {
                nombrePlanInput = (EditText) v.findViewById(R.id.nombrePlanInput);
                nombrePlan = nombrePlanInput.getText().toString();

                Producto p1 = new Producto();
                Producto p2 = new Producto();
                Producto p3 = new Producto();
                Producto p4 = new Producto();
                //PRODUCTOS
                Producto[] productos = {p1,p2,p3,p4};
                EditText[] productoNombre = {p1NombreInput,p2NombreInput,p3NombreInput,p4NombreInput};
                EditText[] productoCostoProduccion = {p1TotalCostoProduccionUnidadInput,p2TotalCostoProduccionUnidadInput,p3TotalCostoProduccionUnidadInput,p4TotalCostoProduccionUnidadInput};
                EditText[] productoPrecioVentaPesimista = {p1PrecioDeVentaPesimistaUnidadInput,p2PrecioDeVentaPesimistaUnidadInput,p3PrecioDeVentaPesimistaUnidadInput,p4PrecioDeVentaPesimistaUnidadInput};
                EditText[] productoPrecioVentaModerado = {p1PrecioDeVentaModeradoUnidadInput,p2PrecioDeVentaModeradoUnidadInput,p3PrecioDeVentaModeradoUnidadInput,p4PrecioDeVentaModeradoUnidadInput};
                EditText[] productoPrecioVentaOptimista = {p1PrecioDeVentaOptimistaUnidadInput,p2PrecioDeVentaOptimistaUnidadInput,p3PrecioDeVentaOptimistaUnidadInput,p4PrecioDeVentaOptimistaUnidadInput};
                EditText[] productoCantidadVendida = {p1CantidadVendidaInput,p2CantidadVendidaInput,p3CantidadVendidaInput,p4CantidadVendidaInput};
                TextView[] productoMargenBrutoVentaUnidadResultado= {p1MargenBrutoVentaUnidadResultado,p2MargenBrutoVentaUnidadResultado,p3MargenBrutoVentaUnidadResultado,p4MargenBrutoVentaUnidadResultado};
                TextView[] productoTotalPeriodoResultado = {p1TotalPeriodoResultado,p2TotalPeriodoResultado,p3TotalPeriodoResultado,p4TotalPeriodoResultado};
                for(int i=0;i<4;i++) {
                    if (!productoNombre[i].getText().toString().isEmpty()) {
                        if (!productoCostoProduccion[i].getText().toString().isEmpty()) {
                            if (!productoPrecioVentaPesimista[i].getText().toString().isEmpty()) {
                                if (!productoPrecioVentaModerado[i].getText().toString().isEmpty()) {
                                    if (!productoPrecioVentaOptimista[i].getText().toString().isEmpty()) {
                                        if (!productoCantidadVendida[i].getText().toString().isEmpty()) {
                                            String pNombre = productoNombre[i].getText().toString();
                                            float pTotalCostoProduccionUnidad = Float.parseFloat(productoCostoProduccion[i].getText().toString());
                                            float pPrecioDeVentaPesimistaUnidad = Float.parseFloat(productoPrecioVentaPesimista[i].getText().toString());
                                            float pPrecioDeVentaModeradoUnidad = Float.parseFloat(productoPrecioVentaModerado[i].getText().toString());
                                            float pPrecioDeVentaOptimistaUnidad = Float.parseFloat(productoPrecioVentaOptimista[i].getText().toString());
                                            int pCantidadVendida = Integer.parseInt(productoCantidadVendida[i].getText().toString());
                                            float pMargenBrutoVentaUnidad = Float.parseFloat(productoMargenBrutoVentaUnidadResultado[i].getText().toString());
                                            float pTotalPeriodo = Float.parseFloat(productoTotalPeriodoResultado[i].getText().toString());
                                            productos[i].setNombre(pNombre);
                                            productos[i].setCostoProduccion(pTotalCostoProduccionUnidad);
                                            productos[i].setPrecioVentaPesimista(pPrecioDeVentaPesimistaUnidad);
                                            productos[i].setPrecioVentaModerado(pPrecioDeVentaModeradoUnidad);
                                            productos[i].setPrecioVentaOptimista(pPrecioDeVentaOptimistaUnidad);
                                            productos[i].setCantidadVendida(pCantidadVendida);
                                            productos[i].setMargenBrutoVenta(pMargenBrutoVentaUnidad);
                                            productos[i].setTotalPeriodo(pTotalPeriodo);
                                        } else {
                                            productos[i].setNombre("NINGUNO");
                                        }
                                    } else {
                                        productos[i].setNombre("NINGUNO");
                                    }
                                } else {
                                    productos[i].setNombre("NINGUNO");
                                }
                            } else {
                                productos[i].setNombre("NINGUNO");
                            }
                        } else {
                            productos[i].setNombre("NINGUNO");
                        }
                    } else {
                        productos[i].setNombre("NINGUNO");
                    }
                }

                CostosProductos costosProductos = new CostosProductos(productos[0],productos[1],productos[2],productos[3]);

                FirebaseFormulario firebaseFormulario = new FirebaseFormulario(nombrePlan);
                firebaseFormulario.guardarCostosProductos(costosProductos);
            }
        });

        //GASTOS FIJOS
        impuestosInput = (EditText) getActivity().findViewById(R.id.impuestoInput);
        alimentacionInput = (EditText) getActivity().findViewById(R.id.alimentacionInput);
        servicioLuzInput = (EditText) getActivity().findViewById(R.id.servicioLuzInput);
        servicioAguaInput = (EditText) getActivity().findViewById(R.id.servicioAguaInput);
        servicioTelefonoInput = (EditText) getActivity().findViewById(R.id.servicioTelefonoIput);
        servicioCelularInput = (EditText) getActivity().findViewById(R.id.servicioCelularInput);
        mm1NombreInput = (EditText) getActivity().findViewById(R.id.m1Input);
        mm2NombreInput = (EditText) getActivity().findViewById(R.id.m2Input);
        mm3NombreInput = (EditText) getActivity().findViewById(R.id.m3nput);
        mm4NombreInput = (EditText) getActivity().findViewById(R.id.mm4Input);
        mantenimiento1Input = (EditText) getActivity().findViewById(R.id.mm1Input);
        mantenimiento2Input = (EditText) getActivity().findViewById(R.id.mm2Input);
        mantenimiento3Input = (EditText) getActivity().findViewById(R.id.m3input);
        mantenimiento4Input = (EditText) getActivity().findViewById(R.id.mm4put);
        saludInput = (EditText) getActivity().findViewById(R.id.eput);
        imprevistosInput = (EditText) getActivity().findViewById(R.id.imprevistosinput);

        totalServiciosResultado = (TextView) getActivity().findViewById(R.id.totalServiciosRes);
        totalMantenimientoResultado = (TextView) getActivity().findViewById(R.id.totalMantenimientoRes);
        totalGastosResultado = (TextView) getActivity().findViewById(R.id.totalGastosRes);

        guardarGastos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vista) {
                nombrePlanInput = (EditText) v.findViewById(R.id.nombrePlanInput);
                nombrePlan = nombrePlanInput.getText().toString();
                ///DATOS EXTRAIDOS DEL INPUT

                //extraerDatosDeudorCredito();

                /*nombreDeudor = nombreInput.getText().toString();
                apellidoDeudor = apellidoInput.getText().toString();
                estadoCivilDeudor = estadoCivilInput.getSelectedItem().toString();
                CIDeudor = CIInput.getText().toString();
                extensionCIDeudor = extensionInput.getText().toString();
                telefonoDeudor = telefonoInput.getText().toString();
                edadDedudor = edadInput.getText().toString();*/

                /*actividadCredito = actividadInput.getSelectedItem().toString();
                System.out.println(actividadCredito);
                montoSolicitadoCredito = Float.parseFloat(montoSolicitadoInput.getText().toString());
                System.out.println(montoSolicitadoCredito);
                formaPagoCredito = formaPagoInput.getSelectedItem().toString();
                System.out.println(formaPagoCredito);
                plazoCredito = Integer.parseInt(plazoInput.getText().toString());
                System.out.println(plazoCredito);
                cuotaCredito = Float.parseFloat(cuotaResultado.getText().toString());
                System.out.println(cuotaCredito);
                interesCredito = Float.parseFloat(interesResultado.getText().toString());
                System.out.println(interesCredito);*/

                /*Deudor deudor = new Deudor(nombreDeudor,apellidoDeudor,estadoCivilDeudor,CIDeudor,extensionCIDeudor,telefonoDeudor,edadDedudor);
                Credito credito = new Credito(actividadCredito,montoSolicitadoCredito,formaPagoCredito,plazoCredito,cuotaCredito,interesCredito);
                DeudorDatosCredito deudorDatosCredito = new DeudorDatosCredito(deudor,credito);*/

                //EXTRACCION DE PRESUPUESTO
                //APORTE PROPIO
                /*efectivoAP = Float.parseFloat(efectivoAPInput.getText().toString());
                manoObraAP = Float.parseFloat(manoObraAPInput.getText().toString());
                materiaInsumosAP = Float.parseFloat(materiaInsumosAPInput.getText().toString());
                equiposAP = Float.parseFloat(equiposAPInput.getText().toString());
                infraestructuraAP = Float.parseFloat(infraestructuraAPInput.getText().toString());
                reqPromocionalesAP = Float.parseFloat(reqPromocionalesAPInput.getText().toString());
                reqLegalesAP = Float.parseFloat(reqLegalesAPInput.getText().toString());
                //REQUERIMIENTO
                gastosOperativosR = Float.parseFloat(gastosOperativosRInput.getText().toString());
                materiaInsumosR = Float.parseFloat(materiaInsumosRInput.getText().toString());
                equiposR = Float.parseFloat(equiposRInput.getText().toString());
                infraestructuraR = Float.parseFloat(infraestructuraRInput.getText().toString());
                reqPromocionalesR = Float.parseFloat(reqPromocionalesRInput.getText().toString());
                reqLegalesR = Float.parseFloat(reqLegalesRInput.getText().toString());
                //TOTALES
                totalAP = Float.parseFloat(totalAPResultado.getText().toString().replace(',','.'));
                totalR = Float.parseFloat(totalRResultado.getText().toString().replace(',','.'));
                totalProyecto = Float.parseFloat(totalProyectoResultado.getText().toString().replace(',','.'));
                aportePropio = Float.parseFloat(aportePropioResultado.getText().toString().replace(',','.'));
                porcentajeAportePropio = Float.parseFloat(porcentajeAportePropioResultado.getText().toString().replace(',','.'));
                montoSolicitado = Float.parseFloat(montoSolicitadoResultado.getText().toString().replace(',','.'));
                montoFinanciar = Float.parseFloat(montoFinanciarResultado.getText().toString().replace(',','.'));
                //MENSAJES
                msAportePropio = mensajeAportePropio.getText().toString();
                msFinanciamiento = mensajeFinanciamiento.getText().toString();

                AportePropio AP = new AportePropio(efectivoAP,manoObraAP,materiaInsumosAP,reqPromocionalesAP,infraestructuraAP,equiposAP,reqLegalesAP);
                Requerimiento R = new Requerimiento(gastosOperativosR,materiaInsumosR,reqPromocionalesR,infraestructuraR,equiposR,reqLegalesR);
                PresupuestoResumen presupuestoResumen = new PresupuestoResumen(AP,R,msAportePropio,msFinanciamiento);
                presupuestoResumen.setTotalAportePropio(totalAP);
                presupuestoResumen.setTotalRequerimiento(totalR);
                presupuestoResumen.setTotalProyecto(totalProyecto);
                presupuestoResumen.setAportePropioCalculado(aportePropio);
                presupuestoResumen.setPorcentajeAportePropio(porcentajeAportePropio);
                presupuestoResumen.setMontoSolicitado(montoSolicitado);
                presupuestoResumen.setMontoFinanciado(montoFinanciar);*/

                //PRODUCTOS COSTOS
                /*Producto p1 = new Producto();
                Producto p2 = new Producto();
                Producto p3 = new Producto();
                Producto p4 = new Producto();
                //PRODUCTOS
                Producto[] productos = {p1,p2,p3,p4};
                EditText[] productoNombre = {p1NombreInput,p2NombreInput,p3NombreInput,p4NombreInput};
                EditText[] productoCostoProduccion = {p1TotalCostoProduccionUnidadInput,p2TotalCostoProduccionUnidadInput,p3TotalCostoProduccionUnidadInput,p4TotalCostoProduccionUnidadInput};
                EditText[] productoPrecioVentaPesimista = {p1PrecioDeVentaPesimistaUnidadInput,p2PrecioDeVentaPesimistaUnidadInput,p3PrecioDeVentaPesimistaUnidadInput,p4PrecioDeVentaPesimistaUnidadInput};
                EditText[] productoPrecioVentaModerado = {p1PrecioDeVentaModeradoUnidadInput,p2PrecioDeVentaModeradoUnidadInput,p3PrecioDeVentaModeradoUnidadInput,p4PrecioDeVentaModeradoUnidadInput};
                EditText[] productoPrecioVentaOptimista = {p1PrecioDeVentaOptimistaUnidadInput,p2PrecioDeVentaOptimistaUnidadInput,p3PrecioDeVentaOptimistaUnidadInput,p4PrecioDeVentaOptimistaUnidadInput};
                EditText[] productoCantidadVendida = {p1CantidadVendidaInput,p2CantidadVendidaInput,p3CantidadVendidaInput,p4CantidadVendidaInput};
                TextView[] productoMargenBrutoVentaUnidadResultado= {p1MargenBrutoVentaUnidadResultado,p2MargenBrutoVentaUnidadResultado,p3MargenBrutoVentaUnidadResultado,p4MargenBrutoVentaUnidadResultado};
                TextView[] productoTotalPeriodoResultado = {p1TotalPeriodoResultado,p2TotalPeriodoResultado,p3TotalPeriodoResultado,p4TotalPeriodoResultado};
                for(int i=0;i<4;i++) {
                    if (!productoNombre[i].getText().toString().isEmpty()) {
                        if (!productoCostoProduccion[i].getText().toString().isEmpty()) {
                            if (!productoPrecioVentaPesimista[i].getText().toString().isEmpty()) {
                                if (!productoPrecioVentaModerado[i].getText().toString().isEmpty()) {
                                    if (!productoPrecioVentaOptimista[i].getText().toString().isEmpty()) {
                                        if (!productoCantidadVendida[i].getText().toString().isEmpty()) {
                                            String pNombre = productoNombre[i].getText().toString();
                                            float pTotalCostoProduccionUnidad = Float.parseFloat(productoCostoProduccion[i].getText().toString());
                                            float pPrecioDeVentaPesimistaUnidad = Float.parseFloat(productoPrecioVentaPesimista[i].getText().toString());
                                            float pPrecioDeVentaModeradoUnidad = Float.parseFloat(productoPrecioVentaModerado[i].getText().toString());
                                            float pPrecioDeVentaOptimistaUnidad = Float.parseFloat(productoPrecioVentaOptimista[i].getText().toString());
                                            int pCantidadVendida = Integer.parseInt(productoCantidadVendida[i].getText().toString());
                                            float pMargenBrutoVentaUnidad = Float.parseFloat(productoMargenBrutoVentaUnidadResultado[i].getText().toString());
                                            float pTotalPeriodo = Float.parseFloat(productoTotalPeriodoResultado[i].getText().toString());
                                            productos[i].setNombre(pNombre);
                                            productos[i].setCostoProduccion(pTotalCostoProduccionUnidad);
                                            productos[i].setPrecioVentaPesimista(pPrecioDeVentaPesimistaUnidad);
                                            productos[i].setPrecioVentaModerado(pPrecioDeVentaModeradoUnidad);
                                            productos[i].setPrecioVentaOptimista(pPrecioDeVentaOptimistaUnidad);
                                            productos[i].setCantidadVendida(pCantidadVendida);
                                            productos[i].setMargenBrutoVenta(pMargenBrutoVentaUnidad);
                                            productos[i].setTotalPeriodo(pTotalPeriodo);
                                        } else {
                                            productos[i].setNombre("NINGUNO");
                                        }
                                    } else {
                                        productos[i].setNombre("NINGUNO");
                                    }
                                } else {
                                    productos[i].setNombre("NINGUNO");
                                }
                            } else {
                                productos[i].setNombre("NINGUNO");
                            }
                        } else {
                            productos[i].setNombre("NINGUNO");
                        }
                    } else {
                        productos[i].setNombre("NINGUNO");
                    }
                }
                CostosProductos costosProductos = new CostosProductos(productos[0],productos[1],productos[2],productos[3]);
                */
                //GASTOS FIJOS
                Mantenimiento m1 = new Mantenimiento();
                Mantenimiento m2 = new Mantenimiento();
                Mantenimiento m3 = new Mantenimiento();
                Mantenimiento m4 = new Mantenimiento();
                Mantenimiento[] mantenimientos = {m1,m2,m3,m4};
                EditText[] mmNombre = {mm1NombreInput,mm2NombreInput,mm3NombreInput,mm4NombreInput};
                EditText[] mmCosto = {mantenimiento1Input,mantenimiento2Input,mantenimiento3Input,mantenimiento4Input};
                for(int i=0;i<4;i++){
                    if (!mmNombre[i].getText().toString().isEmpty()) {
                        if (!mmCosto[i].getText().toString().isEmpty()) {
                            mantenimientos[i].setNombre(mmNombre[i].getText().toString());
                            float costoMantenimiento = Float.parseFloat(mmCosto[i].getText().toString().replace(',','.'));
                            mantenimientos[i].setCostoMantenimiento(costoMantenimiento);
                        }else {
                            mantenimientos[i].setNombre("NINGUNO");
                        }
                    }else {
                        mantenimientos[i].setNombre("NINGUNO");
                    }
                }
                Servicios servicios = new Servicios();
                if(!impuestosInput.getText().toString().isEmpty()){
                    float impuestos = Float.parseFloat(impuestosInput.getText().toString());
                    servicios.setImpuestos(impuestos);
                }
                if(!alimentacionInput.getText().toString().isEmpty()){
                    float alimentacion = Float.parseFloat(alimentacionInput.getText().toString());
                    servicios.setAlimentacion(alimentacion);
                }
                if(!servicioLuzInput.getText().toString().isEmpty()){
                    float servicioLuz = Float.parseFloat(servicioLuzInput.getText().toString());
                    servicios.setServicioLuz(servicioLuz);
                }
                if(!servicioAguaInput.getText().toString().isEmpty()){
                    float servicioAgua = Float.parseFloat(servicioAguaInput.getText().toString());
                    servicios.setServicioAgua(servicioAgua);
                }
                if(!servicioTelefonoInput.getText().toString().isEmpty()){
                    float servicioTelefono = Float.parseFloat(servicioTelefonoInput.getText().toString());
                    servicios.setServicioTelefono(servicioTelefono);
                }
                if(!servicioCelularInput.getText().toString().isEmpty()){
                    float servicioCelular = Float.parseFloat(servicioCelularInput.getText().toString());
                    servicios.setServicioCelular(servicioCelular);
                }
                if(!saludInput.getText().toString().isEmpty()){
                    float salud = Float.parseFloat(saludInput.getText().toString());
                    servicios.setSalud(salud);
                }
                if(!imprevistosInput.getText().toString().isEmpty()){
                    float imprevistos = Float.parseFloat(imprevistosInput.getText().toString());
                    servicios.setOtrosImprevistos(imprevistos);
                }
                float totalServicios = Float.parseFloat(totalServiciosResultado.getText().toString().replace(',','.'));
                float totalMantenimiento = Float.parseFloat(totalMantenimientoResultado.getText().toString().replace(',','.'));
                float totalGastos = Float.parseFloat(totalGastosResultado.getText().toString().replace(',','.'));
                Gastos gastos = new Gastos(servicios,mantenimientos[0],mantenimientos[1],mantenimientos[2],mantenimientos[3],totalServicios,totalMantenimiento,totalGastos);

                FirebaseFormulario firebaseFormulario = new FirebaseFormulario(nombrePlan);
                firebaseFormulario.guardarGastosFijos(gastos);

                ////FIREBASE
                //FirebaseFormulario firebaseFormulario = new FirebaseFormulario(nombrePlan,presupuestoResumen,costosProductos,gastos);
                //deudorDatosCredito
                //firebaseFormulario.guardarFormularioFirebase();

            }
        });

        return v;
    }

    /*public void extraerDatosDeudorCredito(){

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

        deudor = new Deudor(nombreDeudor,apellidoDeudor,estadoCivilDeudor,CIDeudor,extensionCIDeudor,telefonoDeudor,edadDedudor);
        credito = new Credito(actividadCredito,montoSolicitadoCredito,formaPagoCredito,plazoCredito,cuotaCredito,interesCredito);
        deudorDatosCredito = new DeudorDatosCredito(deudor,credito);

    }*/
}
