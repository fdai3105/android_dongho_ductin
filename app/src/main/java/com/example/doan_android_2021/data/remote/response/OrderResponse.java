package com.example.doan_android_2021.data.remote.response;

import com.example.doan_android_2021.models.Order;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderResponse {
    @SerializedName("data")
    public List<Order> orders;
}
