package com.phunlh2001.prm392_beverages;

import static com.phunlh2001.prm392_beverages.utils.Constant.KEY_LOGIN;
import static com.phunlh2001.prm392_beverages.utils.Constant.PREF_LOGIN;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.phunlh2001.prm392_beverages.data.AppDatabase;
import com.phunlh2001.prm392_beverages.data.dao.UserDao;
import com.phunlh2001.prm392_beverages.data.entities.User;
import com.phunlh2001.prm392_beverages.utils.Hash;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail, edtPwd;
    private Button btnLogin, btnRegister;
    private UserDao userDao;

    private SharedPreferences pref;

    private void initialize() {
        edtEmail = findViewById(R.id.edt_email);
        edtPwd = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_Login);
        btnRegister = findViewById(R.id.Or_Create);

        userDao = AppDatabase.getInstance(this).userDao();

        pref = getSharedPreferences(PREF_LOGIN, Context.MODE_PRIVATE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initialize();

        // Set click listener for login button
        btnLogin.setOnClickListener(view -> loginUser());

        // Set click listener for create account button
        btnRegister.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void loginUser() {
        // Get input values
        String email = edtEmail.getText().toString().trim();
        String password = edtPwd.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Must fill in all blanks", Toast.LENGTH_SHORT).show();
        } else {
            // Kiểm tra thông tin đăng nhập trong cơ sở dữ liệu
            User user = userDao.getUserByEmail(email);
            if (user == null || !user.getPassword().equals(Hash.Md5(password))) {
                Toast.makeText(this, "Email or password incorrect", Toast.LENGTH_SHORT).show();
            } else {
                // Đăng nhập thành công, chuyển sang ProfileActivity và truyền thông tin người dùng qua Intent
                SharedPreferences.Editor editor = pref.edit();
                editor.putString(KEY_LOGIN, email);
                boolean isLogin = editor.commit();

                if (isLogin) {
                    Toast.makeText(this, "Login successfully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
