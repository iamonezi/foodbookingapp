package com.phunlh2001.prm392_beverages.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.phunlh2001.prm392_beverages.data.entities.Category;
import com.phunlh2001.prm392_beverages.data.entities.relationship.CategoryEmbedded;

import java.util.List;

@Dao
public interface CategoryDao {
    @Insert
    void insert(Category... category);

    @Update
    void update(Category category);

    @Delete
    void delete(Category... category);

    @Query("SELECT * FROM categories")
    List<Category> getAll();

    @Query("SELECT * FROM categories WHERE id=:id")
    Category getById(int id);

    @Transaction
    @Query("SELECT * FROM categories")
    List<CategoryEmbedded> getAllProductByCategory();
}
