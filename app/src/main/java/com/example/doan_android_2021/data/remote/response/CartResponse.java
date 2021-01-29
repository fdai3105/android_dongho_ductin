package com.example.doan_android_2021.data.remote.response;

import com.example.doan_android_2021.models.Cart;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CartResponse {
    @SerializedName("data")
    @Expose
    public List<Cart> product;
}
