package com.example.proyectomovil.menú;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.proyectomovil.R;
import com.example.proyectomovil.examenes.AgregarExamenActivity;
import com.example.proyectomovil.login.LoginActivity;
import com.example.proyectomovil.materias.MainActivity;
import com.example.proyectomovil.notas.MainActivity2;
import com.example.proyectomovil.notas.NuevaNotaActivity;
import com.example.proyectomovil.notas.VerNotasActivity;
import com.example.proyectomovil.notas.VisualizarMateriaActivity;


public class MenuPrincipal extends AppCompatActivity {
    ImageView imageView4, imageView5, imageView6, imageView7;
    Button button2, button4, button5, button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        button2 = findViewById(R.id.button2);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);



        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipal.this, AgregarExamenActivity.class);
                startActivity(intent);            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipal.this, MainActivity.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipal.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipal.this, VisualizarMateriaActivity.class);
                startActivity(intent);
            }
        });
    }


}