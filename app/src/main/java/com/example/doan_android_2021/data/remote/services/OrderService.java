package com.example.doan_android_2021.data.remote.services;

import com.example.doan_android_2021.data.remote.response.OrderDetailResponse;
import com.example.doan_android_2021.data.remote.response.OrderResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface OrderService {
    @GET("order")
    Call<OrderResponse> getOrders(@Header("Authorization") String token);

    @GET("order/{id}")
    Call<OrderDetailResponse> getOrderDetail(@Header("Authorization") String token, @Path("id") Long id);
}
