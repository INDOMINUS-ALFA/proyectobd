package com.modulo.proyectobd.controlador;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerControler  extends FragmentPagerAdapter {
    int numftabs;

    public PagerControler(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numftabs = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                CaracteristicasFragment caract = new CaracteristicasFragment();
                return  caract;
            case 1:
                HistoriaFragment hist = new HistoriaFragment();
                return  hist;
            default:
        }


        return null;
    }

    @Override
    public int getCount() {
        return numftabs;
    }
}
