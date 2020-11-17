package com.modulo.proyectobd.ui;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Query;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.modulo.proyectobd.basesDatos.models.Marca;
import com.modulo.proyectobd.basesDatos.models.Moto;
import com.modulo.proyectobd.basesDatos.models.MotoDao;
import com.modulo.proyectobd.recycler.MarcaRecyclerviewAdapter;
import com.modulo.proyectobd.recycler.MotoRecyclerviewAdapter;
import com.modulo.proyectobd.recycler.MotoSViewHolder;
import com.modulo.proyectobd.R;
import com.modulo.proyectobd.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListaMotosActivity extends AppCompatActivity {
    private List<Moto> MotoList;
    private ViewModel myMotoViewModel;
    MotoDao mMotoDao;

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

            myMotoViewModel.getAllMotos().observe(this, motos -> {
                adapter.submitList(motos);



                String marca = getIntent().getStringExtra("marca");
                mMotoDao.getAlphabetizedMotos(marca);
                RecyclerView my_rw = (RecyclerView) findViewById(R.id.recyclerviewmoto_id);
                MotoRecyclerviewAdapter my_adapter = new MotoRecyclerviewAdapter(this,MotoList);

                my_rw.setLayoutManager(new GridLayoutManager(this,3));
                my_rw.setAdapter(my_adapter);
                
        });



    }


}