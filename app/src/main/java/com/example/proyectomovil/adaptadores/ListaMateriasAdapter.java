package com.example.proyectomovil.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectomovil.R;
import com.example.proyectomovil.materias.VerActivity;
import com.example.proyectomovil.entidades.Materias;

import java.util.ArrayList;

public class ListaMateriasAdapter extends RecyclerView.Adapter<ListaMateriasAdapter.MateriaViewHolder> {
    ArrayList<Materias> listaMaterias;
    public ListaMateriasAdapter(ArrayList<Materias> listaMaterias){
        this.listaMaterias=listaMaterias;
    }
    @NonNull
    @Override
    public MateriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_materia, null, false);
        return new MateriaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MateriaViewHolder holder, int position) {
        holder.viewId.setText(String.valueOf(listaMaterias.get(position).getId()));
        holder.viewNombre.setText(listaMaterias.get(position).getNombre());
        holder.viewPeriodo.setText(listaMaterias.get(position).getPeriodo());
        holder.viewDias.setText(listaMaterias.get(position).getDias());
        holder.viewHoraInicio.setText(listaMaterias.get(position).getHora_inicio());
        holder.viewHoraFin.setText(listaMaterias.get(position).getHora_fin());
    }

    @Override
    public int getItemCount() {
        return listaMaterias.size();
    }

    public class MateriaViewHolder extends RecyclerView.ViewHolder {
        TextView viewNombre, viewPeriodo, viewDias, viewHoraInicio, viewHoraFin, viewId;
        public MateriaViewHolder(@NonNull View itemView) {
            super(itemView);
            viewId=itemView.findViewById(R.id.viewId);
            viewNombre=itemView.findViewById(R.id.viewNombre);
            viewPeriodo=itemView.findViewById(R.id.viewPeriodo);
            viewDias=itemView.findViewById(R.id.viewDias);
            viewHoraInicio=itemView.findViewById(R.id.viewHoraInicio);
            viewHoraFin=itemView.findViewById(R.id.viewHoraFin);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID", listaMaterias.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
