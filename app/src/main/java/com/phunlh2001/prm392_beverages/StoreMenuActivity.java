package com.phunlh2001.prm392_beverages;

import static com.phunlh2001.prm392_beverages.utils.Constant.KEY_LOGIN;
import static com.phunlh2001.prm392_beverages.utils.Constant.PREF_LOGIN;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.phunlh2001.prm392_beverages.adapters.CategoryAdapter;
import com.phunlh2001.prm392_beverages.adapters.ProductMenuAdapter;
import com.phunlh2001.prm392_beverages.data.AppDatabase;
import com.phunlh2001.prm392_beverages.data.dao.ProductDao;
import com.phunlh2001.prm392_beverages.data.entities.Product;
import com.phunlh2001.prm392_beverages.viewmodel.CategoryViewModel;

import java.util.ArrayList;
import java.util.List;

public class StoreMenuActivity extends AppCompatActivity {

    private RecyclerView rcvMenu, rcvCate;
    private TextView btnCart;
    private ProductMenuAdapter productMenuAdapter;
    private CategoryAdapter categoryAdapter;
    private ProductDao productDao;

    private void initialize() {
        rcvMenu = findViewById(R.id.rcv_menu);
        rcvCate = findViewById(R.id.rcv_cates);
        btnCart = findViewById(R.id.btn_store_cart);
        productMenuAdapter = new ProductMenuAdapter(this);
        categoryAdapter = new CategoryAdapter(this);
        productDao = AppDatabase.getInstance(this).productDao();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_menu);
        initialize();

        recyclerViewCategory();
        recyclerViewProduct();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnCart.setOnClickListener(v -> {
            Intent intent = new Intent(StoreMenuActivity.this, CartActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rcvCate.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryViewModel> categories = new ArrayList<>();
        categories.add(new CategoryViewModel("Coffee", "cate_coffee"));
        categories.add(new CategoryViewModel("Tea", "cate_tea"));
        categories.add(new CategoryViewModel("Freeze", "cate_freeze"));
        categories.add(new CategoryViewModel("Mojito", "cate_mojito"));
        categories.add(new CategoryViewModel("Smoothie", "cate_smoothie"));

        categoryAdapter.setData(categories);
        rcvCate.setAdapter(categoryAdapter);
    }

    private void recyclerViewProduct() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        rcvMenu.setLayoutManager(gridLayoutManager);

        List<Product> _list = productDao.getAll();
        productMenuAdapter.setData(_list);
        rcvMenu.setAdapter(productMenuAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
        if (id == R.id.search) {
            Intent intent = new Intent(this, SearchProductActivity.class);
            startActivity(intent);
        } if (id == R.id.action1) {
            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
            SharedPreferences pref = getSharedPreferences(PREF_LOGIN, Context.MODE_PRIVATE);
            pref.edit().remove(KEY_LOGIN).commit();
            startActivity(new Intent(this, LoginActivity.class));
        } else if (id == R.id.exit) {
            Toast.makeText(this, "Exit", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}