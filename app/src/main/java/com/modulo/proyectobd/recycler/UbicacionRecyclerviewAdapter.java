package com.modulo.proyectobd.recycler;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.modulo.proyectobd.R;
import com.modulo.proyectobd.basesDatos.models.MotoMinimal;
import com.modulo.proyectobd.basesDatos.models.Ubicacion;
import com.modulo.proyectobd.ui.MotoEspecificaActivity;
import com.modulo.proyectobd.ui.Ubicaciones_Activity;

import java.util.List;

public class UbicacionRecyclerviewAdapter extends RecyclerView.Adapter<UbicacionRecyclerviewAdapter.MyViewHolderUbicacion> {
    private Context mContextUbi;
    private List<Ubicacion> mDataUbicacion;

    public UbicacionRecyclerviewAdapter(Context mContext, List<Ubicacion> mData) {
        this.mContextUbi = mContext;
        this.mDataUbicacion = mData;


    }



    @NonNull
    @Override
    public MyViewHolderUbicacion onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContextUbi);
        view= mInflater.inflate(R.layout.activity_ubicaciones, parent, false);
        return new MyViewHolderUbicacion(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderUbicacion holder, final int position) {
        holder.nombre.setText(mDataUbicacion.get(position).getNombre());
        holder.latitud.setText(Double.toString(mDataUbicacion.get(position).getLatitud()));
        holder.longitud.setText(Double.toString(mDataUbicacion.get(position).getLongitud()));
        //set click listener
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(mContextUbi, Ubicaciones_Activity.class);
                String nombre = (String) holder.nombre.getText();
                String latitud = (String) holder.latitud.getText();
                String longitud = (String) holder.latitud.getText();
                intent.putExtra("nombre", nombre);
                intent.putExtra("latitud", latitud);
                intent.putExtra("longitud", longitud);
                mContextUbi.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {

        return mDataUbicacion.size();
    }

    public static class MyViewHolderUbicacion extends RecyclerView.ViewHolder{
        public View cardView;
            TextView nombre;
            TextView latitud;
            TextView longitud;

        public MyViewHolderUbicacion(@NonNull View itemView) {

            super(itemView);
            nombre =  itemView.findViewById(R.id.nombre_ubicacion);
            latitud =  itemView.findViewById(R.id.latitud_coordenada);
            longitud =  itemView.findViewById(R.id.longitud_coordenada);
            cardView =  itemView.findViewById(R.id.cardview_ubicaciones_id);
        }
    }
}
