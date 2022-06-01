package com.example.simuladorplandenegocios.Controlador;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.simuladorplandenegocios.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductoCostos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductoCostos extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText producto1Nombre,producto1CostoProduccion,producto1PrecioVentaPesimista,producto1PrecioVentaModerado,producto1PrecioVentaOptimista,producto1CantidadVendida;
    private EditText producto2Nombre,producto2CostoProduccion,producto2PrecioVentaPesimista,producto2PrecioVentaModerado,producto2PrecioVentaOptimista,producto2CantidadVendida;
    private EditText producto3Nombre,producto3CostoProduccion,producto3PrecioVentaPesimista,producto3PrecioVentaModerado,producto3PrecioVentaOptimista,producto3CantidadVendida;
    private EditText producto4Nombre,producto4CostoProduccion,producto4PrecioVentaPesimista,producto4PrecioVentaModerado,producto4PrecioVentaOptimista,producto4CantidadVendida;
    private TextView producto1MargenBruto,producto2MargenBruto,producto3MargenBruto,producto4MargenBruto;
    private TextView producto1TotalPeriodo,producto2TotalPeriodo,producto3TotalPeriodo,producto4TotalPeriodo;
    String producto1MargenBrutoText,producto2MargenBrutoText,producto3MargenBrutoText,producto4MargenBrutoText;
    String producto1TotalPeriodoText,producto2TotalPeriodoText,producto3TotalPeriodoText,producto4TotalPeriodoText;
    String[] margenBrutoProducto;
    String[] totalPeriodoProducto;
    private Button calcularCostos;
    public ProductoCostos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductoCostos.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductoCostos newInstance(String param1, String param2) {
        ProductoCostos fragment = new ProductoCostos();
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
        //return inflater.inflate(R.layout.fragment_producto_costos, container, false);

        View v = inflater.inflate(R.layout.fragment_producto_costos, container, false);
        calcularCostos = (Button) v.findViewById(R.id.calcularProductosButton);

        producto1Nombre = (EditText) v.findViewById(R.id.producto1NombreInput);
        producto1CostoProduccion = (EditText) v.findViewById(R.id.producto1TotalCostoProduccionUnidadInput);
        producto1PrecioVentaPesimista = (EditText) v.findViewById(R.id.producto1PrecioDeVentaPesimistaUnidadInput);
        producto1PrecioVentaModerado = (EditText) v.findViewById(R.id.producto1PrecioDeVentaModeradoUnidadInput);
        producto1PrecioVentaOptimista = (EditText) v.findViewById(R.id.producto1PrecioDeVentaOptimistaUnidadInput);
        producto1CantidadVendida = (EditText) v.findViewById(R.id.producto1CantidadVendidaInput);

        producto2Nombre = (EditText) v.findViewById(R.id.producto2NombreInput);
        producto2CostoProduccion = (EditText) v.findViewById(R.id.producto2TotalCostoProduccionUnidadInput);
        producto2PrecioVentaPesimista = (EditText) v.findViewById(R.id.producto2PrecioDeVentaPesimistaUnidadInput);
        producto2PrecioVentaModerado = (EditText) v.findViewById(R.id.producto2PrecioDeVentaModeradoUnidadInput);
        producto2PrecioVentaOptimista = (EditText) v.findViewById(R.id.producto2PrecioDeVentaOptimistaUnidadInput);
        producto2CantidadVendida = (EditText) v.findViewById(R.id.producto2CantidadVendidaInput);

        producto3Nombre = (EditText) v.findViewById(R.id.producto3NombreInput);
        producto3CostoProduccion = (EditText) v.findViewById(R.id.producto3TotalCostoProduccionUnidadInput);
        producto3PrecioVentaPesimista = (EditText) v.findViewById(R.id.producto3PrecioDeVentaPesimistaUnidadInput);
        producto3PrecioVentaModerado = (EditText) v.findViewById(R.id.producto3PrecioDeVentaModeradoUnidadInput);
        producto3PrecioVentaOptimista = (EditText) v.findViewById(R.id.producto3PrecioDeVentaOptimistaUnidadInput);
        producto3CantidadVendida = (EditText) v.findViewById(R.id.producto3CantidadVendidaInput);

        producto4Nombre = (EditText) v.findViewById(R.id.producto4NombreInput);
        producto4CostoProduccion = (EditText) v.findViewById(R.id.producto4TotalCostoProduccionUnidadInput);
        producto4PrecioVentaPesimista = (EditText) v.findViewById(R.id.producto4PrecioDeVentaPesimistaUnidadInput);
        producto4PrecioVentaModerado = (EditText) v.findViewById(R.id.producto4PrecioDeVentaModeradoUnidadInput);
        producto4PrecioVentaOptimista = (EditText) v.findViewById(R.id.producto4PrecioDeVentaOptimistaUnidadInput);
        producto4CantidadVendida = (EditText) v.findViewById(R.id.producto4CantidadVendidaInput);

        producto1MargenBruto = (TextView) v.findViewById(R.id.producto1MargenBrutoVentaUnidadResultado);
        producto2MargenBruto = (TextView) v.findViewById(R.id.producto2MargenBrutoVentaUnidadResultado);
        producto3MargenBruto = (TextView) v.findViewById(R.id.producto3MargenBrutoVentaUnidadResultado);
        producto4MargenBruto = (TextView) v.findViewById(R.id.producto4MargenBrutoVentaUnidadResultado);
        producto1TotalPeriodo = (TextView) v.findViewById(R.id.producto1TotalPeriodoResultado);
        producto2TotalPeriodo = (TextView) v.findViewById(R.id.producto2TotalPeriodoResultado);
        producto3TotalPeriodo = (TextView) v.findViewById(R.id.producto3TotalPeriodoResultado);
        producto4TotalPeriodo = (TextView) v.findViewById(R.id.producto4TotalPeriodoResultado);

        calcularCostos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularMargenBrutoToltalPeriodoProductos();
                producto1MargenBruto.setText(margenBrutoProducto[0]);
                producto2MargenBruto.setText(margenBrutoProducto[1]);
                producto3MargenBruto.setText(margenBrutoProducto[2]);
                producto4MargenBruto.setText(margenBrutoProducto[3]);
                producto1TotalPeriodo.setText(totalPeriodoProducto[0]);
                producto2TotalPeriodo.setText(totalPeriodoProducto[1]);
                producto3TotalPeriodo.setText(totalPeriodoProducto[2]);
                producto4TotalPeriodo.setText(totalPeriodoProducto[3]);

            }
        });
        return v;
    }
    private void calcularMargenBrutoToltalPeriodoProductos(){
        EditText[] productoNombre = {producto1Nombre,producto2Nombre,producto3Nombre,producto4Nombre};
        EditText[] productoCostoProduccion = {producto1CostoProduccion,producto2CostoProduccion,producto3CostoProduccion,producto4CostoProduccion};
        EditText[] productoPrecioVentaPesimista = {producto1PrecioVentaPesimista,producto2PrecioVentaPesimista,producto3PrecioVentaPesimista,producto4PrecioVentaPesimista};
        EditText[] productoPrecioVentaModerado = {producto1PrecioVentaModerado,producto2PrecioVentaModerado,producto3PrecioVentaModerado,producto4PrecioVentaModerado};
        EditText[] productoPrecioVentaOptimista = {producto1PrecioVentaOptimista,producto2PrecioVentaOptimista,producto3PrecioVentaOptimista,producto4PrecioVentaOptimista};
        EditText[] productoCantidadVendida = {producto1CantidadVendida,producto2CantidadVendida,producto3CantidadVendida,producto4CantidadVendida};
        this.margenBrutoProducto = new String[4];
        this.totalPeriodoProducto = new String[4];
        for(int i=0;i<4;i++){
            if(!productoNombre[i].getText().toString().isEmpty()){
                if(!productoCostoProduccion[i].getText().toString().isEmpty()){
                    if(!productoPrecioVentaPesimista[i].getText().toString().isEmpty()){
                        if(!productoPrecioVentaModerado[i].getText().toString().isEmpty()){
                            if(!productoPrecioVentaOptimista[i].getText().toString().isEmpty()){
                                if(!productoCantidadVendida[i].getText().toString().isEmpty()){
                                    float margenBruto = (Float.parseFloat(productoPrecioVentaModerado[i].getText().toString()) - Float.parseFloat(productoCostoProduccion[i].getText().toString()))/(Float.parseFloat(productoPrecioVentaModerado[i].getText().toString()));
                                    margenBrutoProducto[i] = Float.toString(margenBruto);
                                    float totalPeriodo = Float.parseFloat(productoPrecioVentaModerado[i].getText().toString()) * Float.parseFloat(productoCantidadVendida[i].getText().toString());
                                    totalPeriodoProducto[i] = Float.toString(totalPeriodo);
                                }else{
                                    totalPeriodoProducto[i] = "0";
                                    margenBrutoProducto[i] = "0";
                                }
                            }else{
                                totalPeriodoProducto[i] = "0";
                                margenBrutoProducto[i] = "0";
                            }
                        }else{
                            totalPeriodoProducto[i] = "0";
                            margenBrutoProducto[i] = "0";
                        }
                    }else{
                        totalPeriodoProducto[i] = "0";
                        margenBrutoProducto[i] = "0";
                    }
                }else{
                    totalPeriodoProducto[i] = "0";
                    margenBrutoProducto[i] = "0";
                }
            }else{
                totalPeriodoProducto[i] = "0";
                margenBrutoProducto[i] = "0";
            }
        }
    }

}