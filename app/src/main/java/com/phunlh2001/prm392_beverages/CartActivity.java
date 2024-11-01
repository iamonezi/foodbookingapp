package com.phunlh2001.prm392_beverages;

import static com.phunlh2001.prm392_beverages.utils.Constant.KEY_CART;
import static com.phunlh2001.prm392_beverages.utils.Constant.PREF_CART;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.phunlh2001.prm392_beverages.adapters.CartAdapter;
import com.phunlh2001.prm392_beverages.data.entities.Product;
import com.phunlh2001.prm392_beverages.viewmodel.CartViewModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private RecyclerView rcvCart;
    private Button btnCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_cart);
        initializeVariables();

        CartAdapter adapter = new CartAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvCart.setLayoutManager(linearLayoutManager);
        rcvCart.setAdapter(adapter);

        btnCheckout.setOnClickListener(view -> {
            SharedPreferences pref = getSharedPreferences(PREF_CART, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.remove(KEY_CART);
            editor.apply();
            Toast.makeText(this, "Checkout successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(CartActivity.this, HomeActivity.class);
            startActivity(intent);
        });

        // load data
        List<Product> _prods = getList();
        adapter.setDataProducts(_prods);
    }

    private void initializeVariables() {
        rcvCart = findViewById(R.id.rcvCart);
        btnCheckout = findViewById(R.id.btnCheckout);
    }

    private List<Product> getList() {
        SharedPreferences pref = getSharedPreferences(PREF_CART, Context.MODE_PRIVATE);
        List<Product> arrItems = new ArrayList<>();
        String json = pref.getString(KEY_CART, null);
        if (json != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Product>>(){}.getType();
            arrItems = gson.fromJson(json, type);
        }
        return arrItems;
    }
}