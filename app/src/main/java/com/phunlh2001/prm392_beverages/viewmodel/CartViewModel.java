package com.phunlh2001.prm392_beverages.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.phunlh2001.prm392_beverages.data.entities.OrderDetail;
import com.phunlh2001.prm392_beverages.repository.CartRepo;

import java.util.List;

public class CartViewModel extends AndroidViewModel {
    private CartRepo cartRepo;

    public CartViewModel(@NonNull Application application) {
        super(application);
        cartRepo = new CartRepo(application);
    }

    public LiveData<List<OrderDetail>> getAllCartItems() {
        return cartRepo.getAllCartItemsLiveData();
    }

    public void insertCartItem(OrderDetail orderDetail) {
        cartRepo.insertCartItem(orderDetail);
    }

    public void updateQuantity(int product_id, int order_id, int quantity) {
        cartRepo.updateQuantity(product_id, order_id, quantity);
    }

    public void deleteCartItem(OrderDetail orderDetail) {
        cartRepo.deleteCartItem(orderDetail);
    }

    public void deleteAllCartItems() {
        cartRepo.deleteAllCartItems();
    }

    public void getProductPriceById(int product_id) {
        cartRepo.getProductPriceById(product_id);
    }
}
