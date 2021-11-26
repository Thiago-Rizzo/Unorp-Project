package com.example.unorpproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.unorpproject.model.User;

import java.util.Objects;

public class AreaUsuario extends AppCompatActivity {

    Button bt_voltar;
    TextView nome;
    TextView cpf;
    TextView email;
    public static User user = LoginForm.user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_usuario);
        Toolbar toolbar = findViewById(R.id.add_item_toolbar);
        ImageButton btn = toolbar.findViewById(R.id.back_arrow);
        btn.setOnClickListener(view -> {
            startActivity(new Intent(AreaUsuario.this, AreaLogged.class));
            finish();
        });

        bt_voltar = findViewById(R.id.bt_voltar);
        nome = findViewById(R.id.nome);
        cpf = findViewById(R.id.nomeUser);
        email = findViewById(R.id.emailUser);

        nome.setText(user.getName());
        cpf.setText(user.getCpf());
        email.setText(user.getEmail());

        bt_voltar.setOnClickListener(view -> {
            startActivity(new Intent(AreaUsuario.this, LoginForm.class));
            finish();
        });

    }
}