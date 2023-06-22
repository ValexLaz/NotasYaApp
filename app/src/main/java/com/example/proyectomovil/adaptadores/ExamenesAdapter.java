package com.example.proyectomovil.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectomovil.R;
import com.example.proyectomovil.entidades.Examenes;
import com.example.proyectomovil.examenes.MostrarExamenesActivity;

import java.util.List;

public class ExamenesAdapter extends RecyclerView.Adapter<ExamenesAdapter.ExamenesViewHolder> {
    private List<Examenes> ExamenesList;

    public ExamenesAdapter(List<Examenes> ExamenesList) {
        this.ExamenesList = ExamenesList;
    }

    @NonNull
    @Override
    public ExamenesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_examen, parent, false);
        return new ExamenesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamenesViewHolder holder, int position) {
        Examenes examenes = ExamenesList.get(position);

        holder.materiaTextView.setText(examenes.getMateria());
        holder.fechaTextView.setText(examenes.getFecha());
        holder.horaTextView.setText(examenes.getHora());
    }

    @Override
    public int getItemCount() {
        return ExamenesList.size();
    }

    public static class ExamenesViewHolder extends RecyclerView.ViewHolder {
        public TextView materiaTextView;
        public TextView fechaTextView;
        public TextView horaTextView;

        public ExamenesViewHolder(@NonNull View itemView) {
            super(itemView);
            materiaTextView = itemView.findViewById(R.id.text_view_materia);
            fechaTextView = itemView.findViewById(R.id.text_view_fecha);
            horaTextView = itemView.findViewById(R.id.text_view_hora);
        }
    }
}

