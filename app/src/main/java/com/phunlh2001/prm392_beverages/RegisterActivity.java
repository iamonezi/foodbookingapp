package com.phunlh2001.prm392_beverages;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.phunlh2001.prm392_beverages.data.AppDatabase;
import com.phunlh2001.prm392_beverages.data.dao.UserDao;
import com.phunlh2001.prm392_beverages.data.entities.User;
import com.phunlh2001.prm392_beverages.data.entities.enums.RoleAccount;
import com.phunlh2001.prm392_beverages.utils.Hash;

import org.jetbrains.annotations.Contract;

import java.util.Date;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtEmail, edtName, edtPhone, edtPassword, edtAddress, edtConfirmPwd;
    private Button btnRegister;
    private UserDao userDao;

    private void initialize() {
        edtEmail = findViewById(R.id.edtEmail);
        edtName = findViewById(R.id.edtFullName);
        edtPhone = findViewById(R.id.edtPhone);
        edtPassword = findViewById(R.id.edtPassword);
        edtAddress = findViewById(R.id.edtAddress);
        edtConfirmPwd = findViewById(R.id.edtConfirmPassword);

        btnRegister = findViewById(R.id.btnRegister);

        userDao = AppDatabase.getInstance(this).userDao();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        initialize();

        // Set click listener for sign up button
        btnRegister.setOnClickListener(view -> registerUser());
    }

    private void registerUser() {
        // Get input values
        String email = edtEmail.getText().toString().trim();
        String fullName = edtName.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();
        String confirmPwd = edtConfirmPwd.getText().toString().trim();

        try {
            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(fullName) || TextUtils.isEmpty(phone) ||
                    TextUtils.isEmpty(password) || TextUtils.isEmpty(address) || TextUtils.isEmpty(confirmPwd)) {
                throw new Exception("Must be fill in all blank");
            } else if (!isValidEmail(email)) {
                throw new Exception("Invalid email address");
            } else if (!isValidName(fullName)) {
                throw new Exception("Invalid name (maximum 20 words)");
            } else if (!isValidPhone(phone)) {
                throw new Exception("Invalid phone number");
            } else if (!password.equals(confirmPwd)) {
                throw new Exception("Confirm password incorrect");
            } else if (userDao.getUserByEmail(email) != null) {
                throw new Exception("Email already exists");
            }else {
                // Create a new User object
                User user = new User(email, Hash.Md5(password), fullName, address, phone);
                // Insert the user into the database using UserDao
                userDao.insert(user);

                // Show a success message
                Toast.makeText(RegisterActivity.this, "Account created successfully", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        } catch (Exception e) {
            Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z]\\w*@[a-zA-Z]\\w*(\\.[a-zA-Z]\\w*)+$");
    }

    private boolean isValidName(String name) {
        return name.split(" ").length <= 20;
    }

    private boolean isValidPhone(String phone) {
        return phone.matches("^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$");
    }
}