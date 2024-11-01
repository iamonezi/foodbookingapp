package com.phunlh2001.prm392_beverages;

import static com.phunlh2001.prm392_beverages.utils.Constant.KEY_LOGIN;
import static com.phunlh2001.prm392_beverages.utils.Constant.PREF_LOGIN;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.phunlh2001.prm392_beverages.adapters.ViewPagerOrderHistoryAdapter;
import com.phunlh2001.prm392_beverages.data.entities.Order;

import java.util.List;

public class OrderHistory extends AppCompatActivity {
    TabLayout mTabLayout;
    ViewPager2 viewPager2;
    ViewPagerOrderHistoryAdapter viewPagerOrderHistoryAdapter;
    List<Order> mListOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Fragment
        //Test fragment order management
        mTabLayout = findViewById(R.id.tab_layout);
        mTabLayout.setTabIndicatorFullWidth(true);
        mTabLayout.addTab(mTabLayout.newTab().setText("Store pickup"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Delivery"));

        SharedPreferences pref = getSharedPreferences(PREF_LOGIN, Context.MODE_PRIVATE);
        String email = pref.getString(KEY_LOGIN, "");

        viewPager2= findViewById(R.id.view_pager);
        viewPagerOrderHistoryAdapter = new ViewPagerOrderHistoryAdapter(this, email);
        viewPager2.setAdapter(viewPagerOrderHistoryAdapter);

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

        //RCV in fragment
        //replaceFragment(new OrderHistory_Delivery_Fragment());
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.order_history_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
        }
        if (id == R.id.action1) {
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