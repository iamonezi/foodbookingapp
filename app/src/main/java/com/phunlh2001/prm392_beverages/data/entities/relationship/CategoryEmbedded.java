package com.phunlh2001.prm392_beverages.data.entities.relationship;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.phunlh2001.prm392_beverages.data.entities.Category;
import com.phunlh2001.prm392_beverages.data.entities.Product;

import java.util.List;

public class CategoryEmbedded {
    @Embedded
    public Category category;

    @Relation(
            parentColumn = "id",
            entityColumn = "category_id"
    )
    public List<Product> products;
}
