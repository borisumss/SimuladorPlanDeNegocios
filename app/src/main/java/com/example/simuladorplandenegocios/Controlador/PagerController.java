package com.example.simuladorplandenegocios.Controlador;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerController extends FragmentPagerAdapter{
    private int numoftabs;

    public PagerController(@NonNull FragmentManager fm, int behavior){
        super(fm, behavior);
        this.numoftabs = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

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
    }

    @Override
    public int getCount() {
        return numoftabs;
    }
}
