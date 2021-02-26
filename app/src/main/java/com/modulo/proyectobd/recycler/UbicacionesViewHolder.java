package com.modulo.proyectobd.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.modulo.proyectobd.R;
import com.modulo.proyectobd.basesDatos.models.Ubicacion;

import java.util.List;

public class UbicacionesViewHolder extends RecyclerView.ViewHolder{

    TextView nombre;
    TextView latitud;
    TextView longitud;
    CardView cardView;

    private static Context ubicacionContext;
    private static List<Ubicacion> ubicacion_data;
    public UbicacionesViewHolder(View itemView) {
        super(itemView);
        nombre = itemView.findViewById(R.id.nombre_ubicacion);
        latitud = itemView.findViewById(R.id.latitud_coordenada);
        longitud = itemView.findViewById(R.id.longitud_coordenada);
        cardView = itemView.findViewById(R.id.cardview_ubicaciones_id);



    }



    public static class UbicacionListAdapter extends ListAdapter<Ubicacion, UbicacionesViewHolder> {

            public UbicacionListAdapter(@NonNull DiffUtil.ItemCallback<Ubicacion> diffCallback) {
                super(diffCallback);

            }

            @Override
            public UbicacionesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View view;
                LayoutInflater mInflater = LayoutInflater.from(ubicacionContext);
                view= mInflater.inflate(R.layout.activity_ubicaciones, parent, false);
                return new UbicacionesViewHolder(view);
            }


            @Override
            public void onBindViewHolder(@NonNull UbicacionesViewHolder holder, int position) {


                holder.nombre.setText(ubicacion_data.get(position).getNombre());
                holder.latitud.setText(Double.toString(ubicacion_data.get(position).getLatitud()));
                holder.longitud.setText(Double.toString(ubicacion_data.get(position).getLongitud()));
                //set click listener
                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }


        public static class WordDiff extends DiffUtil.ItemCallback<Ubicacion> {

                @Override
                public boolean areItemsTheSame(@NonNull Ubicacion oldItem, @NonNull Ubicacion newItem) {
                    return oldItem == newItem;
                }

                @Override
                public boolean areContentsTheSame(@NonNull Ubicacion oldItem, @NonNull Ubicacion newItem) {
                    return oldItem.getNombre().equals(newItem.getNombre());
                }
            }
        }
}









