package com.phunlh2001.prm392_beverages.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.phunlh2001.prm392_beverages.OrderHistory_Delivery_Fragment;
import com.phunlh2001.prm392_beverages.OrderHistory_StorePickup_Fragment;
import com.phunlh2001.prm392_beverages.Store_pickup_No_Order_Fragment;
import com.phunlh2001.prm392_beverages.data.AppDatabase;
import com.phunlh2001.prm392_beverages.data.entities.Order;
import com.phunlh2001.prm392_beverages.data.entities.User;
import com.phunlh2001.prm392_beverages.data.entities.enums.OrderStatus;
import com.phunlh2001.prm392_beverages.data.entities.enums.OrderType;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerOrderHistoryAdapter extends FragmentStateAdapter {
    Context context;
    List<Order> mListOrder = new ArrayList<>();
    List<Order> mListStorePickup = new ArrayList<>();
    List<Order> mListPreparing = new ArrayList<>();
    String email;
    int userId = 1;
    public ViewPagerOrderHistoryAdapter(@NonNull FragmentActivity fragmentActivity, String email) {
        super(fragmentActivity);
        context = fragmentActivity;
        this.email = email;
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        getDataOrder();
        switch (position){
            case 0:
                for(Order o: mListOrder){
                    if(o.getType().equals(OrderType.STORE_PICKUP) && o.getStatus().equals(OrderStatus.ORDER_COMPLETED)){
                        mListStorePickup.add(o);                    }
                }
                return new OrderHistory_StorePickup_Fragment(mListStorePickup);
            case 1:
                for(Order o: mListOrder){
                    if(o.getType().equals(OrderType.DELIVERY) && (o.getStatus().equals(OrderStatus.DELIVERED) ||
                            o.getStatus().equals(OrderStatus.DELIVERY_FAILED))){
                        mListPreparing.add(o);                    }
                }
                return new OrderHistory_Delivery_Fragment(mListPreparing);

            default:
                return new Store_pickup_No_Order_Fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public void getDataOrder(){
        mListOrder.clear();
        User user = AppDatabase.getInstance(context).userDao().getUserByEmail(email);
        mListOrder = AppDatabase.getInstance(context).orderDao().getOrderByUserId(user.getId());
        /*mListOrder.add(new Order(100, OrderType.DELIVERY, OrderStatus.DELIVERED, userId));
        mListOrder.add(new Order(130, OrderType.STORE_PICKUP,OrderStatus.ORDER_COMPLETED, userId));
        mListOrder.add(new Order(1302, OrderType.DELIVERY,OrderStatus.DELIVERED, userId));
        mListOrder.add(new Order(1303, OrderType.DELIVERY,OrderStatus.DELIVERY_FAILED, userId));
        mListOrder.add(new Order(13031, OrderType.STORE_PICKUP,OrderStatus.ORDER_COMPLETED, userId));*/
    }
    

}
