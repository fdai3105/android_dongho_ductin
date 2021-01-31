package com.example.doan_android_2021.screens.order_detail;

import com.example.doan_android_2021.BaseContact;
import com.example.doan_android_2021.models.OrderDetail;

import java.util.List;

public interface OrderDetailContact {
    interface OrderDetailView extends BaseContact.BaseView {
        void onGetOrderDetailSuccess(List<OrderDetail> orderDetails);

        void onGetOrderDetailFail(String message);
    }

    interface OrderDetailPresent extends BaseContact.BasePresent {
        void getOrderDetail(String token, Long id);
    }
}
