package com.example.proyectomovil.materias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.proyectomovil.R;
import com.example.proyectomovil.adaptadores.ListaMateriasAdapter;
import com.example.proyectomovil.db.DbMaterias;
import com.example.proyectomovil.entidades.Materias;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView listaMaterias;
    ArrayList<Materias> listaArrayMaterias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaMaterias = findViewById(R.id.listaMaterias);
        listaMaterias.setLayoutManager(new LinearLayoutManager(this));

        DbMaterias dbMaterias= new DbMaterias(MainActivity.this);

        listaArrayMaterias = new ArrayList<>();

        ListaMateriasAdapter adapter = new ListaMateriasAdapter(dbMaterias.mostrarMaterias());
        listaMaterias.setAdapter(adapter);

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
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }



}