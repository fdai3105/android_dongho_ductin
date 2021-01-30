package com.example.doan_android_2021.screens.dashboard.home;


import com.example.doan_android_2021.data.remote.ApiClient;
import com.example.doan_android_2021.data.remote.response.HotProductResponse;
import com.example.doan_android_2021.data.remote.services.ProductService;
import com.example.doan_android_2021.models.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class HomePresent implements HomeContact.HomePresent {
    private final HomeContact.HomeView view;
    private final ProductService productService;
    public Product product;

    public HomePresent(HomeContact.HomeView view) {
        this.view = view;
        productService = ApiClient.getProductService();
    }

    void init() {
        getProducts(1);
        getBanners();
        getHotProducts();
    }

    @Override
    public void getProducts(long page) {
        productService.getProducts(page).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                view.showProgress();
                product = response.body();
                view.onLoadProductsSuccess(product.getData());
                view.hideProgress();
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });
    }

    @Override
    public void getBanners() {
        List<String> banner = new ArrayList<>();
        banner.add("https://cdn.tgdd.vn/2021/01/banner/1200-350-1200x350-2.png");
        banner.add("https://www.dangquangwatch.vn/upload/slideshow/346623209_dang_quang_watch_2021.jpg");
        banner.add("https://www.dangquangwatch.vn/upload/slideshow/2113663755_dang_quang_watch_cuoi_2021.jpg");
        view.onLoadBannerSuccess(banner);
    }

    @Override
    public void getHotProducts() {
        productService.getHotProducts().enqueue(new Callback<HotProductResponse>() {
            @Override
            public void onResponse(Call<HotProductResponse> call, Response<HotProductResponse> response) {
                if (response.isSuccessful()) {
                    view.onLoadHotProductsSuccess(response.body().hotProducts);
                } else {
                    view.onLoadHotProductsFail("cant get");
                }
            }

            @Override
            public void onFailure(Call<HotProductResponse> call, Throwable t) {
                view.onLoadHotProductsFail("cant get");
            }
        });
    }

    @Override
    public void loadMore() {
        if (product.getMeta().getCurrentPage() + 1 > product.getMeta().getLastPage()) return;
        productService.getProducts(product.getMeta().getCurrentPage() + 1).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    product = response.body();
                    view.onLoadMore(product);
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });
    }
}
