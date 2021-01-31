package com.example.doan_android_2021.screens.order;

import com.example.doan_android_2021.BaseContact;
import com.example.doan_android_2021.models.Order;

import java.util.List;

public interface OrderContact {
    interface OrderView extends BaseContact.BaseView {
        void onGetOrderSuccess(List<Order> orders);

        void onGetOrderFail(String message);
    }

    interface OrderPresent extends BaseContact.BasePresent {
        void getOrder(String token);
    }

}
