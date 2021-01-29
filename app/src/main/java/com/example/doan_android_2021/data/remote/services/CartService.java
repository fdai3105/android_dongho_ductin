package com.example.doan_android_2021.data.remote.services;

import com.example.doan_android_2021.data.remote.response.CartResponse;
import com.example.doan_android_2021.models.Order;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface CartService {
    @GET("get-carts")
    Call<CartResponse> getCart(@Header("Authorization") String token);

    @GET("order")
    Call<Order> getOrder(String token);

    @POST("add-to-cart")
    Call<ResponseBody> addToCart(@Header("Authorization") String header, @Body Map<String, String> body);

    @POST("remove-cart")
    Call<ResponseBody> removeCart(@Body Map<String, String> body);
}
