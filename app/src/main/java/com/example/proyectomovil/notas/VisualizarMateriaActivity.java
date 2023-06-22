package com.example.proyectomovil.notas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectomovil.R;
import com.example.proyectomovil.adaptadores.ListaNotasAdapter;
import com.example.proyectomovil.db.DbMaterias;
import com.example.proyectomovil.db.DbNotas;
import com.example.proyectomovil.entidades.Materias;
import com.example.proyectomovil.entidades.Notas;

import java.util.ArrayList;

public class VisualizarMateriaActivity extends AppCompatActivity {
    private DbMaterias dbMaterias;
    private DbNotas dbNotas;
    private EditText editTextIdMateria;
    private TextView materiaTextView;
    private RecyclerView notasRecyclerView;
    private TextView promedioTextView;
    private ListaNotasAdapter notasAdapter;
    Button btnMostrarDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_materia);

        dbMaterias = new DbMaterias(this);
        dbNotas = new DbNotas(this);

        editTextIdMateria = findViewById(R.id.editTextIdMateria);
        materiaTextView = findViewById(R.id.materiaTextView);
        notasRecyclerView = findViewById(R.id.notasRecyclerView);
        promedioTextView = findViewById(R.id.promedioTextView);
        btnMostrarDatos= findViewById(R.id.btnMostrarDatos);

        // Configura el RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        notasRecyclerView.setLayoutManager(layoutManager);
        notasAdapter = new ListaNotasAdapter(new ArrayList<>());
        notasRecyclerView.setAdapter(notasAdapter);

        // Configura el evento de clic para el botón "Mostrar Datos"
        btnMostrarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDatos();
            }
        });
    }

    private void mostrarDatos() {
        String materiaIdString = editTextIdMateria.getText().toString();
        int materiaId = Integer.parseInt(materiaIdString);
        mostrarMateria(materiaId);
        mostrarNotas(materiaId);
        mostrarPromedio(materiaId);
    }

    private void mostrarMateria(int materiaId) {
        Materias materia = dbMaterias.verMateria(materiaId);
        if (materia != null) {
            materiaTextView.setText(materia.getNombre());
        }
    }

    private void mostrarNotas(int materiaId) {
        ArrayList<Notas> notas = dbNotas.mostrarNotasPorMateria(materiaId);
        notasAdapter.setListaNotas(notas);
    }

    private void mostrarPromedio(int materiaId) {
        double promedio = dbNotas.obtenerPromedioPorMateria(materiaId);
        promedioTextView.setText("Promedio: " + promedio);
    }
}
