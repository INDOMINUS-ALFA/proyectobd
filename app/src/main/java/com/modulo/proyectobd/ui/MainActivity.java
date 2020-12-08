package com.modulo.proyectobd.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
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

import com.modulo.proyectobd.basesDatos.models.Marca;
import com.modulo.proyectobd.R;
import com.modulo.proyectobd.recycler.MarcaRecyclerviewAdapter;
import com.modulo.proyectobd.settings.MySettings;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Marca> lstmarca;
    private Context context;




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
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        navigationBarStatusBar();

        lstmarca = new ArrayList<>();
        lstmarca.add(new Marca("Aprilia", R.drawable.aprilia));
        lstmarca.add(new Marca("Kawasaki", R.drawable.kawasaki));
        lstmarca.add(new Marca("Yamaha", R.drawable.yamaha));
        lstmarca.add(new Marca("Suzuki", R.drawable.suzuki));
        lstmarca.add(new Marca("BMW", R.drawable.bmw));
        lstmarca.add(new Marca("KTM", R.drawable.ktm));
        lstmarca.add(new Marca("Honda", R.drawable.honda));
        lstmarca.add(new Marca("Harley Davison", R.drawable.harley));
        lstmarca.add(new Marca("Triumph", R.drawable.triumph));
        lstmarca.add(new Marca("MV Augustav", R.drawable.mv_augustav));
        lstmarca.add(new Marca ("Benelli", R.drawable.benelli));
        lstmarca.add(new Marca("Ducati", R.drawable.ducati));
        lstmarca.add(new Marca("Husqvarna", R.drawable.husqvarna));
        lstmarca.add(new Marca("Peugeot", R.drawable.peugeot));
        lstmarca.add(new Marca("Piaggio", R.drawable.piaggio));
        lstmarca.add(new Marca("Royal Enfield", R.drawable.royal_enfield));




        RecyclerView my_rw = (RecyclerView) findViewById(R.id.recyclerview_id);
        MarcaRecyclerviewAdapter my_adapter = new MarcaRecyclerviewAdapter(this,lstmarca);

        my_rw.setLayoutManager(new GridLayoutManager(this,3));
        my_rw.setAdapter(my_adapter);
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
            Intent intent = new Intent(this, MySettings.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void navigationBarStatusBar() {

        // Fix portrait issues
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            // Fix issues for KitKat setting Status Bar color primary
            if (Build.VERSION.SDK_INT >= 19) {
                TypedValue typedValue19 = new TypedValue();
                MainActivity.this.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue19, true);
                final int color = typedValue19.data;
                FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
                statusBar.setBackgroundColor(color);
            }

            // Fix issues for Lollipop, setting Status Bar color primary dark
            if (Build.VERSION.SDK_INT >= 21) {
                TypedValue typedValue21 = new TypedValue();
                MainActivity.this.getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue21, true);
                final int color = typedValue21.data;
                FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
                statusBar.setBackgroundColor(color);
            }
        }

        // Fix landscape issues (only Lollipop)
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (Build.VERSION.SDK_INT >= 19) {
                TypedValue typedValue19 = new TypedValue();
                MainActivity.this.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue19, true);
                final int color = typedValue19.data;
                FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
                statusBar.setBackgroundColor(color);
            }
            if (Build.VERSION.SDK_INT >= 21) {
                TypedValue typedValue = new TypedValue();
                MainActivity.this.getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
                final int color = typedValue.data;
                FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
                statusBar.setBackgroundColor(color);
            }
        }
    }

}