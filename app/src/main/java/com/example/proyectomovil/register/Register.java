package com.example.proyectomovil.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectomovil.R;
import com.example.proyectomovil.db.DbHelper;
import com.example.proyectomovil.login.LoginActivity;

public class Register extends AppCompatActivity {
    EditText username, password, repassword;
    Button entrar, registrar;
    DbHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        entrar = findViewById(R.id.entrar);
        registrar = findViewById(R.id.registrar);
        repassword = findViewById(R.id.repassword);
        DB = new DbHelper(this);



        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(Register.this, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser == false){
                            Boolean insert = DB.insertData(user,pass);
                            if (insert == true){
                                Toast.makeText(Register.this, "Usuario Registrado", Toast.LENGTH_SHORT).show();

                            }else{
                                Toast.makeText(Register.this, "Error de Registro", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(Register.this, "Usuario ya registrado", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Register.this, "Password not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}