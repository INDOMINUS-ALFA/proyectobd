package com.modulo.proyectobd.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toolbar;

import com.modulo.proyectobd.Geolocalizacion.LocalizacionActivity;
import com.modulo.proyectobd.R;
import com.modulo.proyectobd.UbicacionesViewModel;
import com.modulo.proyectobd.ViewModel;
import com.modulo.proyectobd.recycler.UbicacionRecyclerviewAdapter;
import com.modulo.proyectobd.recycler.UbicacionesViewHolder;
import com.modulo.proyectobd.settings.MySettings;

public class Ubicaciones_Activity extends AppCompatActivity {
    private UbicacionesViewModel myUbicacionViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("VALUES", MODE_PRIVATE);
        int temaelegido = sharedPreferences.getInt("THEME", 1);

        switch (temaelegido){
            case 1: setTheme(R.style.AppTheme);
                break;
            case 2: setTheme(R.style.custom1);
                break;
            case 3: setTheme(R.style.custom2);
                break;
            case 4: setTheme(R.style.custom3);
                break;
        }

        setContentView(R.layout.lista_ubicaciones);



        RecyclerView recyclerViewUbicaciones = findViewById(R.id.recycler_ubicaciones_id);
        final UbicacionesViewHolder.UbicacionListAdapter adapter = new UbicacionesViewHolder.UbicacionListAdapter(new UbicacionesViewHolder.UbicacionListAdapter.WordDiff());

        recyclerViewUbicaciones.setAdapter(adapter);
        recyclerViewUbicaciones.setLayoutManager(new LinearLayoutManager(this));
        myUbicacionViewModel = new ViewModelProvider(this).get(UbicacionesViewModel.class);
        myUbicacionViewModel.getAllUbicaciones().observe(this, Ubicaciones -> {
            adapter.submitList(Ubicaciones);



            RecyclerView my_rw =  findViewById(R.id.recycler_ubicaciones_id);
            UbicacionRecyclerviewAdapter my_adapter = new UbicacionRecyclerviewAdapter(this,Ubicaciones);

            my_rw.setLayoutManager(new GridLayoutManager(this,3));
            my_rw.setAdapter(my_adapter);

        });


    }


    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id  = item.getItemId();

        if(id == R.id.settings_itm){
            Intent intent3 = new Intent(this, MySettings.class);
            startActivity(intent3);
        }else if (id == R.id.home_itm){
            Intent intent2 = new Intent(this, MainActivity.class);
            startActivity(intent2);
        }else if (id == R.id.location){
            Intent intent3 = new Intent(this, LocalizacionActivity.class);
            startActivity(intent3);
        }else if (id == R.id.ubicaciones_lista) {
            Intent intent4 = new Intent(this, Ubicaciones_Activity.class);
            startActivity(intent4);

        }
        return super.onOptionsItemSelected(item);
    }


}