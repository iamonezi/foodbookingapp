package com.phunlh2001.prm392_beverages;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.phunlh2001.prm392_beverages.adapters.OrderHistoryAdapter;
import com.phunlh2001.prm392_beverages.data.entities.Order;
import com.phunlh2001.prm392_beverages.data.entities.enums.OrderStatus;
import com.phunlh2001.prm392_beverages.data.entities.enums.OrderType;
import com.phunlh2001.prm392_beverages.viewmodel.OrderInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderHistory_StorePickup_Fragment extends Fragment {
    private static final int MY_REQUEST_CODE = 10;
    RecyclerView rcv;
    OrderHistoryAdapter orderHistoryAdapter;
    List<Order> mListOrder;


    public OrderHistory_StorePickup_Fragment(List<Order> mListOrder) {
        this.mListOrder = mListOrder;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_history_store_pickup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        orderHistoryAdapter = new OrderHistoryAdapter(getContext(), mListOrder, "STOREPICKUP", new OrderHistoryAdapter.IClickItem() {
            @Override
            public void onBindItem(List<OrderInfo> orderinfo, Order order) {
                bindItemDetail(orderinfo, order);
            }
        });
        rcv = view.findViewById(R.id.rcv);
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        rcv.setHasFixedSize(true);
        rcv.setAdapter(orderHistoryAdapter);
        orderHistoryAdapter.notifyDataSetChanged();
    }

    public void bindItemDetail(List<OrderInfo> mListproduct, Order order){
        Intent intent = new Intent(getActivity(), OrderDetail.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("order",(Serializable) order);
        bundle.putSerializable("listProduct", (Serializable) mListproduct);
        intent.putExtras(bundle);
        startActivityForResult(intent, MY_REQUEST_CODE);
    }

}