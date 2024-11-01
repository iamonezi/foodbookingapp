package com.phunlh2001.prm392_beverages.data.entities.relationship;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.phunlh2001.prm392_beverages.data.entities.Order;
import com.phunlh2001.prm392_beverages.data.entities.OrderDetail;
import com.phunlh2001.prm392_beverages.data.entities.Product;

import java.util.List;

public class OrderWithProductsRef {
    @Embedded
    public Order order;

    @Relation(
            parentColumn = "id", //Order @id
            entityColumn = "id", //Product @id
            associateBy = @Junction(value = OrderDetail.class,
                    parentColumn = "order_id",
                    entityColumn = "product_id")
    )
    public List<Product> products;
}
