package com.phunlh2001.prm392_beverages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.phunlh2001.prm392_beverages.R;

public class Store_pickup_No_Order_Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_delivery_no_order, container, false);
        Button btn = (Button) rootView.findViewById(R.id.btn_order_now);
        btn.setOnClickListener(v -> {
            Toast.makeText(rootView.getContext(), "Order store pickup (coming soon)", Toast.LENGTH_SHORT).show();
        });
        return rootView;
    }
}