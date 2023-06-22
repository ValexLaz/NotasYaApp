package com.example.proyectomovil.notas;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectomovil.R;
import com.example.proyectomovil.adaptadores.ListaNotasAdapter;
import com.example.proyectomovil.db.DbNotas;
import com.example.proyectomovil.entidades.Notas;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    RecyclerView listaNotas;
    ArrayList<Notas> listaArrayNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listaNotas=findViewById(R.id.listaNotas);
        listaNotas.setLayoutManager(new LinearLayoutManager(this));

        DbNotas dbNotas = new DbNotas(MainActivity2.this);
        listaArrayNotas = new ArrayList<>();

        ListaNotasAdapter adapter = new ListaNotasAdapter(dbNotas.mostrarNotas());
        listaNotas.setAdapter(adapter);

    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_nuevo:
                nuevoRegistro();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void nuevoRegistro(){
        Intent intent = new Intent(this, NuevaNotaActivity.class);
        startActivity(intent);
    }
}