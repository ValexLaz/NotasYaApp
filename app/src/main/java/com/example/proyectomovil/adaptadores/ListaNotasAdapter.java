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
import com.example.proyectomovil.notas.VerNotasActivity;
import com.example.proyectomovil.entidades.Notas;

import java.util.ArrayList;

public class ListaNotasAdapter extends RecyclerView.Adapter<ListaNotasAdapter.NotasViewHolder> {
    ArrayList<Notas> listaNotas;
    public ListaNotasAdapter(ArrayList<Notas> listaNotas){
        this.listaNotas = listaNotas;
    }
    @NonNull
    @Override
    public NotasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_nota, null, false);
        return new NotasViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull NotasViewHolder holder, int position) {
        holder.viewNota.setText(String.valueOf(listaNotas.get(position).getNota()));
        holder.viewId_materia.setText(String.valueOf(listaNotas.get(position).getId_materia()));
    }


    @Override
    public int getItemCount() {
        return listaNotas.size();
    }
    public void setListaNotas(ArrayList<Notas> listaNotas) {
        this.listaNotas = listaNotas;
        notifyDataSetChanged();
    }

    public class NotasViewHolder extends RecyclerView.ViewHolder {

        TextView viewNota, viewId_materia;
        public NotasViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNota = itemView.findViewById(R.id.viewNota);
            viewId_materia=itemView.findViewById(R.id.viewId_materia);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerNotasActivity.class);
                    intent.putExtra("ID", listaNotas.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
