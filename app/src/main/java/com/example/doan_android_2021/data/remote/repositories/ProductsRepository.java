package com.example.doan_android_2021.data.remote.repositories;

import com.example.doan_android_2021.models.Product;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductsRepository {
    @SerializedName("data")
    public ArrayList<Product> product;
}

