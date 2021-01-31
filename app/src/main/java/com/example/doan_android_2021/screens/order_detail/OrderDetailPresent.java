package com.example.doan_android_2021.screens.order_detail;

import com.example.doan_android_2021.data.remote.ApiClient;
import com.example.doan_android_2021.data.remote.response.OrderDetailResponse;
import com.example.doan_android_2021.data.remote.services.OrderService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailPresent implements OrderDetailContact.OrderDetailPresent {
    private final OrderDetailContact.OrderDetailView view;
    private final OrderService service;

    public OrderDetailPresent(OrderDetailContact.OrderDetailView view) {
        this.view = view;
        service = ApiClient.getOrderService();
    }

    @Override
    public void getOrderDetail(String token, Long id) {
        if(token == null) return;
        service.getOrderDetail(token, id).enqueue(new Callback<OrderDetailResponse>() {
            @Override
            public void onResponse(Call<OrderDetailResponse> call, Response<OrderDetailResponse> response) {
                if (response.isSuccessful()) {
                    view.onGetOrderDetailSuccess(response.body().orderDetail);
                } else {
                    try {
                        view.onGetOrderDetailFail(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<OrderDetailResponse> call, Throwable t) {
                view.onGetOrderDetailFail(t.getMessage());
            }
        });
    }
}
