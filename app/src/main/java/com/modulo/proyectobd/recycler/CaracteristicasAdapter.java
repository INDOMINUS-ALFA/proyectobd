package com.modulo.proyectobd.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.modulo.proyectobd.R;
import com.modulo.proyectobd.basesDatos.models.Moto;

import java.util.ArrayList;

public class CaracteristicasAdapter extends RecyclerView.Adapter<CaracteristicasAdapter.CaracteristicasAdapter.ViewHolderData>{


        private ArrayList<Moto> motocaract;
        private Context context;

        public CaracteristicasAdapter(Context context, ArrayList<Moto> motocaract) {
            this.context = context;
            this.motocaract = motocaract;

        }

        @NonNull
        @Override
        public ViewHolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.ability_list_item, parent, false);
            return new ViewHolderData(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolderData holder, int position) {
            holder.assignData(abilities.get(position));
        }

        @Override
        public int getItemCount() {
            return abilities.size();
        }

        public class ViewHolderData extends RecyclerView.ViewHolder implements View.OnClickListener {

            private TextView Caracteristica, Descripcion;


            public ViewHolderData(@NonNull View itemView) {
                super(itemView);
                Caracteristica = itemView.findViewById(R.id.heroAbilities_listView_name);
                Descripcion = itemView.findViewById(R.id.heroAbilities_listView_power);



                itemView.setOnClickListener(this);
            }

            public void assignData(Moto moto) {
                Caracteristica.setText();
                Descripcion.setText();

            }


        }


    }
}
