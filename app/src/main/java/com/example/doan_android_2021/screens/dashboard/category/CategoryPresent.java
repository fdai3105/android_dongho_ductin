package com.example.doan_android_2021.screens.dashboard.category;

import com.example.doan_android_2021.data.remote.ApiClient;
import com.example.doan_android_2021.data.remote.response.BrandResponse;
import com.example.doan_android_2021.data.remote.services.BrandService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class CategoryPresent implements CategoryContact.CategoryPresent {
    private final CategoryContact.CategoryView view;
    private final BrandService brandService;

    CategoryPresent(CategoryContact.CategoryView view) {
        this.view = view;
        this.brandService = ApiClient.getBrandService();
    }

    @Override
    public void getBrands() {
        view.showProgress();
        brandService.getBrands().enqueue(new Callback<BrandResponse>() {
            @Override
            public void onResponse(Call<BrandResponse> call, Response<BrandResponse> response) {
                if (response.isSuccessful()) {
                    view.onGetBrandsSuccess(response.body().brands);
                } else {
                    view.onGetBrandsFail("Fail");
                }
                view.hideProgress();
            }

            @Override
            public void onFailure(Call<BrandResponse> call, Throwable t) {
                view.onGetBrandsFail("Fail");
                view.hideProgress();
            }
        });
    }
}
