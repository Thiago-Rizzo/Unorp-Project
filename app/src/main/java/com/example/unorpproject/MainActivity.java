package com.example.unorpproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.DecimalFormat;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    EditText et_watts;
    EditText et_tempouso;
    TextView consumouteis;
    TextView consumomes;
    TextView valoruteis;
    TextView valormes;
    Button bt_calcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.customtoolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        et_watts = findViewById(R.id.et_watts);
        et_tempouso = findViewById(R.id.et_tempouso);
        consumouteis = findViewById(R.id.consumouteis);
        consumomes = findViewById(R.id.consumomes);
        valoruteis = findViewById(R.id.custouteis);
        valormes = findViewById(R.id.customes);
        bt_calcular = findViewById(R.id.bt_calcular);

        bt_calcular.setOnClickListener(view -> {

            if (et_watts.getText().toString().isEmpty()) {
                et_watts.setError("Campo Vazio, preencha com apenas números.");
            }
            else if (et_tempouso.getText().toString().isEmpty()) {
                et_tempouso.setError("Campo Vazio, preencha com apenas números.");
            }
            else {
                double valorW = Double.parseDouble(et_watts.getText().toString());
                double valorH = Double.parseDouble(et_tempouso.getText().toString());

                if (valorH > 24) {
                    et_tempouso.setError("Valor máximo 24!");
                }
                else {
                    DecimalFormat arredondar = new DecimalFormat("#.##");

                    double consumodias = valorW * valorH * 0.02;
                    double consumo1mes = valorW * valorH * 0.03;

                    consumouteis.setText(arredondar.format(consumodias));
                    consumomes.setText(arredondar.format(consumo1mes));

                    valoruteis.setText(arredondar.format(consumodias * 0.596));
                    valormes.setText(arredondar.format(consumo1mes * 0.596));
                }
            }
        });
    }
}