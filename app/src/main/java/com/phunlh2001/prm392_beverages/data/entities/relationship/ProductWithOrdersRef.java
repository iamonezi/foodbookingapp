package com.phunlh2001.prm392_beverages.data.entities.relationship;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.phunlh2001.prm392_beverages.data.entities.Order;
import com.phunlh2001.prm392_beverages.data.entities.OrderDetail;
import com.phunlh2001.prm392_beverages.data.entities.Product;

import java.util.List;

public class ProductWithOrdersRef {
    @Embedded
    public Product product;

    @Relation(
            parentColumn = "id", //Product @id
            entityColumn = "id", //Order @id
            associateBy = @Junction(value = OrderDetail.class,
                    parentColumn = "product_id",
                    entityColumn = "order_id")
    )
    public List<Order> orders;
}
