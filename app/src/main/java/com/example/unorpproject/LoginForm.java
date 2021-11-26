package com.example.unorpproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.unorpproject.model.Result;
import com.example.unorpproject.model.User;
import com.example.unorpproject.retrofit.BaseAsyncTask;
import com.example.unorpproject.retrofit.RequestRetrofit;
import com.example.unorpproject.retrofit.SignUpService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginForm extends AppCompatActivity {

    Button bt_entrar, bt_criarConta, bt_outraTela;
    EditText et_email, et_senha;
    String[] mensagens = {"E-mail ou senha incorretos!", "Usuário não encontrado."};

    public static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);

        bt_entrar = findViewById(R.id.bt_entrar);
        bt_criarConta = findViewById(R.id.bt_criarConta);
        bt_outraTela = findViewById(R.id.bt_outraTela);
        et_email = findViewById(R.id.et_emailLogin);
        et_senha = findViewById(R.id.et_senhaLogin);

        bt_entrar.setOnClickListener(view -> {
            String email = et_email.getText().toString();
            String password = et_senha.getText().toString();
            login(email, password);
        });

        bt_criarConta.setOnClickListener(view -> startActivity(new Intent(LoginForm.this,
                CadastroForm.class)));

        bt_outraTela.setOnClickListener(view -> startActivity(new Intent(LoginForm.this,
                MainActivity.class)));
    }

    private void login(String email, String password) {

        SignUpService service = new RequestRetrofit().getSignUpService();
        Call<Result<User>> call = service.login(email, password);
        new BaseAsyncTask<>(() -> {
            try {
                Response<Result<User>> response = call.execute();
                return response.body();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }, response -> {
            if (response != null) {
                user = response.getResponse();
                if (user != null) {
                    startActivity(new Intent(LoginForm.this,
                            AreaLogged.class));
                } else {
                    geratoast(response.getMessage());
                }
            } else {
                geratoast(mensagens[0]);
            }
        }).execute();
    }

    private void geratoast(String mensagens) {

        LayoutInflater inflater = getLayoutInflater();

        View layout = inflater.inflate(R.layout.toastf, findViewById(R.id.custom_toast_layout_id));

        TextView toastMessage = layout.findViewById(R.id.texto);
        toastMessage.setText(mensagens);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}