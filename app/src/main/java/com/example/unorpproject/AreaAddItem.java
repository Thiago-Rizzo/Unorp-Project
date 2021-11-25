package com.example.unorpproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.unorpproject.model.Item;

public class AreaAddItem extends AppCompatActivity {

    Button bt_cancelar;
    Button bt_salvar;

    TextView titulo;
    TextView nomeItem;
    EditText potenciaItem;
    EditText tempousoItem;
    EditText quantidadeItem;

    public static boolean remover;
    public static Item item;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        bt_cancelar = findViewById(R.id.bt_cancelar);
        bt_salvar = findViewById(R.id.bt_salvar);

        titulo = findViewById(R.id.titulo);

        nomeItem = findViewById(R.id.nomedoItem);
        potenciaItem = findViewById(R.id.potencia);
        tempousoItem = findViewById(R.id.horas);
        quantidadeItem = findViewById(R.id.quantidade);

        if(remover) {
            bt_cancelar.setText("Remover");
            titulo.setText("Editar Item");

            nomeItem.setText(item.getNome());
            potenciaItem.setText(Double.toString(item.getWatts()));
            tempousoItem.setText(Double.toString(item.getHrs()));
            quantidadeItem.setText(Double.toString(item.getQtd()));
        }

        bt_cancelar.setOnClickListener(view -> {
            AreaLogged.itens.remove(item);

            startActivity(new Intent(AreaAddItem.this, AreaLogged.class));
            finish();
        });

        bt_salvar.setOnClickListener(view -> {
            if(remover) {
                AreaLogged.itens.remove(item);
            }

            AreaLogged.itens.add(new Item(
                    nomeItem.getText().toString(), potenciaItem.getText().toString(),
                    tempousoItem.getText().toString(), quantidadeItem.getText().toString()
            ));
            startActivity(new Intent(AreaAddItem.this, AreaLogged.class));
            finish();
        });

    }

}
