package com.example.doan_android_2021.data.remote.response;

import com.example.doan_android_2021.models.ProductDatum;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse {
    @SerializedName("data")
    public List<ProductDatum> products;
}
