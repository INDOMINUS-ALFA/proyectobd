package com.modulo.proyectobd.Geolocalizacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.modulo.proyectobd.R;
import com.modulo.proyectobd.settings.MySettings;
import com.modulo.proyectobd.ui.MainActivity;
import com.modulo.proyectobd.ui.Ubicaciones_Activity;
import com.modulo.proyectobd.ui.Guardado_de_datos;

public class LocalizacionActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    private TextView tvLatitud, tvLongitud, tvAltura, tvPrecision;
    private LocationManager locManager;
    private Location loc;
    private Button guardar;

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

        setContentView(R.layout.activity_localizacion);
        tvLatitud = (TextView) findViewById(R.id.Latitud);
        tvLongitud = (TextView) findViewById(R.id.Longitud);
        tvAltura = (TextView) findViewById(R.id.Altura);
        tvPrecision = (TextView) findViewById(R.id.Precision);
        ActivityCompat.requestPermissions(LocalizacionActivity.this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            tvLatitud.setText("No se han definido los permisos necesarios.");
            tvLongitud.setText("");
            tvAltura.setText("");
            tvPrecision.setText("");

            return;
        } else {
        }
        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        tvLatitud.setText(String.valueOf(loc.getLatitude()));
        tvLongitud.setText(String.valueOf(loc.getLongitude()));
        tvAltura.setText(String.valueOf(loc.getAltitude()));
        tvPrecision.setText(String.valueOf(loc.getAccuracy()));


        guardar = (Button) findViewById(R.id.button) ;
        if (guardar != null) {
            guardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent enviarDatos = new Intent(LocalizacionActivity.this, Guardado_de_datos.class);
                    enviarDatos.putExtra("longitud", String.valueOf(tvLongitud));
                    enviarDatos.putExtra("latitud", String.valueOf(tvLatitud));
                    enviarDatos.putExtra("altura", String.valueOf(tvAltura));
                    enviarDatos.putExtra("preciosion", String.valueOf(tvPrecision));
                }
            });
        }
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
        } else if (id == R.id.home_itm){
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
                LocalizacionActivity.this.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue19, true);
                final int color = typedValue19.data;
                FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
                statusBar.setBackgroundColor(color);
            }

            // Fix issues for Lollipop, setting Status Bar color primary dark
            if (Build.VERSION.SDK_INT >= 21) {
                TypedValue typedValue21 = new TypedValue();
                LocalizacionActivity.this.getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue21, true);
                final int color = typedValue21.data;
                FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
                statusBar.setBackgroundColor(color);
            }
        }

        // Fix landscape issues (only Lollipop)
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (Build.VERSION.SDK_INT >= 19) {
                TypedValue typedValue19 = new TypedValue();
                LocalizacionActivity.this.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue19, true);
                final int color = typedValue19.data;
                FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
                statusBar.setBackgroundColor(color);
            }
            if (Build.VERSION.SDK_INT >= 21) {
                TypedValue typedValue = new TypedValue();
                LocalizacionActivity.this.getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
                final int color = typedValue.data;
                FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
                statusBar.setBackgroundColor(color);
            }
        }
    }

}