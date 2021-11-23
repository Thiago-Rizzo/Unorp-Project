package com.example.unorpproject;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.unorpproject.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AreaLogged extends AppCompatActivity {

    Spinner spinner;

    String[] list_itens = {"Selecione:", "Lâmpada 9 W", "Chuveiro 4800 W",
            "Microondas 1000 W", "Notebook 65 W"};

    ImageButton bt_addItem;
    ImageButton bt_removeItem;

    TextView chompWatts, chompGasto;
    TextView lampWatts, lampGasto;
    TextView chuveWatts, chuveGasto;
    TextView ventiWatts, ventiGasto;
    TextView grillWatts, grillGasto;
    TextView gastototal;

    EditText chompHrs, chompQtd;
    EditText lampHrs, lampQtd;
    EditText chuveHrs, chuveQtd;
    EditText ventiHrs, ventiQtd;
    EditText grillHrs, grillQtd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_logged);
        Toolbar toolbar = findViewById(R.id.customtoolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        spinner = findViewById(R.id.spinner);
        bt_addItem = findViewById(R.id.bt_addItem);
        bt_removeItem = findViewById(R.id.bt_removeItem);

        chompWatts = findViewById(R.id.chompWatts);
        chompGasto = findViewById(R.id.chompGasto);
        lampWatts = findViewById(R.id.lampWatts);
        lampGasto = findViewById(R.id.lampGasto);
        chuveWatts = findViewById(R.id.chuveWatts);
        chuveGasto = findViewById(R.id.chuveGasto);
        ventiWatts = findViewById(R.id.ventiWatts);
        ventiGasto = findViewById(R.id.ventiGasto);
        grillWatts = findViewById(R.id.grillWatts);
        grillGasto = findViewById(R.id.grillGasto);

        chompHrs = findViewById(R.id.chompHrs);
        chompQtd = findViewById(R.id.chompQtd);
        lampHrs = findViewById(R.id.lampHrs);
        lampQtd = findViewById(R.id.lampQtd);
        chuveHrs = findViewById(R.id.chuveHrs);
        chuveQtd = findViewById(R.id.chuveQtd);
        ventiHrs = findViewById(R.id.ventiHrs);
        ventiQtd = findViewById(R.id.ventiQtd);
        grillHrs = findViewById(R.id.grillHrs);
        grillQtd = findViewById(R.id.grillQtd);

        gastototal = findViewById(R.id.gastototal);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner, list_itens);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown);

        spinner.getBackground().setColorFilter(Color.parseColor("#4f4f4f"),
                PorterDuff.Mode.SRC_ATOP);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);

                // TESTE DE SELEÇÃO DE ITENS NO SPINNER
                if (position == 1) {
                    geratoast("Lâmpada 9 W selecionada");
                } else if (position == 2) {
                    geratoast("Chuveiro 4800 W selecionado");
                } else if (position == 3) {
                    geratoast("Microondas 1k W selecionado");
                } else if (position == 4) {
                    geratoast("Notebook 65 W selecionado");
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        bt_addItem.setOnClickListener(view -> startActivity(new Intent(AreaLogged.this,
                AreaAddItem.class)));

        List<Item> itens = new ArrayList<>();
        Item chomp = new Item(
                chompWatts.getText().toString(),
                chompHrs.getText().toString(),
                chompQtd.getText().toString()
        );
        itens.add(chomp);

        Item lamp = new Item(
                lampWatts.getText().toString(),
                lampHrs.getText().toString(),
                lampQtd.getText().toString()
        );
        itens.add(lamp);

        Item chuve = new Item(
                chuveWatts.getText().toString(),
                chuveHrs.getText().toString(),
                chuveQtd.getText().toString()
        );
        itens.add(chuve);

        Item venti = new Item(
                ventiWatts.getText().toString(),
                ventiHrs.getText().toString(),
                ventiQtd.getText().toString()
        );
        itens.add(venti);

        Item grill = new Item(
                grillWatts.getText().toString(),
                grillHrs.getText().toString(),
                grillQtd.getText().toString()
        );

        double total = itens.stream().mapToDouble(Item::getGasto).sum();

        for(Item i : itens){
            //desenha a linha em tela
        }

        chompGasto.setText(Double.toString(chomp.getGasto()));
//        lampGasto.setText((int) gastoLamp);
//        chuveGasto.setText((int) gastoChuve);
//        ventiGasto.setText((int) gastoVenti);
//        grillGasto.setText((int) gastoGrill);
//
//        gastototal.setText((int) totalgasto);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu) {
            startActivity(new Intent(AreaLogged.this, AreaUsuario.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
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