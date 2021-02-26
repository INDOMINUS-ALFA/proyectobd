package com.modulo.proyectobd.basesDatos;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.modulo.proyectobd.basesDatos.models.Ubicacion;
import com.modulo.proyectobd.basesDatos.models.UbicacionDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Ubicacion.class}, version = 1, exportSchema = false)
public abstract class UbicacionesBD extends RoomDatabase {

    public abstract UbicacionDao UbiDao();

    private static volatile UbicacionesBD INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    private static UbicacionesBD.Callback sRoomDatabaseCallback = new RoomBD.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                UbicacionDao udao = INSTANCE.UbiDao();
                udao.deleteAll();

                Ubicacion ubicacion = new Ubicacion(
                        "Prueba",
                        "Esto es una prueba",
                        -158.987,
                        150.17,
                        25.0,
                        15.0);

                udao.insert(ubicacion);

                Ubicacion ubicacion2 = new Ubicacion(
                        "Prueba2",
                        "Esto es una prueba3",
                        -158.987,
                        150.17,
                        25.0,
                        15.0);

                udao.insert(ubicacion2);

                Ubicacion ubicacion3 = new Ubicacion(
                        "Prueba3",
                        "Esto es una prueba2",
                        -359,
                        256,
                        12,
                        3);

                udao.insert(ubicacion3);
                Ubicacion ubicacion4 = new Ubicacion(
                        "Bar Manolo",
                        "El mejor bar para bocatas y cervezas",
                        -569.02,
                        563.01,
                        156,
                        23);
                udao.insert(ubicacion4);

            });
        }
    };


    public static String DATABASE_NAME = "BDdatabase";

    public static UbicacionesBD getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (UbicacionesBD.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            UbicacionesBD.class, DATABASE_NAME).allowMainThreadQueries()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}