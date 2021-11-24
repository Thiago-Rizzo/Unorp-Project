package com.example.unorpproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.unorpproject.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AreaLogged extends AppCompatActivity {

    ImageButton bt_addItem;

    TextView gastototal;

    public static List<Item> itens = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_logged);
        Toolbar toolbar = findViewById(R.id.customtoolbar);
        setSupportActionBar(toolbar);
        //Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        bt_addItem = findViewById(R.id.bt_addItem);
        gastototal = findViewById(R.id.gastototal);

        gastototal.setText("0.0");

        bt_addItem.setOnClickListener(view -> {

            AreaAddItem.remover = false;
            AreaAddItem.item = new Item();

            startActivity(new Intent(AreaLogged.this, AreaAddItem.class));
            finish();

        });

        double total = itens.stream().mapToDouble(Item::getGasto).sum();

        TableLayout table = AreaLogged.this.findViewById(R.id.table);

        List<Item> duplicata = new ArrayList<>(itens);

        for (Item i : duplicata) {
            //desenha a linha em tela

            @SuppressLint("InflateParams") TableRow tablerow = (TableRow) LayoutInflater.
                    from(AreaLogged.this).inflate(R.layout.atribuindo_linhas_tabela, null);

            ((TextView) tablerow.findViewById(R.id.name)).setText(i.getNome());
            ((TextView) tablerow.findViewById(R.id.wattsItem)).setText(Double.toString(i.getWatts()));
            ((TextView) tablerow.findViewById(R.id.hrsItem)).setText(Double.toString(i.getHrs()));
            ((TextView) tablerow.findViewById(R.id.qtdItem)).setText(Double.toString(i.getQtd()));


            Button remove = tablerow.findViewById(R.id.remove);

            remove.setOnClickListener(view -> {
                AreaAddItem.remover = true;
                AreaAddItem.item = i;

                startActivity(new Intent(AreaLogged.this, AreaAddItem.class));
                finish();

            });

            table.addView(tablerow);

        }
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

}
