package com.example.doan_android_2021.data.remote.response;

import com.example.doan_android_2021.models.ProductDatum;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductResponse {
    @SerializedName("data")
    @Expose
    public ProductDatum product;
}
