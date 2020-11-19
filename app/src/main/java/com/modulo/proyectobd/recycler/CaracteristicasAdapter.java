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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CaracteristicasAdapter extends RecyclerView.Adapter<CaracteristicasAdapter.ViewHolderCaracteristicas>{

    Moto mimoto;
    String[]  nombres = new String[]{"año", "cilindros","Par Motor", "Peso","Precio", "Suspendion", "Motor", "Frenos", "Especificaciones", "Categoria", "Marca", "Tipo Carnet"  };

    public CaracteristicasAdapter(Moto mimoto) {
        this.mimoto = mimoto;
    }

    @NonNull
    @Override
    public CaracteristicasAdapter.ViewHolderCaracteristicas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recicler_especificaciones, null, false);
        return new ViewHolderCaracteristicas(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCaracteristicas holder, int position) {
        holder.nombre.setText(nombres[position]);
        switch (position) {
            case 0:

                    holder.descripcion.setText(mimoto.getAnio() + "");
                    break;

            case 1:
                    holder.descripcion.setText(mimoto.getCilindros() + "");
                    break;

            case 2:
                holder.descripcion.setText(mimoto.getParMotor() + "");
                break;
            case 3:
                holder.descripcion.setText(mimoto.getPeso()+"");
                break;

            case 4:
                holder.descripcion.setText(mimoto.getPrecio()+"");
                break;
            case 5:
                holder.descripcion.setText(mimoto.getSuspension());
                break;
            case 6:
                holder.descripcion.setText(mimoto.getMotor());
                break;
            case 7:
                holder.descripcion.setText(mimoto.getFrenos());
                break;
            case 8:
                holder.descripcion.setText(mimoto.getEspecificaciones());
                break;
            case 9:
                holder.descripcion.setText(mimoto.getCategoria());
                break;
            case 10:
                holder.descripcion.setText(mimoto.getMarca());
                break;
            case 11:
                holder.descripcion.setText(mimoto.getTipo_carnet());
                break;
        }
    }



    @Override
    public int getItemCount() {
        return nombres.length;
    }

    public class ViewHolderCaracteristicas extends RecyclerView.ViewHolder {
        TextView nombre ;
        TextView descripcion;


        public ViewHolderCaracteristicas(@NonNull View itemView) {
            super(itemView);

            nombre = (TextView) itemView.findViewById(R.id.nombre);
            descripcion = (TextView) itemView.findViewById(R.id.descripcion);
        }
    }
}
