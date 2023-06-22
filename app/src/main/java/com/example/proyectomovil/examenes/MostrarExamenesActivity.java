package com.example.proyectomovil.examenes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.proyectomovil.R;
import com.example.proyectomovil.adaptadores.ExamenesAdapter;
import com.example.proyectomovil.db.DbHelper;
import com.example.proyectomovil.entidades.Examenes;

import java.util.ArrayList;
import java.util.List;

public class MostrarExamenesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ExamenesAdapter ExamenesAdapter;
    private List<Examenes> ExamenesList;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_mostrar_examenes);

        recyclerView = findViewById(R.id.recycler_view_examenes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DbHelper dbHelper = new DbHelper(this);
        database = dbHelper.getReadableDatabase();

        ExamenesList = new ArrayList<>();
        ExamenesAdapter = new ExamenesAdapter(ExamenesList);
        recyclerView.setAdapter(ExamenesAdapter);

        cargarExamenes();
    }

    private void cargarExamenes() {
        ExamenesList.clear();

        Cursor cursor = database.query("examenes", null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String materia = cursor.getString(cursor.getColumnIndex("materia"));
                String fecha = cursor.getString(cursor.getColumnIndex("fecha"));
                String hora = cursor.getString(cursor.getColumnIndex("hora"));

                Examenes examenes = new Examenes(materia, fecha, hora);
                ExamenesList.add(examenes);
            } while (cursor.moveToNext());
        }

        cursor.close();

        ExamenesAdapter.notifyDataSetChanged();
    }
}
