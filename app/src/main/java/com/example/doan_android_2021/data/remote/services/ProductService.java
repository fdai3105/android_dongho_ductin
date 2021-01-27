package com.example.doan_android_2021.data.remote.services;

import com.example.doan_android_2021.models.Product;
import com.example.doan_android_2021.models.ProductDatum;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductService {
    @GET("product/{id}")
    @SerializedName("data")
    Call<ProductDatum> getProduct(@Path("id") long id);

    @GET("product")
    Call<Product> getProducts();

    @GET("product")
    Call<Product> loadMore(@Query("page") long id);
}
