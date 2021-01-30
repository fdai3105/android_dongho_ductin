package com.example.doan_android_2021.data.remote.services;

import com.example.doan_android_2021.models.Address;
import com.example.doan_android_2021.models.User;
import com.google.gson.JsonElement;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserService {
    @POST("login")
    @Headers({"Accept: application/json"})
    Call<User> login(@Body Map<String, String> body);

    @POST("register")
    @Headers({"Accept: application/json"})
    Call<JsonElement> register(@Body Map<String, String> body);

    @GET("user-address")
    Call<List<Address>> getAddress(@Header("Authorization") String token);
}
