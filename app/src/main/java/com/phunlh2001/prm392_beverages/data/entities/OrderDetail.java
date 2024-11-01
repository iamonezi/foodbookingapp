package com.phunlh2001.prm392_beverages.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(tableName = "OrderDetails",
        primaryKeys = {"product_id", "order_id"},
        foreignKeys = {
            @ForeignKey(entity = Product.class,
                    parentColumns = "id",
                    childColumns = "product_id",
                    onDelete = ForeignKey.CASCADE),
            @ForeignKey(entity = Order.class,
                    parentColumns = "id",
                    childColumns = "order_id",
                    onDelete = ForeignKey.CASCADE)
        },
        indices = {
                @Index(value = "product_id"),
                @Index(value = "order_id")
        })
public class OrderDetail {
    public int product_id;
    public int order_id;
    public int quantity;

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
