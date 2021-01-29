package com.example.doan_android_2021.screens.dashboard.category;

import com.example.doan_android_2021.BaseContact;
import com.example.doan_android_2021.models.Brand;
import com.example.doan_android_2021.models.Category;

import java.util.List;

public interface CategoryContact {
    interface CategoryView extends BaseContact.BaseView {
        void onGetBrandsSuccess(List<Brand> brands);

        void onGetBrandsFail(String message);
    }

    interface CategoryPresent extends BaseContact.BasePresent {

        void getBrands();
    }
}
