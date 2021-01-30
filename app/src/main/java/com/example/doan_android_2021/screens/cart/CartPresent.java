package com.example.doan_android_2021.screens.cart;

import android.util.Log;

import com.example.doan_android_2021.data.remote.ApiClient;
import com.example.doan_android_2021.data.remote.response.CartResponse;
import com.example.doan_android_2021.data.remote.services.CartService;
import com.example.doan_android_2021.data.remote.services.UserService;
import com.example.doan_android_2021.models.Address;
import com.example.doan_android_2021.utlis.SharedPref;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class CartPresent implements CartContact.CartPresent {
    private final CartContact.CartView view;
    private final CartService cartService;
    private final UserService userService;
    private final SharedPref pref;

    CartPresent(CartContact.CartView view, SharedPref pref) {
        this.view = view;
        this.pref = pref;
        this.cartService = ApiClient.getCartService();
        this.userService = ApiClient.getUserService();
    }

    @Override
    public void getCart(String token) {
        if (token == null) {
            view.onGetCartFail("Chưa đăng nhập");
            return;
        }
        cartService.getCart(token).enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                view.showProgress();
                if (response.isSuccessful()) {
                    view.onGetCardSuccess(response.body().product);
                } else {
                    try {
                        Log.e("TAG", "onResponse: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    view.onGetCartFail("");
                }
                view.showProgress();
            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void getAddressUser() {
        if (pref.getToken() == null) return;
        List<String> address = new ArrayList<>();
        userService.getAddress(pref.getToken()).enqueue(new Callback<List<Address>>() {
            @Override
            public void onResponse(Call<List<Address>> call, Response<List<Address>> response) {
                if (response.isSuccessful()) {
                    view.onGetAddressSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Address>> call, Throwable t) {

            }
        });
    }

    @Override
    public void removeCart(int id) {
        Map<String, String> body = new HashMap<>();
        body.put("id", id + "");
        cartService.removeCart(body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    getCart(pref.getToken());
                } else {
                    view.onGetCartFail("K xoá được");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    @Override
    public void checkout(int idAddress, String note) {
        if (pref.getToken() == null) return;

        Map<String, String> body = new HashMap<>();
        body.put("address_id", idAddress + "");
        body.put("note", note);
        cartService.checkout(pref.getToken(), body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    view.onCheckoutSuccess();
                } else {
                    view.onGetCartFail("Lỗi checkout");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view.onGetCartFail("Lỗi checkout");

            }
        });
    }
}
