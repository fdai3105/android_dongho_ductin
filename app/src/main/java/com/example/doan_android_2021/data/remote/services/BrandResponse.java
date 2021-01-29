package com.example.doan_android_2021.data.remote.services;

import com.example.doan_android_2021.models.Brand;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BrandResponse {
    @SerializedName("data")
    public List<Brand> brands;
}
