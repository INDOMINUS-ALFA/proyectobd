package com.modulo.proyectobd.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.modulo.proyectobd.basesDatos.models.Marca;
import com.modulo.proyectobd.R;
import com.modulo.proyectobd.recycler.MarcaRecyclerviewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Marca> lstmarca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstmarca = new ArrayList<>();
        lstmarca.add(new Marca("Aprilia", R.drawable.aprilia));
        lstmarca.add(new Marca("Kawasaki", R.drawable.kawasaki));
        lstmarca.add(new Marca("Yamaha", R.drawable.yamaha));
        lstmarca.add(new Marca("Suzuki", R.drawable.suzuki));
        lstmarca.add(new Marca("BMW", R.drawable.bmw));



        RecyclerView my_rw = (RecyclerView) findViewById(R.id.recyclerview_id);
        MarcaRecyclerviewAdapter my_adapter = new MarcaRecyclerviewAdapter(this,lstmarca);

        my_rw.setLayoutManager(new GridLayoutManager(this,3));
        my_rw.setAdapter(my_adapter);
    }
}