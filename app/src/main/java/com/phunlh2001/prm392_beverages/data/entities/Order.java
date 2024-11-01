package com.phunlh2001.prm392_beverages.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.phunlh2001.prm392_beverages.data.entities.enums.OrderStatus;
import com.phunlh2001.prm392_beverages.data.entities.enums.OrderType;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "Orders",
        foreignKeys = @ForeignKey(entity = User.class,
                parentColumns = "id",
                childColumns = "user_id",
                onDelete = ForeignKey.CASCADE),
        indices = @Index(value = "user_id"))
public class Order implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private double total_price;
    private OrderType type;
    private OrderStatus status;
    private int user_id;
    @ColumnInfo(name = "createAt", defaultValue = "(datetime('now', 'localtime'))")
    private Date createAt;

    public Order(double total_price, int user_id) {
        this.total_price = total_price;
        this.user_id = user_id;

        this.type = OrderType.DELIVERY;
        this.status = OrderStatus.PREPARING;
        this.createAt = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
