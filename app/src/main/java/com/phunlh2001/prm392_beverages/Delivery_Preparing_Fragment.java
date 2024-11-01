package com.phunlh2001.prm392_beverages;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.phunlh2001.prm392_beverages.R;
import com.phunlh2001.prm392_beverages.data.AppDatabase;
import com.phunlh2001.prm392_beverages.data.entities.Order;
import com.phunlh2001.prm392_beverages.data.entities.Product;
import com.phunlh2001.prm392_beverages.data.entities.User;
import com.phunlh2001.prm392_beverages.data.entities.enums.OrderStatus;
import com.phunlh2001.prm392_beverages.viewmodel.OrderInfo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class Delivery_Preparing_Fragment extends Fragment {
    private static final int MY_REQUEST_CODE = 10;
    private Order order;
    private List<OrderInfo> mListproduct;

    public Delivery_Preparing_Fragment(Order order) {
        this.order = order;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delivery_stauts, container, false);
        TextView status = (TextView) view.findViewById(R.id.status_order);
        TextView order_date = (TextView) view.findViewById(R.id.order_date);
        TextView totalPrice = (TextView) view.findViewById(R.id.totalPrice);
        TextView productName = (TextView) view.findViewById(R.id.products_name);
        TextView location = (TextView) view.findViewById(R.id.location);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        //Get address
        User user = AppDatabase.getInstance(getActivity()).userDao().getById(order.getUser_id());
        //Get product
        mListproduct = AppDatabase.getInstance(getActivity()).orderDetailDao().getProductNameByOrderId(order.getId());
        //System.out.println(formatter.format(date));
        if(order.getStatus().equals(OrderStatus.DELIVERING)){
            status.setText("Delivering");
            status.setTextColor(Color.parseColor("#1A94FF"));
            status.setBackgroundResource(R.drawable.custom_text_status_delivering);

        }
        String proName = "";
        for(OrderInfo s: mListproduct){
            proName += s.getTitle() + "(x" + s.getQuantity() + ")" + " ";
        }
        totalPrice.setText(String.valueOf(order.getTotal_price()) + " đ");
        order_date.setText(String.valueOf(formatter.format(order.getCreateAt())));
        location.setText(user.getAddress());
        productName.setText(String.valueOf(proName));
        //Else layout will display Preparing (Already in design)

        //Onclick LinearLayout to go Order Detail
        LinearLayout orderDetail = (LinearLayout) view.findViewById(R.id.order_detail);
        orderDetail.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), OrderDetail.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("order",(Serializable) order);
            bundle.putSerializable("listProduct", (Serializable) mListproduct);
            intent.putExtras(bundle);
            startActivityForResult(intent, MY_REQUEST_CODE);
        });
        return view;
    }
}