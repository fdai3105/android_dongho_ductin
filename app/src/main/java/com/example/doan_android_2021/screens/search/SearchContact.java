package com.example.doan_android_2021.screens.search;

import com.example.doan_android_2021.BaseContact;
import com.example.doan_android_2021.models.ProductDatum;

import java.util.List;

public interface SearchContact {
    interface SearchView extends BaseContact.BaseView {
        void onSearchSuccess(List<ProductDatum> products);

        void onSearchFail(String message);
    }

    interface SearchPresent extends BaseContact.BasePresent {
        void onSearching(String keyword);
    }
}
