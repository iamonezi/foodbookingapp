package com.phunlh2001.prm392_beverages.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.phunlh2001.prm392_beverages.data.entities.OrderDetail;
import com.phunlh2001.prm392_beverages.data.entities.Product;

import java.util.List;

@Dao
public interface CartDao {
    @Insert
    void insertCartItem(OrderDetail orderDetail);

//    @Query("SELECT * FROM OrderDetails INNER JOIN Products,Orders ON OrderDetails.product_id = Products.id AND OrderDetails.order_id = Orders.id")
//    LiveData<List<OrderDetail>> getAllCartItems();

    @Delete
    void deleteCartItem(OrderDetail orderDetail);

    @Query("UPDATE OrderDetails SET quantity=:quantity " +
            "WHERE product_id=:product_id AND order_id=:order_id")
    void updateQuantity(int product_id, int order_id, int quantity);

//    @Query("SELECT price FROM Products INNER JOIN OrderDetails ON OrderDetails.product_id = Products.id " +
//            "WHERE product_id=:product_id")
//    Product getProductPriceById(int product_id);

    @Query("DELETE FROM OrderDetails")
    void deleteAllItems();
}
