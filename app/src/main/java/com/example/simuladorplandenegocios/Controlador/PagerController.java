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
                return new tab1();
            case 1:
                return new tab2();
            case 2:
                return new tab3();
            case 3:
                return new tab4();
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return numoftabs;
    }
}
