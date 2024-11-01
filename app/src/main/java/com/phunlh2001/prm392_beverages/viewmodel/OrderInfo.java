package com.phunlh2001.prm392_beverages.viewmodel;

import java.io.Serializable;

public class OrderInfo implements Serializable {
    private String thumbnail;
    private String title;
    private String description;
    private double price;
    private int quantity;

    public OrderInfo(String thumbnail, String title, String description, double price, int quantity) {
        this.thumbnail = thumbnail;
        this.title = title;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
