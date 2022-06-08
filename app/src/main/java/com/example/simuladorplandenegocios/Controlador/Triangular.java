package com.example.simuladorplandenegocios.Controlador;



import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Triangular {

    private String nombrePlanNegocio;
    private FirebaseFirestore db;
    private float inflacion;
    private float fca[];
    public Triangular(String nombrePlanNegocio){
        this.nombrePlanNegocio = nombrePlanNegocio;
        this.db = FirebaseFirestore.getInstance();
        this.inflacion = (float)((0.000049f + 0.0147f + 0.0151f + 0.0271f + 0.04f)/5);
        this.fca = new float[8];
    }

    public void ejecutarSimulacion(){
        int plazo = 0;
        float cuota = 0f;
        float interesCredito = 0f;
        DocumentReference docRef = this.db.collection(""+this.nombrePlanNegocio).document("Deudor y Credito");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        HashMap credito = (HashMap) document.get("Credito");
                        plazo = (int) credito.get("Plazo");

                        //Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        //Log.d(TAG, "No such document");
                    }
                } else {
                    //Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
        //float interes = this.db.collection(""+this.nombrePlanNegocio).document();
        //float TREMA =


    }
}
