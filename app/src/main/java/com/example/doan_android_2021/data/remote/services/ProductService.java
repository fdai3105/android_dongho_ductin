package com.example.doan_android_2021.data.remote.services;

import com.example.doan_android_2021.data.remote.response.ProductResponse;
import com.example.doan_android_2021.models.Product;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductService {
    @GET("product/{id}")
    Call<ProductResponse> getProduct(@Path("id") long id);

    @GET("product")
    Call<Product> getProducts();

    @GET("product")
    Call<Product> loadMore(@Query("page") long id);
}
