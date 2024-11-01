package com.phunlh2001.prm392_beverages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout navigateStore, navigateDelivery;

    private void initialize() {
        navigateDelivery = findViewById(R.id.btn_delivery);
        navigateStore = findViewById(R.id.btn_store_pickup);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initialize();

        navigateStore.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, StoreMenuActivity.class);
            startActivity(intent);
            finish();
        });

        navigateDelivery.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, DeliveryMenuActivity.class);
            startActivity(intent);
            finish();
        });

        BottomNavigationView navigationView = findViewById(R.id.bottomNav);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            Intent intent;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        intent = new Intent(HomeActivity.this, HomeActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.order:
                        intent = new Intent(HomeActivity.this, OrderManagement.class);
                        startActivity(intent);
                        break;
                    case R.id.profile:
                        intent = new Intent(HomeActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
    }
}