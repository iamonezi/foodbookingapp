package com.phunlh2001.prm392_beverages;

import static com.phunlh2001.prm392_beverages.utils.Constant.KEY_LOGIN;
import static com.phunlh2001.prm392_beverages.utils.Constant.PREF_LOGIN;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.phunlh2001.prm392_beverages.adapters.ViewPagerOrderManagementAdapter;


public class OrderManagement extends AppCompatActivity {
    TabLayout mTabLayout;
    ViewPager2 viewPager2;
    ViewPagerOrderManagementAdapter viewPagerOrderManagementAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_management);

        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        //Nav
        BottomNavigationView navigationView = findViewById(R.id.bottomNav);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            Intent intent;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        intent = new Intent(OrderManagement.this, HomeActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.order:
                        intent = new Intent(OrderManagement.this, OrderManagement.class);
                        startActivity(intent);
                        break;
                    case R.id.profile:
                        intent = new Intent(OrderManagement.this, ProfileActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
        //Fragment
        //Test fragment order management
        mTabLayout = findViewById(R.id.tab_layout);
        mTabLayout.setTabIndicatorFullWidth(true);
        mTabLayout.addTab(mTabLayout.newTab().setText("Store pickup"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Delivery"));

        //Get email
        SharedPreferences pref = getSharedPreferences(PREF_LOGIN, Context.MODE_PRIVATE);
        String email = pref.getString(KEY_LOGIN, "");
        viewPager2= findViewById(R.id.view_pager);
        viewPagerOrderManagementAdapter = new ViewPagerOrderManagementAdapter(this, email);
        viewPager2.setAdapter(viewPagerOrderManagementAdapter);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mTabLayout.getTabAt(position).select();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.order_management_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.history) {
            Intent intent = new Intent(this, OrderHistory.class);
            startActivity(intent);
        } else if (id == R.id.action1) {
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

