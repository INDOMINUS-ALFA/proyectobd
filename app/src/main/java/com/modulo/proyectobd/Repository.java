package com.modulo.proyectobd;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.modulo.proyectobd.basesDatos.RoomBD;
import com.modulo.proyectobd.basesDatos.models.Moto;
import com.modulo.proyectobd.basesDatos.models.MotoDao;

import java.util.List;

public class Repository {

    private MotoDao mMotoDao;
    private LiveData<List<Moto>> mAllMotos;

    /*Note that in order to unit test the WordRepository, you have to remove the Application
    dependency. This adds complexity and much more code, and this sample is not about testing.
    See the BasicSample in the android-architecture-components repository at
     https://github.com/googlesamples*/

    Repository(Application application) {
        RoomBD db = RoomBD.getDatabase(application);
        mMotoDao = db.mainDao();
        //mAllMotos = mMotoDao.getAlphabetizedMotos(smarca);
    }
    /* Room executes all queries on a separate thread.
       Observed LiveData will notify the observer when the data has changed.*/
    LiveData<List<Moto>> getAllMotos() {

        return mAllMotos;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Moto moto) {
        RoomBD.databaseWriteExecutor.execute(() -> {
            mMotoDao.insert(moto);
        });
    }



}
