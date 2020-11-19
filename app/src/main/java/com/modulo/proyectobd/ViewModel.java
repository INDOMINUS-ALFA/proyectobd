package com.modulo.proyectobd;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.modulo.proyectobd.basesDatos.models.Moto;
import com.modulo.proyectobd.basesDatos.models.MotoMinimal;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private Repository mRepository;

    private final LiveData<List<Moto>> mAllMotos;
    private Moto moto;

    public ViewModel (Application application) {
        super(application);
        mRepository = new Repository(application);
        mAllMotos = mRepository.getAllMotos();

    }

    public LiveData<List<MotoMinimal>> getAllMotos(String marca) { return mRepository.getMotosPorMarca(marca); }

   

    public void insert(Moto moto) { mRepository.insert(moto); }

    public Moto motoSelecionada(String nombre) {
        return mRepository.motoSelecionada(nombre);
    }
}