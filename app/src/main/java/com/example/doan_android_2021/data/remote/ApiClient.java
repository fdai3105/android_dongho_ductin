package com.example.doan_android_2021.data.remote;

import com.example.doan_android_2021.data.remote.services.BrandService;
import com.example.doan_android_2021.data.remote.services.CategoryService;
import com.example.doan_android_2021.data.remote.services.ProductService;
import com.example.doan_android_2021.utlis.RetrofitClient;

public class ApiClient {
    public static final String BASE_URL = "http://fd-blog.herokuapp.com/api/";

    public static ProductService getProductService() {
        return RetrofitClient.getRetrofit(BASE_URL).create(ProductService.class);
    }

    public static BrandService getBrandService() {
        return RetrofitClient.getRetrofit(BASE_URL).create(BrandService.class);
    }

    public static CategoryService getCategoryService() {
        return RetrofitClient.getRetrofit(BASE_URL).create(CategoryService.class);
    }
}
