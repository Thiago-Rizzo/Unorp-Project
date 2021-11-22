package com.example.unorpproject;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AreaAddItem extends AppCompatActivity {

    Button bt_cancelar;
    Button bt_salvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        bt_cancelar = findViewById(R.id.bt_cancelar);
        bt_salvar = findViewById(R.id.bt_salvar);

        bt_cancelar.setOnClickListener(view -> finish());

        bt_salvar.setOnClickListener(view -> finish());

    }

}
