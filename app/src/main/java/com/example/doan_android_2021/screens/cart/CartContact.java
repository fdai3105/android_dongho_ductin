package com.example.doan_android_2021.screens.cart;

import com.example.doan_android_2021.BaseContact;
import com.example.doan_android_2021.models.Cart;

import java.util.List;

public interface CartContact {
    interface CartView extends BaseContact.BaseView {
        void onGetCardSuccess(List<Cart> carts);

        void onGetCartFail(String message);
    }

    interface CartPresent extends BaseContact.BasePresent {
        void getCart(String token);

        void removeCart(int id);

        void checkout(String token);
    }
}
