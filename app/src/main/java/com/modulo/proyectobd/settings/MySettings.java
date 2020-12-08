package com.modulo.proyectobd.settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.modulo.proyectobd.R;
import com.modulo.proyectobd.ui.MainActivity;

public class MySettings extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Button DeafultButton, Custom1, Custom2, Custom3;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        sharedPreferences = getSharedPreferences("VALUES" , MODE_PRIVATE);
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


        setContentView(R.layout.my_settings);


        // Setup Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Fix issues of navigation and status bars
        navigationBarStatusBar();

        // Setup buttons to apply themes.
        setMyTheme();

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



    @Override
    protected void onPause() {
        super.onPause();


    }

    public void navigationBarStatusBar() {

        // Fix portrait issues
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            // Fix issues for KitKat setting Status Bar color primary
            if (Build.VERSION.SDK_INT >= 19) {
                TypedValue typedValue19 = new TypedValue();
                MySettings.this.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue19, true);
                final int color = typedValue19.data;
                FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
                statusBar.setBackgroundColor(color);
            }

            // Fix issues for Lollipop, setting Status Bar color primary dark
            if (Build.VERSION.SDK_INT >= 21) {
                TypedValue typedValue21 = new TypedValue();
                MySettings.this.getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue21, true);
                final int color = typedValue21.data;
                FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
                statusBar.setBackgroundColor(color);
            }
        }

        // Fix landscape issues (only Lollipop)
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (Build.VERSION.SDK_INT >= 19) {
                TypedValue typedValue19 = new TypedValue();
                MySettings.this.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue19, true);
                final int color = typedValue19.data;
                FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
                statusBar.setBackgroundColor(color);
            }
            if (Build.VERSION.SDK_INT >= 21) {
                TypedValue typedValue = new TypedValue();
                MySettings.this.getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
                final int color = typedValue.data;
                FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
                statusBar.setBackgroundColor(color);
            }
        }
    }

    private void setMyTheme() {
        DeafultButton = (Button) findViewById(R.id.defaulttema);
        DeafultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().putInt("THEME",1).apply();
                Intent intent = new Intent(MySettings.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Custom1 = (Button) findViewById(R.id.buttonRed);
        Custom1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().putInt("THEME",2).apply();
                Intent intent = new Intent(MySettings.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Custom2 = (Button) findViewById(R.id.buttonIndigo);
        Custom2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().putInt("THEME",3).apply();
                Intent intent = new Intent(MySettings.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Custom3 = (Button) findViewById(R.id.buttontema3);
        Custom3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().putInt("THEME",4).apply();
                Intent intent = new Intent(MySettings.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}