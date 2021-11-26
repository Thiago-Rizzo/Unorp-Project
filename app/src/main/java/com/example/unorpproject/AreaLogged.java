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

import com.example.unorpproject.model.Home;
import com.example.unorpproject.model.Item;
import com.example.unorpproject.model.Result;
import com.example.unorpproject.model.User;
import com.example.unorpproject.retrofit.BaseAsyncTask;
import com.example.unorpproject.retrofit.RequestRetrofit;
import com.example.unorpproject.retrofit.SignUpService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Response;

public class AreaLogged extends AppCompatActivity {

    ImageButton bt_addItem;

    TextView gastototal;

    public static User user = LoginForm.user;

    public static List<Item> itens = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_logged);
        Toolbar toolbar = findViewById(R.id.customtoolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar());

        bt_addItem = findViewById(R.id.bt_addItem);
        gastototal = findViewById(R.id.gastototal);
        listHome();

        bt_addItem.setOnClickListener(view -> {

            AreaAddItem.remover = false;
            AreaAddItem.item = new Item();

            startActivity(new Intent(AreaLogged.this, AreaAddItem.class));
            finish();

        });
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
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void listHome() {
        SignUpService service = new RequestRetrofit().getSignUpService();
        Call<Result<List<Home>>> call = service.findHome(user.getId());
        new BaseAsyncTask<>(() -> {
            try {
                Response<Result<List<Home>>> response = call.execute();
                return response.body();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }, response -> {
            if (response != null) {
                List<Home> homes = response.getResponse();
                List<Item> itens2 = new ArrayList<>();
                homes.forEach(h -> itens2.add(new Item(h.getId(), h.getDeviceName(), h.getPower(), h.getUsageTime(), h.getQuantity())));
                AreaLogged.itens = itens2;
                TableLayout table = AreaLogged.this.findViewById(R.id.table);
                for (Item i : itens2) {
                    @SuppressLint("InflateParams") TableRow tablerow = (TableRow) LayoutInflater.
                            from(AreaLogged.this).inflate(R.layout.atribuindo_linhas_tabela, null);

                    ((TextView) tablerow.findViewById(R.id.name)).setText(i.getNome());
                    ((TextView) tablerow.findViewById(R.id.wattsItem)).setText(Long.toString(i.getWatts()));
                    ((TextView) tablerow.findViewById(R.id.hrsItem)).setText(Long.toString(i.getHrs()));
                    ((TextView) tablerow.findViewById(R.id.qtdItem)).setText(Long.toString(i.getQtd()));
                    ((TextView) tablerow.findViewById(R.id.gastoItem)).setText(Long.toString(i.getGasto()));


                    Button remove = tablerow.findViewById(R.id.remove);
                    remove.setOnClickListener(view -> {
                        AreaAddItem.remover = true;
                        AreaAddItem.item = i;

                        startActivity(new Intent(AreaLogged.this, AreaAddItem.class));
                        finish();

                    });
                    table.addView(tablerow);
                }


                long total = itens.stream().mapToLong(Item::getGasto).sum();
                gastototal.setText(Long.toString(total));
            }
        }).execute();
    }

}
