package com.example.doan_android_2021.screens.brand;

import com.example.doan_android_2021.BaseContact;
import com.example.doan_android_2021.models.ProductDatum;

import java.util.List;

public interface BrandContact {
    interface BrandView extends BaseContact.BaseView {
        void onLoadProductSuccess(List<ProductDatum> products);

        void onLoadProductFail(String message);
    }

    interface BrandPresent  extends BaseContact.BasePresent {
        void getProductsByBrand(Long id);
    }

}
