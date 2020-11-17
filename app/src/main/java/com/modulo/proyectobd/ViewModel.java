package com.modulo.proyectobd;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.modulo.proyectobd.basesDatos.models.Moto;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private Repository mRepository;

    private final LiveData<List<Moto>> mAllMotos;

    public ViewModel (Application application) {
        super(application);
        mRepository = new Repository(application);
        mAllMotos = mRepository.getAllMotos();
    }

    public LiveData<List<Moto>> getAllMotos() { return mAllMotos; }

    public void insert(Moto moto) { mRepository.insert(moto); }

}
