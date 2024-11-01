package com.phunlh2001.prm392_beverages;

import static com.phunlh2001.prm392_beverages.utils.Constant.KEY_ADDRESS;
import static com.phunlh2001.prm392_beverages.utils.Constant.PREF_ADDRESS;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.phunlh2001.prm392_beverages.adapters.SavedPlaceAdapter;
import com.phunlh2001.prm392_beverages.helper.ISavedPlace;
import com.phunlh2001.prm392_beverages.viewmodel.AddressViewModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AddressSelectorActivity extends AppCompatActivity {

    private List<AddressViewModel> addressList;
    private RecyclerView rcvUserAddress;
    private SavedPlaceAdapter addressAdapter;

    private Gson gson;
    private SharedPreferences pref;

    private void initialize() {
        addressList = new ArrayList<>();
        rcvUserAddress = findViewById(R.id.rcvAddress);
        pref = getSharedPreferences(PREF_ADDRESS, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_selector);
        initialize();

        addressAdapter = new SavedPlaceAdapter(new ISavedPlace() {
            @Override
            public void onClickEditAddress(AddressViewModel address) {
                handleClickEdit(address);
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClickDeleteAddress(AddressViewModel address) {
                handleClickDelete(address);
            }

            @Override
            public void onClickItemAddress(AddressViewModel address) {
                handleClickItem(address);
            }
        });

        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        rcvUserAddress.setLayoutManager(linearLayout);
        rcvUserAddress.setAdapter(addressAdapter);

        LoadData();
    }

    private void LoadData() {
        addressList = getList();
        addressAdapter.setData(addressList);
    }

    public void addNewAddress(View view) {
        Intent intent = new Intent(AddressSelectorActivity.this, NewAddressActivity.class);
        startActivity(intent);
    }

    public void useCurrentAddress(View view) {
        Intent intent = new Intent(AddressSelectorActivity.this, DeliveryMenuActivity.class);
        startActivity(intent);
    }

    private void handleClickEdit(AddressViewModel address) {
        Intent intent = new Intent(AddressSelectorActivity.this, NewAddressActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("address", address);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void handleClickDelete(AddressViewModel address) {
        new AlertDialog.Builder(this)
                .setTitle("Confirmation")
                .setMessage("Do you want to remove this address?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    // delete here
                    addressList = getList();
                    addressList.removeIf(obj -> obj.getName().equals(address.getName()));
                    setList(addressList);
                    Toast.makeText(this, "Delete address successfully", Toast.LENGTH_SHORT).show();
                    LoadData();
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void handleClickItem(AddressViewModel address) {
        Intent intent = new Intent(AddressSelectorActivity.this, DeliveryMenuActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("addressItem", address);
        intent.putExtras(bundle);
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


}