package com.example.proyectomovil.materias;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.proyectomovil.R;
import com.example.proyectomovil.db.DbMaterias;
import com.example.proyectomovil.entidades.Materias;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerActivity extends AppCompatActivity {

    EditText txtNombre, txtPeriodo, txtDias, txtHoraInicio, txtHoraFin;
    Button btnGuardarMat;

    FloatingActionButton fabEditar, fabEliminar;
    Materias materia;
    int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtNombre=findViewById(R.id.txtNombre);
        txtPeriodo=findViewById(R.id.txtPeriodo);
        txtDias=findViewById(R.id.txtDias);
        txtHoraInicio=findViewById(R.id.txtHoraInicio);
        txtHoraFin=findViewById(R.id.txtHoraFin);
        btnGuardarMat=findViewById(R.id.btnGuardarMat);
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
        DbMaterias dbMaterias = new DbMaterias(VerActivity.this);
        materia = dbMaterias.verMateria(id);

        if(materia != null){
            txtNombre.setText(materia.getNombre());
            txtPeriodo.setText(materia.getPeriodo());
            txtDias.setText(materia.getDias());
            txtHoraInicio.setText(materia.getHora_inicio());
            txtHoraFin.setText(materia.getHora_fin());
            btnGuardarMat.setVisibility(View.INVISIBLE);
            txtNombre.setInputType(InputType.TYPE_NULL);
            txtPeriodo.setInputType(InputType.TYPE_NULL);
            txtDias.setInputType(InputType.TYPE_NULL);
            txtHoraInicio.setInputType(InputType.TYPE_NULL);
            txtHoraFin.setInputType(InputType.TYPE_NULL);
        }

        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerActivity.this, EditarActivity.class);
                intent.putExtra("ID",id);
                startActivity(intent);
            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerActivity.this);
                builder.setMessage("Desea eliminar Materia?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(dbMaterias.eliminarMateria(id)){
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
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}