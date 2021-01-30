package com.example.doan_android_2021.data.remote.services;

import com.example.doan_android_2021.data.remote.response.CategoryResponse;
import com.example.doan_android_2021.data.remote.response.ProductResponse;
import com.example.doan_android_2021.data.remote.response.ProductsResponse;
import com.example.doan_android_2021.models.Category;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CategoryService {
    @GET("/{id}")
    Call<ProductsResponse> getProductsByCategory(@Path("id") long id);

    @GET("category")
    Call<CategoryResponse> getCategories();
}
