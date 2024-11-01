package com.phunlh2001.prm392_beverages.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.phunlh2001.prm392_beverages.data.entities.Product;
import com.phunlh2001.prm392_beverages.data.entities.relationship.ProductWithOrdersRef;

import java.util.List;

@Dao
public interface ProductDao {
    @Insert
    void insert(Product... product);

    @Update
    void update(Product product);

    @Delete
    void delete(Product... product);

    @Query("SELECT * FROM products")
    List<Product> getAll();

    @Query("SELECT * FROM products WHERE id=:id")
    Product getById(int id);

    @Transaction
    @Query("SELECT * FROM products")
    List<ProductWithOrdersRef> getAllOrderHaveProduct();

    @Query("SELECT * FROM products WHERE title LIKE '%' || :name || '%'")
    List<Product> searchProduct(String name);

//    @Query("SELECT * FROM products WHERE id=:id")
//    List<Product> getListProductById(int id);
}
