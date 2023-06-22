package com.example.proyectomovil.notas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectomovil.R;
import com.example.proyectomovil.db.DbNotas;
import com.example.proyectomovil.entidades.Notas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarNotasActivity extends AppCompatActivity {

    EditText txtNota, txtId_materia;
    Button btnGuardarNota;
    FloatingActionButton fabEditar, fabEliminar;
    boolean correcto=false;

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
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar=findViewById(R.id.fabEliminar);
        fabEliminar.setVisibility(View.INVISIBLE);

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
        DbNotas dbNotas = new DbNotas(EditarNotasActivity.this);
        nota = dbNotas.verNotas(id);

        if(nota != null){
            txtNota.setText(String.valueOf(nota.getNota()));
            txtId_materia.setText(String.valueOf(nota.getId_materia()));

        }
        btnGuardarNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtNota.getText().toString().equals("") && !txtId_materia.getText().toString().equals("") ){
                    int nota = Integer.parseInt(txtNota.getText().toString());
                    int id_materia = Integer.parseInt(txtId_materia.getText().toString());
                    correcto = dbNotas.editarNota(id, nota, id_materia);

                    if(correcto){
                        Toast.makeText(EditarNotasActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(EditarNotasActivity.this, "ERROR AL MODIFICAR EL REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else{
                    Toast.makeText(EditarNotasActivity.this, "DEBE LLENAR TODOS LOS CAMPOS", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    private  void verRegistro(){
        Intent intent = new Intent(this, VerNotasActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }

}