package com.example.simuladorplandenegocios.Controlador;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.simuladorplandenegocios.Vista.Grafica;
import com.example.simuladorplandenegocios.Vista.Resultados;
import com.example.simuladorplandenegocios.Vista.Simulacion;

public class PagerController2 extends FragmentPagerAdapter {
    int numTabs;

    public PagerController2(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numTabs = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new Simulacion();
            case 1:
                return new Resultados();
            case 2:
                return new Grafica();
            default:
                return null;

        }
    }
    @Override
    public int getCount() {
        return numTabs;
    }
}
