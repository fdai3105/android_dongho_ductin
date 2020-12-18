package com.example.doan_android_2021.screens.home;

import com.example.doan_android_2021.data.remote.ApiClient;
import com.example.doan_android_2021.data.remote.repositories.ProductsRepository;
import com.example.doan_android_2021.data.remote.services.ProductService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class HomePresent implements HomeContact.HomePresent {
    private HomeContact.HomeView homeView;
    private ProductService productService;

    public HomePresent(HomeContact.HomeView homeView) {
        this.homeView = homeView;
        productService = ApiClient.getProductService();
    }

    void init() {
        getProducts(0);
    }

    @Override
    public void getProducts(int offset) {
        productService.getProducts().enqueue(new Callback<ProductsRepository>() {
            @Override
            public void onResponse(Call<ProductsRepository> call, Response<ProductsRepository> response) {
                homeView.showProgress();
                homeView.onLoadProductsSuccess(response.body().product);
                homeView.hideProgress();
            }

            @Override
            public void onFailure(Call<ProductsRepository> call, Throwable t) {
            }
        });
    }
}
