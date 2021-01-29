package com.example.doan_android_2021.screens.dashboard.home;

import com.example.doan_android_2021.BaseContact;
import com.example.doan_android_2021.models.Product;
import com.example.doan_android_2021.models.ProductDatum;

import java.util.ArrayList;
import java.util.List;

public interface HomeContact {
    interface HomeView extends BaseContact.BaseView {
        void onLoadProductsSuccess(ArrayList<ProductDatum> products);

        void onLoadProductsFail();

        void onLoadBannerSuccess(List<String> banners);

        void onLoadMore(Product product);

    }

    interface HomePresent extends BaseContact.BasePresent {
        void getProducts();

        void getBanners();

        void loadMore();
    }
}
