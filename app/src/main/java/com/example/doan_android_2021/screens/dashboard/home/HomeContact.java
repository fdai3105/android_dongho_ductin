package com.example.doan_android_2021.screens.dashboard.home;

import com.example.doan_android_2021.BaseContact;
import com.example.doan_android_2021.models.Product;
import com.example.doan_android_2021.models.ProductDatum;

import java.util.ArrayList;
import java.util.List;

public interface HomeContact {
    interface HomeView extends BaseContact.BaseView {
        void onLoadBannerSuccess(List<String> banners);

        void onLoadProductsSuccess(ArrayList<ProductDatum> products);

        void onLoadProductsFail();

        void onLoadMore(Product product);

        void onLoadHotProductsSuccess(List<ProductDatum> hotProducts);

        void onLoadHotProductsFail(String message);
    }

    interface HomePresent extends BaseContact.BasePresent {
        void getBanners();

        void getHotProducts();

        void getProducts(long page);

        void loadMore();
    }
}
