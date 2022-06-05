package com.example.simuladorplandenegocios.Controlador;

import androidx.annotation.NonNull;

import com.example.simuladorplandenegocios.Modelo.Credito;
import com.example.simuladorplandenegocios.Modelo.Deudor;
import com.example.simuladorplandenegocios.Modelo.DeudorDatosCredito;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FirebaseFormulario {
    DeudorDatosCredito deudorDatosCredito;
    Deudor deudor;
    Credito credito;
    FirebaseFirestore db;
    String nombrePlan;

    public FirebaseFormulario(String nombrePlan,DeudorDatosCredito deudorDatosCredito){
        this.nombrePlan = nombrePlan;
        this.deudorDatosCredito = deudorDatosCredito;
        this.db = FirebaseFirestore.getInstance();
    }

    public void guardarFormularioFirebase(){
        this.deudor = this.deudorDatosCredito.getDeudor();
        this.credito = this.deudorDatosCredito.getCredito();
        //DocumentReference alovelaceDocumentRef = db.document(this.nombrePlan+"deudor y credito");
        Map<String, Object> deudorYCredito = new HashMap<>();
        Map<String, Object> deudorDatos = new HashMap<>();
        Map<String, Object> creditoDatos = new HashMap<>();

        deudorDatos.put("Nombre",this.deudor.getNombre());
        deudorDatos.put("Apellido",this.deudor.getApellido());
        deudorDatos.put("Estado Civil",this.deudor.getEstadoCivil());
        deudorDatos.put("CI",this.deudor.getCI());
        deudorDatos.put("Extension",this.deudor.getExtension());
        deudorDatos.put("Telefono",this.deudor.getTelefono());
        deudorDatos.put("Edad",this.deudor.getEdad());

        creditoDatos.put("Actividad",this.credito.getActividad());
        creditoDatos.put("Monto Solicitado",this.credito.getMontoSolicitado());
        creditoDatos.put("Forma de Pago",this.credito.getFormaPago());
        creditoDatos.put("Plazo",this.credito.getPlazo());
        creditoDatos.put("Cuota",this.credito.getCuota());
        creditoDatos.put("Interes",this.credito.getInteres());

        deudorYCredito.put("Deudor",deudorDatos);
        deudorYCredito.put("Credito",creditoDatos);

        this.db.collection(this.nombrePlan).document("Deudor y Credito")
                .set(deudorYCredito)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Log.w(TAG, "Error writing document", e);
                    }
                });

    }
}
