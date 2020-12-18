package com.example.doan_android_2021.screens.home;

import com.example.doan_android_2021.BaseContact;
import com.example.doan_android_2021.models.Product;

import java.util.ArrayList;

public interface HomeContact {
    interface HomeView extends BaseContact.BaseView {
        void onLoadProductsSuccess(ArrayList<Product> products);
        void onLoadProductsFail();
    }

    interface HomePresent extends BaseContact.BasePresent {
        void getProducts(int offset);
    }
}
