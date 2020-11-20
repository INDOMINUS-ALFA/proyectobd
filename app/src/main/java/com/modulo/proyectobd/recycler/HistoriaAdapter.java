package com.modulo.proyectobd.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.modulo.proyectobd.R;
import com.modulo.proyectobd.basesDatos.models.Moto;

public class HistoriaAdapter extends RecyclerView.Adapter<HistoriaAdapter.ViewHolderHistoria>{

    Moto mimoto;

    public HistoriaAdapter(Moto mimoto) {
        this.mimoto = mimoto;
    }

    @NonNull
    @Override
    public ViewHolderHistoria onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_historia, null, false);
        return new ViewHolderHistoria(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderHistoria holder, int position) {
        holder.historia.setText(mimoto.getHistoria());


    }



    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolderHistoria extends RecyclerView.ViewHolder {
        TextView historia ;



        public ViewHolderHistoria(@NonNull View itemView) {
            super(itemView);

            historia = (TextView) itemView.findViewById(R.id.historia_moto);

        }
    }
}
