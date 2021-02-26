package com.modulo.proyectobd;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.modulo.proyectobd.basesDatos.RoomBD;
import com.modulo.proyectobd.basesDatos.UbicacionesBD;
import com.modulo.proyectobd.basesDatos.models.Moto;
import com.modulo.proyectobd.basesDatos.models.MotoDao;
import com.modulo.proyectobd.basesDatos.models.MotoMinimal;
import com.modulo.proyectobd.basesDatos.models.Ubicacion;
import com.modulo.proyectobd.basesDatos.models.UbicacionDao;

import java.util.List;

public class UbicacionesRepository {

    private UbicacionDao mUbicacionDao;
    private LiveData<List<Ubicacion>> mAllUbicaciones;


    /*Note that in order to unit test the WordRepository, you have to remove the Application
    dependency. This adds complexity and much more code, and this sample is not about testing.
    See the BasicSample in the android-architecture-components repository at
     https://github.com/googlesamples*/

    UbicacionesRepository(Application app) {
        UbicacionesBD db = UbicacionesBD.getDatabase(app);
        mUbicacionDao =  db.UbiDao();

    }
    /* Room executes all queries on a separate thread.
       Observed LiveData will notify the observer when the data has changed.*/
    LiveData<List<Ubicacion>> getAllUbicaciones() {
        return mAllUbicaciones;
    }

    LiveData<List<Ubicacion>> getUbicacionesTotales() {
        return mUbicacionDao.getAlphabetizedUbicaciones();
    }
    Ubicacion ubicacionSeleccionada(String nombre){
        return mUbicacionDao.ubicacionSeleccionada(nombre);
    }



    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Ubicacion ubicacion) {
        UbicacionesBD.databaseWriteExecutor.execute(() -> {
            mUbicacionDao.insert(ubicacion);
        });
    }



}
