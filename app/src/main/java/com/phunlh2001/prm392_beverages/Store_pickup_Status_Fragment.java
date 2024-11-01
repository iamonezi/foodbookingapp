package com.phunlh2001.prm392_beverages;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.phunlh2001.prm392_beverages.data.AppDatabase;
import com.phunlh2001.prm392_beverages.data.entities.Order;
import com.phunlh2001.prm392_beverages.data.entities.User;
import com.phunlh2001.prm392_beverages.data.entities.enums.OrderStatus;
import com.phunlh2001.prm392_beverages.viewmodel.OrderInfo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class Store_pickup_Status_Fragment extends Fragment {
    private static final int MY_REQUEST_CODE = 10;
    private Order order;
    private List<OrderInfo> mListproduct;
    public Store_pickup_Status_Fragment(Order order) {
        this.order = order;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Change layout to Store pickup

        View view = inflater.inflate(R.layout.fragment_delivery_stauts, container, false);
        TextView status = (TextView) view.findViewById(R.id.status_order);
        TextView location = (TextView) view.findViewById(R.id.location);
        ImageView imgTime = (ImageView) view.findViewById(R.id.locateToTime);
        TextView totalPrice = (TextView) view.findViewById(R.id.totalPrice);
        TextView order_date = (TextView) view.findViewById(R.id.order_date);
        TextView productName = (TextView) view.findViewById(R.id.products_name);


        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        //Get address
        User user = AppDatabase.getInstance(getActivity()).userDao().getById(order.getUser_id());
        //Get product
        mListproduct = AppDatabase.getInstance(getActivity()).orderDetailDao().getProductNameByOrderId(order.getId());
        //Received
        if(order.getStatus().equals(OrderStatus.ORDER_RECEIVED)){
            status.setText("Order received");
            status.setTextColor(Color.parseColor("#FC820A"));
            status.setBackgroundResource(R.drawable.custom_text_status_preparing);

        }
        //Ready to pickup
        if(order.getStatus().equals(OrderStatus.READY_FOR_PICKUP)){
            status.setText("Ready for pickup");
            status.setTextColor(Color.parseColor("#1A94FF"));
            status.setBackgroundResource(R.drawable.custom_text_status_delivering);

        }
        String proName = "";
        for(OrderInfo s: mListproduct){
            proName += s.getTitle() + "(x" + s.getQuantity() + ")" + " ";
        }
        totalPrice.setText(String.valueOf(order.getTotal_price()) + " đ");
        order_date.setText(String.valueOf(formatter.format(order.getCreateAt())));
        location.setText(String.valueOf(formatter.format(order.getCreateAt())));
        productName.setText(String.valueOf(proName));
        Date dateTime = order.getCreateAt();

        //Change Icon
        imgTime.setImageResource(R.drawable.img_clock);

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