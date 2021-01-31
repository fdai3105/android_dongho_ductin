package com.example.doan_android_2021.screens.dashboard.brand;

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

public class BrandFragment extends Fragment implements BrandContact.BrandView {
    private BrandPresent present;

    private ProgressBar pb;
    private RecyclerView rvBrand;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_brand, container, false);

        /**/
        pb = root.findViewById(R.id.fm_brand_pb);
        rvBrand = root.findViewById(R.id.fm_brand_rv);

        present = new BrandPresent(this);
        present.getBrands();

        rvBrand.setLayoutManager(new GridLayoutManager(getContext(), 2));

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
        rvBrand.setAdapter(brandAdapter);
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