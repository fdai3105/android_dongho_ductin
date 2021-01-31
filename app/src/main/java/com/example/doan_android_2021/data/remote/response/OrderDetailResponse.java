package com.example.doan_android_2021.data.remote.response;

import com.example.doan_android_2021.models.OrderDetail;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderDetailResponse {
    @SerializedName("data")
    public List<OrderDetail> orderDetail;
}
