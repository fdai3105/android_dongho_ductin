package com.example.doan_android_2021.screens.brand;

import com.example.doan_android_2021.data.remote.ApiClient;
import com.example.doan_android_2021.data.remote.response.ProductResponse;
import com.example.doan_android_2021.data.remote.response.ProductsResponse;
import com.example.doan_android_2021.data.remote.services.BrandService;
import com.example.doan_android_2021.data.remote.services.CategoryService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class BrandPresent implements BrandContact.BrandPresent {
    private final BrandContact.BrandView view;
    private final BrandService service;

    BrandPresent(BrandContact.BrandView view) {
        this.view = view;
        this.service = ApiClient.getBrandService();
    }

    @Override
    public void getProductsByBrand(Long id) {
        if (id == null) return;
        view.showProgress();
        service.getProductsByBrand(id).enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                if (response.isSuccessful()) {
                    view.onLoadProductSuccess(response.body().products);
                } else {
                    view.onLoadProductFail("can't load");
                }
                view.hideProgress();
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                view.onLoadProductFail("can't load");
                view.hideProgress();
            }
        });
    }
}
