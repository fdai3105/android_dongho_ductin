package com.example.doan_android_2021.screens.detail;

import com.example.doan_android_2021.BaseContact;
import com.example.doan_android_2021.models.Cart;
import com.example.doan_android_2021.models.Product;
import com.example.doan_android_2021.models.ProductDatum;

public interface DetailContact {
    interface DetailView extends BaseContact.BaseView {
        void onLoadProductSuccess(ProductDatum product);

        void onLoadProductFail(String message);

        void onAddToCartSuccess();

        void onAddToCartFail(String message);

        void onAuthFail(String message);
    }

    interface DetailPresent extends BaseContact.BasePresent {
        void getDetail(long id);

        void addToCart(long productID, int quantity);
    }
}
