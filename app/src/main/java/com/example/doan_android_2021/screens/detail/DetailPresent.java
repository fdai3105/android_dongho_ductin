package com.example.doan_android_2021.screens.detail;

import com.example.doan_android_2021.data.remote.ApiClient;
import com.example.doan_android_2021.data.remote.services.ProductService;
import com.example.doan_android_2021.models.ProductDatum;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class DetailPresent implements DetailContact.DetailPresent {
    private DetailContact.DetailView detailView;
    private ProductService productService;

    public DetailPresent(DetailContact.DetailView detailView) {
        this.detailView = detailView;
        productService = ApiClient.getProductService();
    }


    @Override
    public void getDetail(long id) {
        productService.getProduct(id).enqueue(new Callback<ProductDatum>() {
            @Override
            public void onResponse(Call<ProductDatum> call, Response<ProductDatum> response) {
                detailView.showProgress();
                if (response.isSuccessful()) {
                    System.out.println(response.body());
                    detailView.onLoadProductSuccess(response.body());
                } else {
                    detailView.onLoadProductFail();
                }
                detailView.hideProgress();
            }

            @Override
            public void onFailure(Call<ProductDatum> call, Throwable t) {

            }
        });
    }
}
