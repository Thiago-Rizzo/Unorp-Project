package com.example.unorpproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginForm extends AppCompatActivity {

    Button bt_entrar, bt_criarConta, bt_outraTela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);

        bt_entrar = findViewById(R.id.bt_entrar);
        bt_criarConta = findViewById(R.id.bt_criarConta);
        bt_outraTela = findViewById(R.id.bt_outraTela);


        bt_entrar.setOnClickListener(view -> startActivity(new Intent(LoginForm.this,
                AreaLogged.class)));

        bt_criarConta.setOnClickListener(view -> startActivity(new Intent(LoginForm.this,
                CadastroForm.class)));

        bt_outraTela.setOnClickListener(view -> startActivity(new  Intent(LoginForm.this,
                MainActivity.class)));
    }
}