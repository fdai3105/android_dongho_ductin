package com.example.doan_android_2021.screens.detail;

import com.example.doan_android_2021.data.remote.ApiClient;
import com.example.doan_android_2021.data.remote.response.ProductResponse;
import com.example.doan_android_2021.data.remote.services.CartService;
import com.example.doan_android_2021.data.remote.services.ProductService;
import com.example.doan_android_2021.utlis.SharedPref;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class DetailPresent implements DetailContact.DetailPresent {
    private DetailContact.DetailView view;
    private final SharedPref pref;
    private final ProductService productService;
    private final CartService cartService;

    public DetailPresent(DetailContact.DetailView view, SharedPref pref) {
        this.view = view;
        this.pref = pref;
        productService = ApiClient.getProductService();
        cartService = ApiClient.getCartService();
    }


    @Override
    public void getDetail(long id) {
        view.showProgress();
        productService.getProduct(id).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()) {
                    if (response.isSuccessful()) {
                        view.onLoadProductSuccess(response.body().product);
                    }
                }
                view.hideProgress();
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                view.onLoadProductFail(t.getMessage());
                view.hideProgress();
            }
        });
    }

    @Override
    public void addToCart(long productID, int quantity) {
        if (pref.getToken() == null) {
            view.onAuthFail("Vui lòng đăng nhập");
            return;
        }

        Map<String, String> body = new HashMap<>();
        body.put("product_id", productID + "");
        body.put("quantity", quantity + "");
        cartService.addToCart(pref.getToken(), body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    view.onAddToCartSuccess();
                } else {
                    view.onAddToCartFail("fail");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view.onAddToCartFail(t.getMessage());
            }
        });
    }
}
