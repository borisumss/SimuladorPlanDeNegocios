package com.example.simuladorplandenegocios.Controlador;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Triangular {
    private String nombrePlanNegocio;
    private FirebaseFirestore db;
    public Triangular(String nombrePlanNegocio){
        this.nombrePlanNegocio = nombrePlanNegocio;
        this.db = FirebaseFirestore.getInstance();
    }

    public void ejecutarSimulacion(){



    }
}
