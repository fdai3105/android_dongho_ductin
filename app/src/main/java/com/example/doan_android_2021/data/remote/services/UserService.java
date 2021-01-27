package com.example.doan_android_2021.data.remote.services;

import com.example.doan_android_2021.models.User;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("login")
    Call<User> login(@Body Map<String, String> body);

    @POST("register")
    Call<User> register();
}
