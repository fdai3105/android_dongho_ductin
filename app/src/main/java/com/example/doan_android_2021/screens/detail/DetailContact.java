package com.example.doan_android_2021.screens.detail;

import com.example.doan_android_2021.BaseContact;
import com.example.doan_android_2021.models.Product;

public interface DetailContact {
    interface DetailView extends BaseContact.BaseView {
        void onLoadProductSuccess(Product product);
        void onLoadProductFail();
    }

    interface DetailPresent extends BaseContact.BasePresent {
        void getDetail(long id);
    }
}
