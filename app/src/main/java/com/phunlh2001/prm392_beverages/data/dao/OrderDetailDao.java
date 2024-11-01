package com.phunlh2001.prm392_beverages.data.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.phunlh2001.prm392_beverages.data.entities.OrderDetail;
import com.phunlh2001.prm392_beverages.data.entities.Product;
import com.phunlh2001.prm392_beverages.viewmodel.OrderInfo;

import java.util.List;

@Dao
public interface OrderDetailDao {
    @Query("SELECT Products.thumbnail, Products.title, Products.description, Products.price, OrderDetails.quantity from Products left join OrderDetails on OrderDetails.product_id = Products.id\n" +
            "where OrderDetails.order_id=:id")
    List<OrderInfo> getProductNameByOrderId(int id);
}
