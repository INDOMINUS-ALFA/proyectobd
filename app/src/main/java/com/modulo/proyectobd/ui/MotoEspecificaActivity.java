package com.modulo.proyectobd.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.modulo.proyectobd.Geolocalizacion.LocalizacionActivity;
import com.modulo.proyectobd.R;
import com.modulo.proyectobd.StatsValueFormatter;
import com.modulo.proyectobd.ViewModel;
import com.modulo.proyectobd.basesDatos.models.Moto;
import com.modulo.proyectobd.controlador.PagerControler;
import com.modulo.proyectobd.settings.MySettings;

import java.util.ArrayList;

public class MotoEspecificaActivity extends AppCompatActivity {
    private ViewModel myMotoViewModel;
    private RadarChart statsChart;
    Moto mimoto;
    Context context;

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
        setContentView(R.layout.activity_moto_especifica);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        navigationBarStatusBar();

        myMotoViewModel = new ViewModelProvider(this).get(ViewModel.class);

        String nombre = getIntent().getStringExtra("nombre");
        myMotoViewModel.motoSelecionada(nombre);
        int miniatura = myMotoViewModel.motoSelecionada(nombre).getMiniatura();
        Drawable drawable = getDrawable(miniatura);
        ImageView imagen = findViewById(R.id.motominiatura);
        imagen.setImageDrawable(drawable);



        String nombremoto = myMotoViewModel.motoSelecionada(nombre).getNombre();
        TextView nom = findViewById(R.id.nombre_de_moto);
        nom.setText(nombremoto);

        int cilindrada = myMotoViewModel.motoSelecionada(nombre).getCilindrada();
        TextView cilin = findViewById(R.id.cilindrada_moto);
        cilin.setText(cilindrada + "CC");



        mimoto = new Moto();

        mimoto.setAnio(myMotoViewModel.motoSelecionada(nombre).getAnio());
        mimoto.setCilindros(myMotoViewModel.motoSelecionada(nombre).getCilindros());
        mimoto.setParMotor( myMotoViewModel.motoSelecionada(nombre).getParMotor());
        mimoto.setPeso( myMotoViewModel.motoSelecionada(nombre).getPeso());
        mimoto.setPrecio( myMotoViewModel.motoSelecionada(nombre).getPrecio());
        mimoto.setSuspension(myMotoViewModel.motoSelecionada(nombre).getSuspension());
        mimoto.setMotor(myMotoViewModel.motoSelecionada(nombre).getMotor());
        mimoto.setFrenos( myMotoViewModel.motoSelecionada(nombre).getFrenos());
        mimoto.setEspecificaciones(myMotoViewModel.motoSelecionada(nombre).getEspecificaciones());
        mimoto.setCategoria(myMotoViewModel.motoSelecionada(nombre).getCategoria());
        mimoto.setMarca(myMotoViewModel.motoSelecionada(nombre).getMarca());
        mimoto.setTipo_carnet(myMotoViewModel.motoSelecionada(nombre).getTipo_carnet());
        mimoto.setHistoria(myMotoViewModel.motoSelecionada(nombre).getHistoria());



            statsChart = findViewById(R.id.motostats);
            statsChart.setLogEnabled(false);



        TabLayout tabla = findViewById(R.id.tablayout1);
        ViewPager view = findViewById(R.id.viewPager);

        TabItem caracteristicas = findViewById(R.id.tablaCaracteristicas);
        TabItem historia = findViewById(R.id.tablaHistoria);
        configureInitialUIValues();
        updateUI();




        PagerControler myControler = new PagerControler(getSupportFragmentManager(), tabla.getTabCount(), mimoto);
        view.setAdapter(myControler);
        tabla.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                view.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    myControler.notifyDataSetChanged();
                }
                if (tab.getPosition() == 1) {
                    myControler.notifyDataSetChanged();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        view.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabla));
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }


    private void configureInitialUIValues() {


        statsChart.getDescription().setEnabled(false);
        statsChart.setWebLineWidth(1f);
        statsChart.setWebColor(Color.LTGRAY);
        statsChart.setWebLineWidthInner(1f);
        statsChart.setWebColorInner(Color.LTGRAY);
        statsChart.setWebAlpha(100);
        statsChart.setTouchEnabled(false);

        MarkerView mv = new MarkerView(this, R.layout.fragment_caracteristicas);
        statsChart.setMarker(mv);

        statsChart.setRotationEnabled(false);

        XAxis xAxis = statsChart.getXAxis();
        xAxis.setTextSize(5f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);

        xAxis.setTextColor(Color.BLACK);

        YAxis yAxis = statsChart.getYAxis();
        yAxis.setLabelCount(5, false);
        yAxis.setTextSize(5f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(100f);
        yAxis.setDrawLabels(false);
        xAxis.setValueFormatter(new StatsValueFormatter());

        Legend l = statsChart.getLegend();
        l.setEnabled(false);
    }

    private void updateStatsChart() {

        ArrayList<RadarEntry> radarStats = new ArrayList<>();

        String nombre = getIntent().getStringExtra("nombre");
        int manejo = myMotoViewModel.motoSelecionada(nombre).getManejo();
        int aceleracion = myMotoViewModel.motoSelecionada(nombre).getManejo();
        int frenado = myMotoViewModel.motoSelecionada(nombre).getFrenado();
        int punta = myMotoViewModel.motoSelecionada(nombre).getVelPunta();
        int aerodinamica = myMotoViewModel.motoSelecionada(nombre).getAerodinamica();


        radarStats.add(new RadarEntry(manejo));
        radarStats.add(new RadarEntry(aceleracion));
        radarStats.add(new RadarEntry(frenado));
        radarStats.add(new RadarEntry(punta));
        radarStats.add(new RadarEntry(aerodinamica));

        RadarDataSet set = new RadarDataSet(radarStats, "Estadisticas");
        //set.setColor(Color.rgb(103, 110, 129));
        set.setColor(Color.rgb(255, 0, 0));
        //set.setFillColor(Color.rgb(0, 0, 255));
        set.setFillColor(Color.parseColor("#ff0000"));
        set.setDrawFilled(true);
        set.setFillAlpha(100);
        set.setLineWidth(2f);
        set.setDrawHighlightCircleEnabled(false);
        set.setDrawHighlightIndicators(false);

        RadarData data = new RadarData(set);

        data.setValueTextSize(8f);
        data.setDrawValues(false);

        statsChart.setData(data);
        statsChart.invalidate();
    }

    public void updateUI() {
        updateStatsChart();
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
                MotoEspecificaActivity.this.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue19, true);
                final int color = typedValue19.data;
                FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
                statusBar.setBackgroundColor(color);
            }

            // Fix issues for Lollipop, setting Status Bar color primary dark
            if (Build.VERSION.SDK_INT >= 21) {
                TypedValue typedValue21 = new TypedValue();
                MotoEspecificaActivity.this.getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue21, true);
                final int color = typedValue21.data;
                FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
                statusBar.setBackgroundColor(color);
            }
        }

        // Fix landscape issues (only Lollipop)
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (Build.VERSION.SDK_INT >= 19) {
                TypedValue typedValue19 = new TypedValue();
                MotoEspecificaActivity.this.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue19, true);
                final int color = typedValue19.data;
                FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
                statusBar.setBackgroundColor(color);
            }
            if (Build.VERSION.SDK_INT >= 21) {
                TypedValue typedValue = new TypedValue();
                MotoEspecificaActivity.this.getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
                final int color = typedValue.data;
                FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
                statusBar.setBackgroundColor(color);
            }
        }
    }
}