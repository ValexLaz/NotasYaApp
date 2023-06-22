package com.example.proyectomovil.notas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectomovil.R;
import com.example.proyectomovil.db.DbNotas;
import com.example.proyectomovil.entidades.Notas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerNotasActivity extends AppCompatActivity {

    EditText txtNota, txtId_materia;
    Button btnGuardarNota;
    FloatingActionButton fabEditar, fabEliminar;

    Notas nota;
    int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_notas);

        txtNota=findViewById(R.id.txtNota);
        txtId_materia=findViewById(R.id.txtId_materia);
        btnGuardarNota=findViewById(R.id.btnGuardarNota);
        fabEditar=findViewById(R.id.fabEditar);
        fabEliminar=findViewById(R.id.fabEliminar);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id= Integer.parseInt(null);
            } else{
                id = extras.getInt("ID");
            }
        } else {
            id =(int) savedInstanceState.getSerializable("ID");
        }
        DbNotas dbNotas = new DbNotas(VerNotasActivity.this);
        nota = dbNotas.verNotas(id);

        if(nota != null){
            txtNota.setText(String.valueOf(nota.getNota()));
            txtId_materia.setText(String.valueOf(nota.getId_materia()));

            btnGuardarNota.setVisibility(View.INVISIBLE);
            txtNota.setInputType(InputType.TYPE_NULL);
            txtId_materia.setInputType(InputType.TYPE_NULL);
        }
        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerNotasActivity.this, EditarNotasActivity.class);
                intent.putExtra("ID",id);
                startActivity(intent);
            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerNotasActivity.this);
                builder.setMessage("Desea eliminar Materia?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(dbNotas.eliminarNota(id)){
                                    lista();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
            }
        });


    }
    private void lista(){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}