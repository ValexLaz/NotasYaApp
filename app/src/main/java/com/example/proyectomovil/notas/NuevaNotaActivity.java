package com.example.proyectomovil.notas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectomovil.R;
import com.example.proyectomovil.db.DbNotas;

public class NuevaNotaActivity extends AppCompatActivity {
    EditText txtNota;
    EditText txtId_materia;
    Button btnGuardarNota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_nota);

        txtNota=findViewById(R.id.txtNota);
        txtId_materia=findViewById(R.id.txtId_materia);
        btnGuardarNota= findViewById(R.id.btnGuardarNota);


        btnGuardarNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbNotas dbNotas = new DbNotas(NuevaNotaActivity.this);
                int nota = Integer.parseInt(txtNota.getText().toString());
                int id_materia = Integer.parseInt(txtId_materia.getText().toString());
                long id = dbNotas.insertarNota(nota, id_materia);


                if(id>0){
                    Toast.makeText(NuevaNotaActivity.this, "Registro guardado", Toast.LENGTH_LONG).show();
                    limpiar();

                }else{
                    Toast.makeText(NuevaNotaActivity.this, "Error al guardar registro", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
    private void limpiar(){
        txtNota.setText("");
        txtId_materia.setText("");
    }
}