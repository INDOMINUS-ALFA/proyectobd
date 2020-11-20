package com.modulo.proyectobd.ui;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Query;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.modulo.proyectobd.basesDatos.models.Marca;
import com.modulo.proyectobd.basesDatos.models.Moto;
import com.modulo.proyectobd.basesDatos.models.MotoDao;
import com.modulo.proyectobd.basesDatos.models.MotoMinimal;
import com.modulo.proyectobd.recycler.MarcaRecyclerviewAdapter;
import com.modulo.proyectobd.recycler.MotoRecyclerviewAdapter;
import com.modulo.proyectobd.recycler.MotoSViewHolder;
import com.modulo.proyectobd.R;
import com.modulo.proyectobd.ViewModel;
import com.modulo.proyectobd.settings.MySettings;

import java.util.ArrayList;
import java.util.List;

public class ListaMotosActivity extends AppCompatActivity {
    private ViewModel myMotoViewModel;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_motos);



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

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id  = item.getItemId();

        if(id == R.id.settings_itm){
            Intent intent3 = new Intent(this, MySettings.class);
            startActivity(intent3);
        }
        return super.onOptionsItemSelected(item);
    }


}