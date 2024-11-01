package com.phunlh2001.prm392_beverages.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.phunlh2001.prm392_beverages.data.entities.Order;
import com.phunlh2001.prm392_beverages.data.entities.relationship.OrderWithProductsRef;

import java.util.List;

@Dao
public interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Order... orders);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Order orders);

    @Delete
    void delete(Order... orders);

    @Query("SELECT * FROM orders")
    List<Order> getAll();

    @Query("SELECT * FROM orders WHERE id=:id")
    Order getById(int id);

    @Query("SELECT * FROM orders WHERE user_id=:id")
    List<Order> getOrderByUserId(int id);

    @Transaction
    @Query("SELECT * FROM orders")
    List<OrderWithProductsRef> getAllProductInOrder();
}
