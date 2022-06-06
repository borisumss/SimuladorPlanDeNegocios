package com.example.simuladorplandenegocios.Modelo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.simuladorplandenegocios.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Grafica#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Grafica extends Fragment {

   private TextView tv1;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    LineChart mpLineChart;
    public Grafica() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Grafica newInstance(String param1, String param2) {
        Grafica fragment = new Grafica();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getParentFragmentManager().setFragmentResultListener("nombre", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String resultado = result.getString("NombreProyecto");
                tv1.setText(resultado);
            }

        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_grafica, container, false);
        mpLineChart=(LineChart)v.findViewById(R.id.line_chart);
        LineDataSet lineDataSet1 = new LineDataSet(Año1(),"Año 1");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet1);
        LineData data = new LineData(dataSets);
        mpLineChart.setData(data);
        mpLineChart.invalidate();
        return v;
    }
    private ArrayList<Entry> Año1(){
        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        dataVals.add(new Entry(0,20));
        dataVals.add(new Entry(1,24));
        dataVals.add(new Entry(2,2));
        dataVals.add(new Entry(3,10));
        dataVals.add(new Entry(4,28));
        return dataVals;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv1= view.findViewById(R.id.SimulacionR);
    }
}



