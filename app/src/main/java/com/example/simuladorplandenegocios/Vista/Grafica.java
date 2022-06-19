package com.example.simuladorplandenegocios.Vista;

import static android.content.ContentValues.TAG;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.simuladorplandenegocios.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Grafica#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Grafica extends Fragment {

//   private TextView tv1;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String resultado;
    LineChart mpLineChart;
    TextView recomendacionResultado;
    Button mostrarGrafica;
    String nombreSimulacion;
    private String nameBussines;
    private int year1;
    private int year2;
    private int year3;
    private int year4;
    private int year5;
    private int year6;
    private int year7;
    String recomendacion;
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
                resultado = result.getString("NombreProyecto");
                System.out.println(resultado);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v= inflater.inflate(R.layout.fragment_grafica, container, false);

        mostrarGrafica = v.findViewById(R.id.mostrarGrafica);

        recomendacionResultado= v.findViewById(R.id.recomendacion);

        EditText namePlane = (EditText) getActivity().findViewById(R.id.nombreSimuInput);
        nameBussines = namePlane.getText().toString();

        mostrarGrafica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View vista2 = inflater.inflate(R.layout.fragment_simulacion, container, false);
                //grafico
                mpLineChart= v.findViewById(R.id.line_chart);
                Año1();
                Año2();
                Año3();
                Año4();
                Año5();
                Año6();
                Año7();

                /*FirebaseFirestore db = FirebaseFirestore.getInstance();
                DocumentReference docRef = db.collection(nameBussines).document("Grafica");
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                                recomendacion = document.getString("Recomendacion");

                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });*/



            }
        });

        return v;
    }
    private void Año1(){
        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        //firebase
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        System.out.println(nameBussines);
        DocumentReference docRef = db.collection(nameBussines).document("Grafica");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        year1 = (int) document.getDouble("year1").doubleValue();
                        year2 = (int) document.getDouble("year2").doubleValue();
                        year3 = (int) document.getDouble("year3").doubleValue();
                        year4 = (int) document.getDouble("year4").doubleValue();
                        year5 = (int) document.getDouble("year5").doubleValue();
                        year6 = (int) document.getDouble("year6").doubleValue();
                        year7 = (int) document.getDouble("year7").doubleValue();
                        recomendacion = document.getString("Recomendacion");
                        recomendacionResultado.setText(recomendacion);

                        dataVals.add(new Entry(0,0));
                        dataVals.add(new Entry(1,year1));
                        LineDataSet lineDataSet1 = new LineDataSet(dataVals,"Año 1");
                        LineDataSet lineDataSet2 = new LineDataSet(Año2(),"Año 2");
                        LineDataSet lineDataSet3 = new LineDataSet(Año3(),"Año 3");
                        LineDataSet lineDataSet4 = new LineDataSet(Año4(),"Año 4");
                        LineDataSet lineDataSet5 = new LineDataSet(Año5(),"Año 5");
                        LineDataSet lineDataSet6 = new LineDataSet(Año6(),"Año 6");
                        LineDataSet lineDataSet7 = new LineDataSet(Año7(),"Año 7");
                        lineDataSet1.setColor(Color.BLUE);
                        lineDataSet2.setColor(Color.RED);
                        lineDataSet3.setColor(Color.BLACK);
                        lineDataSet4.setColor(Color.CYAN);
                        lineDataSet5.setColor(Color.MAGENTA);
                        lineDataSet6.setColor(Color.YELLOW);
                        lineDataSet7.setColor(Color.GREEN);
                        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                        dataSets.add(lineDataSet1);
                        dataSets.add(lineDataSet2);
                        dataSets.add(lineDataSet3);
                        dataSets.add(lineDataSet4);
                        dataSets.add(lineDataSet5);
                        dataSets.add(lineDataSet6);
                        dataSets.add(lineDataSet7);
                        LineData data = new LineData(dataSets);
                        mpLineChart.setData(data);
                        mpLineChart.invalidate();
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

    }
    private ArrayList<Entry> Año2(){
        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(new LineDataSet(dataVals,"Año 2"));
        LineData data = new LineData(dataSets);
        mpLineChart.setData(data);
        mpLineChart.invalidate();
        dataVals.add(new Entry(1,year1));
        dataVals.add(new Entry(2,year2));
        return dataVals;
    }
    private ArrayList<Entry> Año3(){
        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(new LineDataSet(dataVals,"Año 3"));
        LineData data = new LineData(dataSets);
        mpLineChart.setData(data);
        mpLineChart.invalidate();
        dataVals.add(new Entry(2,year2));
        dataVals.add(new Entry(3,year3));
        return dataVals;
    }
    private ArrayList<Entry> Año4(){
        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(new LineDataSet(dataVals,"Año 4"));
        LineData data = new LineData(dataSets);
        mpLineChart.setData(data);
        mpLineChart.invalidate();
        dataVals.add(new Entry(3,year3));
        dataVals.add(new Entry(4,year4));
        return dataVals;
    }
    private ArrayList<Entry> Año5(){
        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(new LineDataSet(dataVals,"Año 5"));
        LineData data = new LineData(dataSets);
        mpLineChart.setData(data);
        mpLineChart.invalidate();
        dataVals.add(new Entry(4,year4));
        dataVals.add(new Entry(5,year5));
        return dataVals;
    }
    private ArrayList<Entry> Año6(){
        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(new LineDataSet(dataVals,"Año 6"));
        LineData data = new LineData(dataSets);
        mpLineChart.setData(data);
        mpLineChart.invalidate();
        dataVals.add(new Entry(5,year5));
        dataVals.add(new Entry(6,year6));
        return dataVals;
    }
    private ArrayList<Entry> Año7(){
        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(new LineDataSet(dataVals,"Año 7"));
        LineData data = new LineData(dataSets);
        mpLineChart.setData(data);
        mpLineChart.invalidate();
        dataVals.add(new Entry(6,year6));
        dataVals.add(new Entry(7,year7));
        return dataVals;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}



