package com.example.doan_android_2021.screens.dashboard.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_android_2021.R;
import com.example.doan_android_2021.adapters.ProductAdapter;
import com.example.doan_android_2021.models.Product;
import com.example.doan_android_2021.models.ProductDatum;
import com.example.doan_android_2021.screens.detail.DetailActivity;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements HomeContact.HomeView {
    private HomePresent homePresent;
    private ProgressBar homePB;
    public RecyclerView homeRV;
    private ProductAdapter productAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        homePresent = new HomePresent(this);
        homePresent.init();

        homeRV = root.findViewById(R.id.home_cv);
        homePB = root.findViewById(R.id.home_pb);

        homeRV.setLayoutManager(new GridLayoutManager(getContext(), 2));
        homeRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == productAdapter.getItemCount() - 1) {
                        homePresent.loadMore();
                    }
                }
            }
        });
        return root;
    }

    @Override
    public void showProgress() {
        homePB.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        homePB.setVisibility(View.GONE);
    }

    @Override
    public void onLoadProductsSuccess(ArrayList<ProductDatum> products) {
        productAdapter = new ProductAdapter(getContext(), products, id -> {
            Intent intent = new Intent(getContext(), DetailActivity.class);
            intent.putExtra("id", id);
            startActivity(intent);
        });
        homeRV.setAdapter(productAdapter);
    }

    @Override
    public void onLoadMore(Product product) {
        if (product.getData() == null) {

        }
        productAdapter.addMore(product);
    }

    @Override
    public void onLoadProductsFail() {

    }
}