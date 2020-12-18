package com.example.doan_android_2021.data.remote.services;

import com.example.doan_android_2021.data.remote.repositories.ProductRepository;
import com.example.doan_android_2021.data.remote.repositories.ProductsRepository;
import com.example.doan_android_2021.models.Product;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductService {
    @GET("product/{id}")
    Call<ProductRepository> getProduct(@Path("id") long id);

    @GET("product")
    Call<ProductsRepository> getProducts();
}
