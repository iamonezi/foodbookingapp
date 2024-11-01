package com.phunlh2001.prm392_beverages;

import static com.phunlh2001.prm392_beverages.utils.Constant.KEY_ADDRESS;
import static com.phunlh2001.prm392_beverages.utils.Constant.PREF_ADDRESS;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.phunlh2001.prm392_beverages.viewmodel.AddressViewModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NewAddressActivity extends AppCompatActivity {

    private EditText edtAddress, edtName, edtPhone;
    private Button btnSave;

    private List<AddressViewModel> addressList;
    private Gson gson;
    private SharedPreferences pref;

    String strName, strPhone, strAddress;

    private void init() {
        edtAddress = findViewById(R.id.edt_address);
        edtName = findViewById(R.id.edt_recipient_name);
        edtPhone = findViewById(R.id.edt_recipient_phone);
        btnSave = findViewById(R.id.btnSaveAddress);
        addressList = new ArrayList<>();
        pref = getSharedPreferences(PREF_ADDRESS, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_address);
        init();

        try {
            AddressViewModel address = (AddressViewModel) getIntent().getExtras().get("address");
            if (address != null) {
                edtAddress.setText(address.getAddress());
                edtName.setText(address.getName());
                edtPhone.setText(address.getPhone());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        btnSave.setOnClickListener(v -> handleClickSave());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void handleClickSave() {
        strName = edtName.getText().toString().trim();
        strAddress = edtAddress.getText().toString().trim();
        strPhone = edtPhone.getText().toString().trim();
        try {
            if (TextUtils.isEmpty(strAddress) || TextUtils.isEmpty(strName) || TextUtils.isEmpty(strPhone)) {
                throw new Exception("Must be full all blank");
            } else if (!strPhone.matches("^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$")) {
                throw new Exception("Phone number incorrect");
            } else {
                addressList = getList();
                if (containsName(addressList, strName)) {
                    addressList.removeIf(obj -> obj.getName().equals(strName));
                }
                AddressViewModel address = new AddressViewModel(strName, strPhone, strAddress);
                addressList.add(address);
                setList(addressList);
                navigateToHome();
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void navigateToHome() {
        Intent intent = new Intent(NewAddressActivity.this, AddressSelectorActivity.class);
        startActivity(intent);
    }

    private void setList(List<AddressViewModel> list) {
        SharedPreferences.Editor editor = pref.edit();
        String json = gson.toJson(list);

        editor.putString(KEY_ADDRESS, json);
        editor.apply();
    }

    private List<AddressViewModel> getList() {
        List<AddressViewModel> arrItems = new ArrayList<>();
        String json = pref.getString(KEY_ADDRESS, null);
        if (json != null) {
            Type type = new TypeToken<List<AddressViewModel>>(){}.getType();
            arrItems = gson.fromJson(json, type);
        }
        return arrItems;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private boolean containsName(List<AddressViewModel> list, String text) {
        return list.stream().anyMatch(p -> p.getName().equals(text));
    }
}