package com.modulo.proyectobd;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.ValueFormatter;

public class StatsValueFormatter extends ValueFormatter {

    private String[] stats = {"handly", "T.Sped", "Breack", "AeroDyn", "Speed-Up"};

    @Override
    public String getAxisLabel(float value, AxisBase axis) {
        return stats[(int) value];
    }
}