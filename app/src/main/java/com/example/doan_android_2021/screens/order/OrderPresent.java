package com.example.doan_android_2021.screens.order;

import android.util.Log;

import com.example.doan_android_2021.data.remote.ApiClient;
import com.example.doan_android_2021.data.remote.response.OrderResponse;
import com.example.doan_android_2021.data.remote.services.OrderService;
import com.example.doan_android_2021.utlis.SharedPref;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class OrderPresent implements OrderContact.OrderPresent {
    private final OrderContact.OrderView view;
    private final OrderService service;

    OrderPresent(OrderContact.OrderView view) {
        this.view = view;
        service = ApiClient.getOrderService();
    }

    @Override
    public void getOrder(String token) {
        service.getOrders(token).enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                if (response.isSuccessful()) {
                    view.onGetOrderSuccess(response.body().orders);
                } else {
                    view.onGetOrderFail("fail");
                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                view.onGetOrderFail(t.getMessage());
            }
        });
    }
}
