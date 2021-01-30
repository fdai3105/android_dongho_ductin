package com.example.doan_android_2021.data.remote.services;

import com.example.doan_android_2021.data.remote.response.BrandResponse;
import com.example.doan_android_2021.data.remote.response.ProductsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BrandService {
    @GET("brand/{id}")
    Call<ProductsResponse> getProductsByBrand(@Path("id") Long id);

    @GET("brand")
    Call<BrandResponse> getBrands();
}
