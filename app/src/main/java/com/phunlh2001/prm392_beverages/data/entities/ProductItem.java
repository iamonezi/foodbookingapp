package com.phunlh2001.prm392_beverages.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ProductItem implements Parcelable {
    private String thumbnail;
    private String title;
    private String desc;
    private double price;

    public ProductItem(String thumbnail, String title, String desc, double price) {
        this.thumbnail = thumbnail;
        this.title = title;
        this.desc = desc;
        this.price = price;
    }

    protected ProductItem(Parcel in) {
        thumbnail = in.readString();
        title = in.readString();
        desc = in.readString();
        price = in.readDouble();
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static final Creator<ProductItem> CREATOR = new Creator<ProductItem>() {
        @Override
        public ProductItem createFromParcel(Parcel in) {
            return new ProductItem(in);
        }

        @Override
        public ProductItem[] newArray(int size) {
            return new ProductItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(thumbnail);
        parcel.writeString(title);
        parcel.writeString(desc);
        parcel.writeDouble(price);
    }
}
