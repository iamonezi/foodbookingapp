package com.phunlh2001.prm392_beverages.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.phunlh2001.prm392_beverages.data.AppDatabase;
import com.phunlh2001.prm392_beverages.data.dao.CartDao;
import com.phunlh2001.prm392_beverages.data.entities.OrderDetail;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CartRepo {
    private CartDao cartDao;
    private LiveData<List<OrderDetail>> allCartItemsLiveData;
    private Executor executor = Executors.newSingleThreadExecutor();

    public LiveData<List<OrderDetail>> getAllCartItemsLiveData() {
        return allCartItemsLiveData;
    }

    public CartRepo(Application application){
        cartDao = AppDatabase.getInstance(application).cartDao();
//        allCartItemsLiveData = cartDao.getAllCartItems();
    }

    public void insertCartItem(OrderDetail orderDetail){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDao.insertCartItem(orderDetail);
            }
        });
    }

    public void deleteCartItem(OrderDetail orderDetail){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDao.deleteCartItem(orderDetail);
            }
        });
    }

    public void updateQuantity(int product_id, int order_id , int quantity) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDao.updateQuantity(product_id, order_id, quantity);
            }
        });
    }

    public void deleteAllCartItems(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDao.deleteAllItems();
            }
        });
    }

    public void getProductPriceById(int product_id){
        executor.execute(new Runnable() {
            @Override
            public void run() {
//                cartDao.getProductPriceById(product_id);
            }
        });
    }
}
