package com.example.doan_android_2021.screens.dashboard.brand;

import com.example.doan_android_2021.BaseContact;
import com.example.doan_android_2021.models.Brand;

import java.util.List;

public interface BrandContact {
    interface BrandView extends BaseContact.BaseView {
        void onGetBrandsSuccess(List<Brand> brands);

        void onGetBrandsFail(String message);
    }

    interface BrandPresent extends BaseContact.BasePresent {
        void getBrands();
    }
}
