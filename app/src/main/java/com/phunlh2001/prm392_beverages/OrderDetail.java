package com.phunlh2001.prm392_beverages;

import static com.phunlh2001.prm392_beverages.utils.Constant.KEY_LOGIN;
import static com.phunlh2001.prm392_beverages.utils.Constant.PREF_LOGIN;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.phunlh2001.prm392_beverages.adapters.OrderDetailAdapter;
import com.phunlh2001.prm392_beverages.data.AppDatabase;
import com.phunlh2001.prm392_beverages.data.entities.Order;
import com.phunlh2001.prm392_beverages.data.entities.User;
import com.phunlh2001.prm392_beverages.data.entities.enums.OrderType;
import com.phunlh2001.prm392_beverages.viewmodel.OrderInfo;

import java.text.SimpleDateFormat;
import java.util.List;

@SuppressWarnings("unchecked")
public class OrderDetail extends AppCompatActivity {
    TextView totalPrice, address, orderId, orderDate, toOrpickup;
    ImageView img_location;
    List<OrderInfo> mListOrderInfo;
    RecyclerView rcv;
    OrderDetailAdapter orderDetailAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_order_detail);
        totalPrice = findViewById(R.id.totalPrice);
        orderId = findViewById(R.id.order_id);
        address = findViewById(R.id.address);
        orderDate = findViewById(R.id.order_date);
        toOrpickup = findViewById(R.id.toOrpickup);
        img_location = findViewById(R.id.img_location);
        //Get data
        mListOrderInfo = (List<OrderInfo>) getIntent().getExtras().get("listProduct");
        Order order = (Order) getIntent().getExtras().get("order");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        //Set text
        totalPrice.setText(String.valueOf(order.getTotal_price()));
        orderId.setText(String.valueOf(order.getId()));
        orderDate.setText(String.valueOf(formatter.format(order.getCreateAt())));
        User user = AppDatabase.getInstance(this).userDao().getById(order.getUser_id());
        address.setText(user.getAddress());

        if(order.getType().equals(OrderType.STORE_PICKUP)){
            address.setText(String.valueOf(formatter.format(order.getCreateAt())));
            img_location.setImageResource(R.drawable.img_clock);
            toOrpickup.setText("Pickup date");
        }
        //Set rcv
        orderDetailAdapter = new OrderDetailAdapter(mListOrderInfo, this);
        rcv = findViewById(R.id.rcv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcv.setLayoutManager(linearLayoutManager);
        rcv.setAdapter(orderDetailAdapter);
        orderDetailAdapter.notifyDataSetChanged();
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