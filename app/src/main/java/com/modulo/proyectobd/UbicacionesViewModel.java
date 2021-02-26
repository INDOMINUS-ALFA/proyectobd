package com.modulo.proyectobd;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.modulo.proyectobd.basesDatos.models.Ubicacion;

import java.util.List;

public class UbicacionesViewModel extends AndroidViewModel {

    private UbicacionesRepository mUbicacionesRepository;

    private final LiveData<List<Ubicacion>> mAllUbicaciones;
    private Ubicacion ubicacion;

    public UbicacionesViewModel(Application app) {
        super(app);
        mUbicacionesRepository = new UbicacionesRepository(app);
        mAllUbicaciones = mUbicacionesRepository.getAllUbicaciones();

    }

    public LiveData<List<Ubicacion>> getAllUbicaciones() { return mUbicacionesRepository.getUbicacionesTotales(); }

   

    public void insert(Ubicacion ubicacion) { mUbicacionesRepository.insert(ubicacion); }

    public Ubicacion ubicacionSeleccionada(String nombre) {
        return mUbicacionesRepository.ubicacionSeleccionada(nombre);
    }
}