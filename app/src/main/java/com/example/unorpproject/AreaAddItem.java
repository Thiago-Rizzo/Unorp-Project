package com.example.unorpproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.unorpproject.model.Device;
import com.example.unorpproject.model.Home;
import com.example.unorpproject.model.Item;
import com.example.unorpproject.model.Result;
import com.example.unorpproject.model.User;
import com.example.unorpproject.retrofit.BaseAsyncTask;
import com.example.unorpproject.retrofit.RequestRetrofit;
import com.example.unorpproject.retrofit.SignUpService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class AreaAddItem extends AppCompatActivity {

    Button bt_cancelar;
    Button bt_salvar;

    TextView titulo;
    TextView nomeItem;
    EditText potenciaItem;
    EditText tempousoItem;
    EditText quantidadeItem;
    TextView dica;

    public static boolean remover;
    public static Item item;
    public static User user = LoginForm.user;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        Toolbar toolbar = findViewById(R.id.add_item_toolbar);
        ImageButton btn = toolbar.findViewById(R.id.back_arrow);
        btn.setOnClickListener(view -> {
            startActivity(new Intent(AreaAddItem.this, AreaLogged.class));
            finish();
        });

        bt_cancelar = findViewById(R.id.bt_cancelar);
        bt_salvar = findViewById(R.id.bt_salvar);

        titulo = findViewById(R.id.titulo);

        nomeItem = findViewById(R.id.nomedoItem);
        potenciaItem = findViewById(R.id.potencia);
        tempousoItem = findViewById(R.id.horas);
        quantidadeItem = findViewById(R.id.quantidade);
        dica = findViewById(R.id.dica1);

        if (remover) {
            bt_cancelar.setText("Remover");
            titulo.setText("Editar Item");

            nomeItem.setText(item.getNome());
            potenciaItem.setText(Long.toString(item.getWatts()));
            tempousoItem.setText(Long.toString(item.getHrs()));
            quantidadeItem.setText(Long.toString(item.getQtd()));
            find();
        }

        nomeItem.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                find();
            }
        });

        bt_cancelar.setOnClickListener(view -> {

            delete();
            startActivity(new Intent(AreaAddItem.this, AreaLogged.class));
            finish();
        });

        bt_salvar.setOnClickListener(view -> {
            if (remover) {
                delete();
            }
            insert();
            startActivity(new Intent(AreaAddItem.this, AreaLogged.class));
            finish();
        });

    }

    private void find() {
        SignUpService service = new RequestRetrofit().getSignUpService();
        Call<Result<Device>> call = service.findDevice(nomeItem.getText().toString());
        new BaseAsyncTask<>(() -> {
            try {
                Response<Result<Device>> response = call.execute();
                return response.body();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }, response -> {
            if (response != null) {
                if (response.getResponse() != null) {
                    if (!remover) {
                        potenciaItem.setText(Long.toString(response.getResponse().getPower()));
                        tempousoItem.setText(Long.toString(response.getResponse().getUsageTime()));
                    }
                    dica.setText(response.getResponse().getHint());
                }
            }
        }).execute();
    }

    private void insert() {
        SignUpService service = new RequestRetrofit().getSignUpService();
        Call<Result<Home>> call = service.insert(user.getId(), nomeItem.getText().toString(),
                Long.parseLong(tempousoItem.getText().toString()), Long.parseLong(potenciaItem.getText().toString()),
                Long.parseLong(quantidadeItem.getText().toString()));
        new BaseAsyncTask<>(() -> {
            try {
                Response<Result<Home>> response = call.execute();
                return response.body();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }, response -> {
        }).execute();
    }

    private void delete() {
        SignUpService service = new RequestRetrofit().getSignUpService();
        Call<Result<Home>> call = service.delete(item.getId());
        new BaseAsyncTask<>(() -> {
            try {
                Response<Result<Home>> response = call.execute();
                return response.body();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }, response -> {
            System.out.println(user);
        }).execute();
    }

}
