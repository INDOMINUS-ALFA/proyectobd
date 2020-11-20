package com.modulo.proyectobd.controlador;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.modulo.proyectobd.R;
import com.modulo.proyectobd.basesDatos.models.Moto;
import com.modulo.proyectobd.recycler.CaracteristicasAdapter;


public class HistoriaFragment extends Fragment {
    Moto mimoto;
    RecyclerView myrecycler;

    public HistoriaFragment(Moto mimoto) {
        // Required empty public constructor
        this.mimoto = mimoto;


    }
}