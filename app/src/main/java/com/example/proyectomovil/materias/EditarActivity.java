package com.example.proyectomovil.materias;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectomovil.R;
import com.example.proyectomovil.db.DbMaterias;
import com.example.proyectomovil.entidades.Materias;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarActivity extends AppCompatActivity {

    EditText txtNombre, txtPeriodo, txtDias, txtHoraInicio, txtHoraFin;
    Button btnGuardarMat;
    FloatingActionButton fabEditar, fabEliminar;
    Boolean correcto = false;
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
        DbMaterias dbMaterias = new DbMaterias(EditarActivity.this);
        materia = dbMaterias.verMateria(id);

        if(materia != null){
            txtNombre.setText(materia.getNombre());
            txtPeriodo.setText(materia.getPeriodo());
            txtDias.setText(materia.getDias());
            txtHoraInicio.setText(materia.getHora_inicio());
            txtHoraFin.setText(materia.getHora_fin());

        }

        btnGuardarMat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtNombre.getText().toString().equals("") && !txtPeriodo.getText().toString().equals("") && !txtDias.getText().toString().equals("") && !txtHoraInicio.getText().toString().equals("") && !txtHoraFin.getText().toString().equals("")){
                    correcto = dbMaterias.editarMateria(id,txtNombre.getText().toString(),txtPeriodo.getText().toString(),txtDias.getText().toString(),
                            txtHoraInicio.getText().toString(),txtHoraFin.getText().toString() );
                    if(correcto){
                        Toast.makeText(EditarActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(EditarActivity.this, "ERROR AL MODIFICAR EL REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else{
                    Toast.makeText(EditarActivity.this, "DEBE LLENAR TODOS LOS CAMPOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private  void verRegistro(){
        Intent intent = new Intent(this, VerActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}
