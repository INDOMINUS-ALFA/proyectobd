package com.modulo.proyectobd.recycler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.modulo.proyectobd.R;
import com.modulo.proyectobd.basesDatos.models.Marca;
import com.modulo.proyectobd.basesDatos.models.Moto;
import com.modulo.proyectobd.basesDatos.models.MotoMinimal;
import com.modulo.proyectobd.ui.ListaMotosActivity;
import com.modulo.proyectobd.ui.MotoEspecificaActivity;

import java.util.List;

public class MotoRecyclerviewAdapter extends RecyclerView.Adapter<MotoRecyclerviewAdapter.MyViewHolderMoto> {
    private Context mContextmoto;
    private List<MotoMinimal> mDatamoto;

    public MotoRecyclerviewAdapter(Context mContext, List<MotoMinimal> mData) {
        this.mContextmoto = mContext;
        this.mDatamoto = mData;


    }



    @NonNull
    @Override
    public MyViewHolderMoto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContextmoto);
        view= mInflater.inflate(R.layout.cardview_item_motos, parent, false);
        return new MyViewHolderMoto(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderMoto holder, final int position) {
        holder.nombre_moto.setText(mDatamoto.get(position).getNombre());
        holder.motos_img.setImageResource(mDatamoto.get(position).getMiniatura());
        //set click listener
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(mContextmoto, MotoEspecificaActivity.class);
                String nombre = (String) holder.nombre_moto.getText();
                intent.putExtra("nombre", nombre);
                mContextmoto.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {

        return mDatamoto.size();
    }

    public static class MyViewHolderMoto extends RecyclerView.ViewHolder{
            TextView nombre_moto;
            ImageView motos_img;
            CardView cardView;

        public MyViewHolderMoto(@NonNull View itemView) {

            super(itemView);
            nombre_moto = (TextView) itemView.findViewById(R.id.nombre_moto_id);
            motos_img = (ImageView) itemView.findViewById(R.id.motos_img_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_motos_id);
        }
    }
}
