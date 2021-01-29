package com.example.doan_android_2021.screens.dashboard.category;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_android_2021.R;
import com.example.doan_android_2021.adapters.BrandAdapter;
import com.example.doan_android_2021.models.Brand;
import com.example.doan_android_2021.screens.category.CategoryActivity;

import java.util.List;

public class CategoryFragment extends Fragment implements CategoryContact.CategoryView {
    private CategoryPresent present;

    private RecyclerView categoryRV;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_category, container, false);

        /**/
        categoryRV = root.findViewById(R.id.category_rv);

        present = new CategoryPresent(this);
        present.getBrands();

        categoryRV.setLayoutManager(new GridLayoutManager(getContext(), 2));

        return root;
    }

    @Override
    public void onGetBrandsSuccess(List<Brand> brands) {
        BrandAdapter brandAdapter = new BrandAdapter(getContext(), brands, new BrandAdapter.CategoryClickListener() {
            @Override
            public void onClick(int id) {
                Intent intent = new Intent(getContext(), CategoryActivity.class);
                intent.putExtra("id", 0);
                startActivity(intent);
            }
        });
        categoryRV.setAdapter(brandAdapter);
    }

    @Override
    public void onGetBrandsFail(String message) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}