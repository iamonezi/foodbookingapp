package com.phunlh2001.prm392_beverages.data.entities.relationship;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.phunlh2001.prm392_beverages.data.entities.Order;
import com.phunlh2001.prm392_beverages.data.entities.User;

import java.util.List;

public class UserEmbedded {
    @Embedded
    public User user;

    @Relation(
            parentColumn = "id",
            entityColumn = "user_id"
    )
    public List<Order> orders;
}
