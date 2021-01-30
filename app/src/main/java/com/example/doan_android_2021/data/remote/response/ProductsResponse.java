package com.example.doan_android_2021.data.remote.response;

import com.example.doan_android_2021.models.ProductDatum;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;

public class ProductsResponse {
    @SerializedName("data")
    public List<ProductDatum> products;
}
