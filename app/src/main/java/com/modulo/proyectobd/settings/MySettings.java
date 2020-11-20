package com.modulo.proyectobd.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.modulo.proyectobd.R;

public class MySettings extends AppCompatActivity {
Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_settings);


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

}