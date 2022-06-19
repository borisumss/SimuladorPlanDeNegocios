package com.example.simuladorplandenegocios.Controlador;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.simuladorplandenegocios.Vista.DeudorCredito;
import com.example.simuladorplandenegocios.Vista.GastosFijos;
import com.example.simuladorplandenegocios.Vista.GuardarFormulario;
import com.example.simuladorplandenegocios.Vista.Presupuesto;
import com.example.simuladorplandenegocios.Vista.ProductoCostos;

public class PagerController extends FragmentPagerAdapter{
    private int numoftabs;

    public PagerController(@NonNull FragmentManager fm, int behavior){
        super(fm, behavior);
        this.numoftabs = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        /*Fragment f = new DeudorCredito();
        if( position == 0 ){
            f = new DeudorCredito();
        }else if( position ==1 ){
            f = new Presupuesto();
        }else if( position ==2 ){
            f = new ProductoCostos();
        }else if( position ==3 ){
            f = new GastosFijos();
        }else if ( position == 4 ){
            f = new GuardarFormulario();
        }*/
        switch (position){
            case 0:
                return new DeudorCredito();
            case 1:
                return new Presupuesto();
            case 2:
                return new ProductoCostos();
            case 3:
                return new GastosFijos();
            case 4:
                return new GuardarFormulario();
            default:
                return null;
        }
        //return f;
    }

    @Override
    public int getCount() {
        return numoftabs;
    }
}
