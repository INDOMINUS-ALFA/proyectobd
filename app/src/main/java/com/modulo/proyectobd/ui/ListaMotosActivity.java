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
import com.modulo.proyectobd.recycler.MotoRecyclerviewAdapter;
import com.modulo.proyectobd.recycler.MotoSViewHolder;
import com.modulo.proyectobd.R;
import com.modulo.proyectobd.ViewModel;
import com.modulo.proyectobd.settings.MySettings;

public class ListaMotosActivity extends AppCompatActivity {
    private ViewModel myMotoViewModel;

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
        setContentView(R.layout.lista_motos);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        navigationBarStatusBar();

        RecyclerView recyclerView = findViewById(R.id.recyclerviewmoto_id);
        final MotoSViewHolder.MotoListAdapter adapter = new MotoSViewHolder.MotoListAdapter(new MotoSViewHolder.MotoListAdapter.WordDiff());

        recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            //myMotoViewModel = new ViewModel(this.getApplication());
            myMotoViewModel = new ViewModelProvider(this).get(ViewModel.class);
                String marca = getIntent().getStringExtra("marca");
            myMotoViewModel.getAllMotos(marca).observe(this, motos -> {
                adapter.submitList(motos);



                RecyclerView my_rw = (RecyclerView) findViewById(R.id.recyclerviewmoto_id);
                MotoRecyclerviewAdapter my_adapter = new MotoRecyclerviewAdapter(this,motos);

                my_rw.setLayoutManager(new GridLayoutManager(this,3));
                my_rw.setAdapter(my_adapter);
                
        });



    }

    private void setSupportActionBar(Toolbar toolbar) {
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

    public void navigationBarStatusBar() {

        // Fix portrait issues
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            // Fix issues for KitKat setting Status Bar color primary
            if (Build.VERSION.SDK_INT >= 19) {
                TypedValue typedValue19 = new TypedValue();
                ListaMotosActivity.this.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue19, true);
                final int color = typedValue19.data;
                FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
                statusBar.setBackgroundColor(color);
            }

            // Fix issues for Lollipop, setting Status Bar color primary dark
            if (Build.VERSION.SDK_INT >= 21) {
                TypedValue typedValue21 = new TypedValue();
                ListaMotosActivity.this.getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue21, true);
                final int color = typedValue21.data;
                FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
                statusBar.setBackgroundColor(color);
            }
        }

        // Fix landscape issues (only Lollipop)
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (Build.VERSION.SDK_INT >= 19) {
                TypedValue typedValue19 = new TypedValue();
                ListaMotosActivity.this.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue19, true);
                final int color = typedValue19.data;
                FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
                statusBar.setBackgroundColor(color);
            }
            if (Build.VERSION.SDK_INT >= 21) {
                TypedValue typedValue = new TypedValue();
                ListaMotosActivity.this.getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
                final int color = typedValue.data;
                FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
                statusBar.setBackgroundColor(color);
            }
        }
    }


}