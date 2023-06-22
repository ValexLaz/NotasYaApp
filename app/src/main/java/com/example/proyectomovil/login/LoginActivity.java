package com.example.proyectomovil.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectomovil.examenes.AgregarExamenActivity;
import com.example.proyectomovil.materias.MainActivity;
import com.example.proyectomovil.R;
import com.example.proyectomovil.db.DbHelper;
import com.example.proyectomovil.menú.MenuPrincipal;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button entrar;
    DbHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username1);
        password = findViewById(R.id.password1);
        entrar = findViewById(R.id.entrar1);
        DB = new DbHelper(this);

       entrar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                 String user = username.getText().toString();
                 String pass = password.getText().toString();
                 if(user.equals("")||pass.equals(""))
                     Toast.makeText(LoginActivity.this, "Llene los campos", Toast.LENGTH_SHORT).show();
                 else {
                     Boolean checkuserpass = DB.checkpassword(user,pass);
                     if (checkuserpass == true){
                         Toast.makeText(LoginActivity.this, "Login exitoso", Toast.LENGTH_SHORT).show();
                         Intent intent = new Intent(LoginActivity.this, MenuPrincipal.class);
                         startActivity(intent);
                     }else{
                         Toast.makeText(LoginActivity.this, "Credenciales Inválidas", Toast.LENGTH_SHORT).show();
                     }
                 }
           }
       });
    }
}