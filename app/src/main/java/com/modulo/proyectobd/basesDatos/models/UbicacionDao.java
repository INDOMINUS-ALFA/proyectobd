package com.modulo.proyectobd.basesDatos.models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;
    @Dao
    public interface UbicacionDao {
        //insert cuerry

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        void insert(Ubicacion ubicacion);

        //Delete querry

        @Delete
        void delete(Ubicacion ubicacion);


        //Update query
        @Query("UPDATE Ubicaciones SET Descripcion = :sDescripcion Where nombre = :sNombre")
        void update (int sDescripcion, String sNombre);

        @Query("DELETE  FROM Ubicaciones")
        void deleteAll();



        // select

        @Query("SELECT * FROM Ubicaciones order by nombre ASC")
        LiveData<List<Ubicacion>> getAlphabetizedUbicaciones();


        @Query("SELECT * FROM Ubicaciones WHERE nombre = :snombre")
        Ubicacion ubicacionSeleccionada(String snombre);
}
