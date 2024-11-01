package com.phunlh2001.prm392_beverages;

import static com.phunlh2001.prm392_beverages.utils.Constant.KEY_LOGIN;
import static com.phunlh2001.prm392_beverages.utils.Constant.PREF_LOGIN;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.phunlh2001.prm392_beverages.data.AppDatabase;
import com.phunlh2001.prm392_beverages.data.entities.User;

import java.text.SimpleDateFormat;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvFullName, tvJoindate, tvUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        SharedPreferences pref = getSharedPreferences(PREF_LOGIN, Context.MODE_PRIVATE);
        String email = pref.getString(KEY_LOGIN, "");

        User user = AppDatabase.getInstance(this).userDao().getUserByEmail(email);
        tvFullName = findViewById(R.id.tvFullName);
        tvJoindate = findViewById(R.id.tvJoindate);
        tvUser = findViewById(R.id.tvUser);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        // Hiển thị thông tin người dùng lên TextViews
        tvFullName.setText(user.getFull_name());
        tvUser.setText(user.getFull_name());
        tvJoindate.setText("Join date: " + String.valueOf(formatter.format(user.getCreateAt())));

        BottomNavigationView navigationView = findViewById(R.id.bottomNav);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            Intent intent;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        intent = new Intent(ProfileActivity.this, HomeActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.order:
                        intent = new Intent(ProfileActivity.this, OrderManagement.class);
                        startActivity(intent);
                        break;
                    case R.id.profile:
                        intent = new Intent(ProfileActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
    }
}
