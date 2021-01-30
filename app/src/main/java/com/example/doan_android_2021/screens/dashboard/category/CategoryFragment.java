package com.example.doan_android_2021.screens.dashboard.category;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_android_2021.R;
import com.example.doan_android_2021.adapters.BrandAdapter;
import com.example.doan_android_2021.models.Brand;
import com.example.doan_android_2021.screens.brand.BrandActivity;

import java.util.List;

public class CategoryFragment extends Fragment implements CategoryContact.CategoryView {
    private CategoryPresent present;

    private ProgressBar pb;
    private RecyclerView categoryRV;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_brand, container, false);

        /**/
        pb = root.findViewById(R.id.fm_brand_pb);
        categoryRV = root.findViewById(R.id.fm_brand_rv);

        present = new CategoryPresent(this);
        present.getBrands();

        categoryRV.setLayoutManager(new GridLayoutManager(getContext(), 2));

        return root;
    }

    @Override
    public void onGetBrandsSuccess(List<Brand> brands) {
        BrandAdapter brandAdapter = new BrandAdapter(getContext(), brands, new BrandAdapter.CategoryClickListener() {
            @Override
            public void onClick(Long id, String name) {
                Intent intent = new Intent(getContext(), BrandActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("name", name);
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
        pb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pb.setVisibility(View.GONE);
    }
}