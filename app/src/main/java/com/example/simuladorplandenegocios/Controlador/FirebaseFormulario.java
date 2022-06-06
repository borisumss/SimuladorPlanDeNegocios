package com.example.simuladorplandenegocios.Controlador;

import androidx.annotation.NonNull;

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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class FirebaseFormulario {
    private DeudorDatosCredito deudorDatosCredito;
    private Deudor deudor;
    private Credito credito;
    private AportePropio aportePropio;
    private Requerimiento requerimiento;
    private PresupuestoResumen presupuestoResumen;
    private CostosProductos costosProductos;
    private Producto producto1,producto2,producto3,producto4;
    private Gastos gastos;
    private Servicios servicios;
    private Mantenimiento mantenimiento1,mantenimiento2,mantenimiento3,mantenimiento4;
    private FirebaseFirestore db;
    private String nombrePlan;

    public FirebaseFormulario(String nombrePlan, DeudorDatosCredito deudorDatosCredito, PresupuestoResumen presupuestoResumen, CostosProductos costosProductos, Gastos gastos){
        this.nombrePlan = nombrePlan;
        this.deudorDatosCredito = deudorDatosCredito;
        this.presupuestoResumen = presupuestoResumen;
        this.costosProductos = costosProductos;
        this.gastos = gastos;
        this.db = FirebaseFirestore.getInstance();
    }

    public void guardarFormularioFirebase(){
        guardarDeudorYCredito();
        guardarPresupuestoResumen();
        guardarCostosProductos();
        guardarGastosFijos();
    }

    public void guardarGastosFijos(){
        this.servicios = this.gastos.getServicios();
        this.mantenimiento1 = this.gastos.getMantenimiento1();
        this.mantenimiento2 = this.gastos.getMantenimiento2();
        this.mantenimiento3 = this.gastos.getMantenimiento3();
        this.mantenimiento4 = this.gastos.getMantenimiento4();
        Map<String, Object> gastosFijosDatos = new HashMap<>();
        Map<String, Object> serviciosDatos = new HashMap<>();
        serviciosDatos.put("Impuestos",this.servicios.getImpuestos());
        serviciosDatos.put("Alimentacion",this.servicios.getAlimentacion());
        serviciosDatos.put("Servicio Luz",this.servicios.getServicioLuz());
        serviciosDatos.put("Servicio Agua",this.servicios.getServicioAgua());
        serviciosDatos.put("Servicio Telefono",this.servicios.getServicioTelefono());
        serviciosDatos.put("Servicio Celular",this.servicios.getServicioCelular());
        serviciosDatos.put("Salud",this.servicios.getSalud());
        serviciosDatos.put("Imprevistos",this.servicios.getOtrosImprevistos());
        Map<String, Object> mantenimiento1Datos = new HashMap<>();
        mantenimiento1Datos.put("Nombre",this.mantenimiento1.getNombre());
        mantenimiento1Datos.put("Costo",this.mantenimiento1.getCostoMantenimiento());
        Map<String, Object> mantenimiento2Datos = new HashMap<>();
        mantenimiento2Datos.put("Nombre",this.mantenimiento2.getNombre());
        mantenimiento2Datos.put("Costo",this.mantenimiento2.getCostoMantenimiento());
        Map<String, Object> mantenimiento3Datos = new HashMap<>();
        mantenimiento3Datos.put("Nombre",this.mantenimiento3.getNombre());
        mantenimiento3Datos.put("Costo",this.mantenimiento3.getCostoMantenimiento());
        Map<String, Object> mantenimiento4Datos = new HashMap<>();
        mantenimiento4Datos.put("Nombre",this.mantenimiento4.getNombre());
        mantenimiento4Datos.put("Costo",this.mantenimiento4.getCostoMantenimiento());

        gastosFijosDatos.put("Servicios",serviciosDatos);
        gastosFijosDatos.put("Mantenimiento 1",mantenimiento1Datos);
        gastosFijosDatos.put("Mantenimiento 2",mantenimiento2Datos);
        gastosFijosDatos.put("Mantenimiento 3",mantenimiento3Datos);
        gastosFijosDatos.put("Mantenimiento 4",mantenimiento4Datos);
        gastosFijosDatos.put("Total Servicios",this.gastos.getTotalServicios());
        gastosFijosDatos.put("Total Mantenimiento",this.gastos.getTotalMantenimiento());
        gastosFijosDatos.put("Total Gastos",this.gastos.getTotalGastos());

        this.db.collection(""+this.nombrePlan).document("Gastos Fijos")
                .set(gastosFijosDatos)
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

    public void guardarCostosProductos(){
        this.producto1 = this.costosProductos.getProducto1();
        this.producto2 = this.costosProductos.getProducto2();
        this.producto3 = this.costosProductos.getProducto3();
        this.producto4 = this.costosProductos.getProducto4();

        Map<String, Object> costosProductosDatos = new HashMap<>();
        Map<String, Object> producto1Datos = new HashMap<>();
        producto1Datos.put("Nombre Producto",this.producto1.getNombre());
        producto1Datos.put("Costo Produccion Unidad",this.producto1.getCostoProduccion());
        producto1Datos.put("Precio Venta Pesimista",this.producto1.getPrecioVentaPesimista());
        producto1Datos.put("Precio Venta Moderado",this.producto1.getPrecioVentaModerado());
        producto1Datos.put("Precio Venta Optimista",this.producto1.getPrecioVentaOptimista());
        producto1Datos.put("Margen Bruto Venta",this.producto1.getMargenBrutoVenta());
        producto1Datos.put("Cantidad Vendida",this.producto1.getCantidadVendida());
        producto1Datos.put("Total Periodo",this.producto1.getTotalPeriodo());
        Map<String, Object> producto2Datos = new HashMap<>();
        producto2Datos.put("Nombre Producto",this.producto2.getNombre());
        producto2Datos.put("Costo Produccion Unidad",this.producto2.getCostoProduccion());
        producto2Datos.put("Precio Venta Pesimista",this.producto2.getPrecioVentaPesimista());
        producto2Datos.put("Precio Venta Moderado",this.producto2.getPrecioVentaModerado());
        producto2Datos.put("Precio Venta Optimista",this.producto2.getPrecioVentaOptimista());
        producto2Datos.put("Margen Bruto Venta",this.producto2.getMargenBrutoVenta());
        producto2Datos.put("Cantidad Vendida",this.producto2.getCantidadVendida());
        producto2Datos.put("Total Periodo",this.producto2.getTotalPeriodo());
        Map<String, Object> producto3Datos = new HashMap<>();
        producto3Datos.put("Costo Produccion Unidad",this.producto3.getCostoProduccion());
        producto3Datos.put("Precio Venta Pesimista",this.producto3.getPrecioVentaPesimista());
        producto3Datos.put("Precio Venta Moderado",this.producto3.getPrecioVentaModerado());
        producto3Datos.put("Precio Venta Optimista",this.producto3.getPrecioVentaOptimista());
        producto3Datos.put("Margen Bruto Venta",this.producto3.getMargenBrutoVenta());
        producto3Datos.put("Cantidad Vendida",this.producto3.getCantidadVendida());
        producto3Datos.put("Total Periodo",this.producto3.getTotalPeriodo());
        Map<String, Object> producto4Datos = new HashMap<>();
        producto4Datos.put("Costo Produccion Unidad",this.producto4.getCostoProduccion());
        producto4Datos.put("Precio Venta Pesimista",this.producto4.getPrecioVentaPesimista());
        producto4Datos.put("Precio Venta Moderado",this.producto4.getPrecioVentaModerado());
        producto4Datos.put("Precio Venta Optimista",this.producto4.getPrecioVentaOptimista());
        producto4Datos.put("Margen Bruto Venta",this.producto4.getMargenBrutoVenta());
        producto4Datos.put("Cantidad Vendida",this.producto4.getCantidadVendida());
        producto4Datos.put("Total Periodo",this.producto4.getTotalPeriodo());

        costosProductosDatos.put("Producto 1",producto1Datos);
        costosProductosDatos.put("Producto 2",producto2Datos);
        costosProductosDatos.put("Producto 3",producto3Datos);
        costosProductosDatos.put("Producto 4",producto4Datos);

        this.db.collection(""+this.nombrePlan).document("Costos Productos")
                .set(costosProductosDatos)
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

    public void guardarPresupuestoResumen(){
        this.aportePropio = this.presupuestoResumen.getAportePropio();
        this.requerimiento = this.presupuestoResumen.getRequerimiento();

        Map<String, Object> presupuestoResumenDatos = new HashMap<>();
        Map<String, Object> aportePropioDatos = new HashMap<>();
        Map<String, Object> requerimientoDatos = new HashMap<>();

        aportePropioDatos.put("Efectivo",this.aportePropio.getEfectivo());
        aportePropioDatos.put("Mano de Obra Emprendedor",this.aportePropio.getManoObraEmprendedor());
        aportePropioDatos.put("Materia Prima Insumos",this.aportePropio.getInsumosMateriaPrima());
        aportePropioDatos.put("Requerimientos Promocionales",this.aportePropio.getRequerimientosPromocionales());
        aportePropioDatos.put("Infraestructura",this.aportePropio.getInfraestructura());
        aportePropioDatos.put("Equipos Vehiculos Maquinaria",this.aportePropio.getEquiposVehiculosMaquinaria());
        aportePropioDatos.put("Requerimientos Legales",this.aportePropio.getRequerimientosLegales());

        requerimientoDatos.put("Gastos Operativos",this.requerimiento.getGastosOperativos());
        requerimientoDatos.put("Materia Prima Insumos",this.requerimiento.getInsumosMateriaPrima());
        requerimientoDatos.put("Requerimientos Promocionales",this.requerimiento.getRequerimientosPromocionales());
        requerimientoDatos.put("Infraestructura",this.requerimiento.getInfraestructura());
        requerimientoDatos.put("Equipos Vehiculos Maquinaria",this.requerimiento.getEquiposVehiculosMaquinaria());
        requerimientoDatos.put("Requerimientos Legales",this.requerimiento.getRequerimientosLegales());

        presupuestoResumenDatos.put("Aporte Propio",aportePropioDatos);
        presupuestoResumenDatos.put("Requerimiento",requerimientoDatos);
        presupuestoResumenDatos.put("Cumplimiento Aporte Propio",this.presupuestoResumen.getMensajeCumplimientoAporte());
        presupuestoResumenDatos.put("Cumplimiento Financiamiento",this.presupuestoResumen.getMensajeFinanciamiento());
        presupuestoResumenDatos.put("Total Aporte Propio",this.presupuestoResumen.getTotalAportePropio());
        presupuestoResumenDatos.put("Total Requerimiento",this.presupuestoResumen.getTotalRequerimiento());
        presupuestoResumenDatos.put("Total Proyecto",this.presupuestoResumen.getTotalProyecto());
        presupuestoResumenDatos.put("Aporte Propio Resumen",this.presupuestoResumen.getAportePropioCalculado());
        presupuestoResumenDatos.put("Porcentaje Aporte Propio",this.presupuestoResumen.getPorcentajeAportePropio());
        presupuestoResumenDatos.put("Monto Solicitado",this.presupuestoResumen.getMontoSolicitado());
        presupuestoResumenDatos.put("Monto Financiado",this.presupuestoResumen.getMontoFinanciado());

        this.db.collection(""+this.nombrePlan).document("Presupuesto Resumen")
                .set(presupuestoResumenDatos)
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

    public void guardarDeudorYCredito(){
        this.deudor = this.deudorDatosCredito.getDeudor();
        this.credito = this.deudorDatosCredito.getCredito();
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

        this.db.collection(""+this.nombrePlan).document("Deudor y Credito")
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
