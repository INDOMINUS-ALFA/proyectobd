package com.modulo.proyectobd.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.modulo.proyectobd.R;
import com.modulo.proyectobd.ViewModel;
import com.modulo.proyectobd.controlador.PagerControler;

import java.lang.reflect.Field;

public class MotoEspecificaActivity extends AppCompatActivity {
    private ViewModel myMotoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moto_especifica);
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
        cilin.setText(cilindrada + "");





        TabLayout tabla = findViewById(R.id.tablayout1);
        ViewPager view = findViewById(R.id.viewPager);

        TabItem caracteristicas = findViewById(R.id.tablaCaracteristicas);
        TabItem historia = findViewById(R.id.tablaHistoria);





        PagerControler myControler = new PagerControler(getSupportFragmentManager(), tabla.getTabCount());
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
}