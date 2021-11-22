package com.example.unorpproject;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AreaLogged extends AppCompatActivity {

    Button buto;

    Spinner spinner;

    String[] list_itens = {"Lâmpada 9 W", "Chuveiro 4800 W", "Microondas 1000 W", "Notebook 65 W"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_logged);

        buto = findViewById(R.id.buto);
        spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list_itens);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.getBackground().setColorFilter(Color.parseColor("#4f4f4f"), PorterDuff.Mode.SRC_ATOP);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        buto.setOnClickListener(view -> startActivity(new Intent(AreaLogged.this,
                AreaUsuario.class)));
    }
}