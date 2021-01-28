package com.example.doan_android_2021.utlis;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Headers;

public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit(String base) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(base).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
