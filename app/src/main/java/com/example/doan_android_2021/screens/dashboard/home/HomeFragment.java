package com.example.doan_android_2021.screens.dashboard.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.doan_android_2021.R;
import com.example.doan_android_2021.adapters.AdapterClickListener;
import com.example.doan_android_2021.adapters.BannerAdapter;
import com.example.doan_android_2021.adapters.HotProductAdapter;
import com.example.doan_android_2021.adapters.ProductAdapter;
import com.example.doan_android_2021.models.Product;
import com.example.doan_android_2021.models.ProductDatum;
import com.example.doan_android_2021.screens.detail.DetailActivity;
import com.example.doan_android_2021.utlis.AutoScroll;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements HomeContact.HomeView {
    private HomePresent present;
    private ProgressBar pb;
    private NestedScrollView nsv;
    private RecyclerView rvProduct;
    private RecyclerView rvHotProduct;
    private LinearLayoutManager rvHotProductManager;
    private RecyclerView rvBanner;
    private LinearLayoutManager rvBannerManager;
    private AutoScroll rvBannerAutoScroll;
    private ProductAdapter productAdapter;
    private BannerAdapter bannerAdapter;
    private HotProductAdapter hotProductAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        nsv = root.findViewById(R.id.home_nsv);
        rvBanner = root.findViewById(R.id.home_rv_banner);
        rvHotProduct = root.findViewById(R.id.home_rv_hot);
        rvProduct = root.findViewById(R.id.home_rv_product);
        pb = root.findViewById(R.id.home_pb);

        rvBannerManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvBanner.setLayoutManager(rvBannerManager);
        rvHotProductManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvHotProduct.setLayoutManager(rvHotProductManager);
        rvProduct.setLayoutManager(new GridLayoutManager(getContext(), 2));

        nsv.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    present.loadMore();
                }
            }
        });

        present = new HomePresent(this);
        present.init();

        return root;
    }

    @Override
    public void showProgress() {
        pb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pb.setVisibility(View.GONE);
    }

    @Override
    public void onLoadProductsSuccess(ArrayList<ProductDatum> products) {
        productAdapter = new ProductAdapter(getContext(), products, id -> {
            Intent intent = new Intent(getContext(), DetailActivity.class);
            intent.putExtra("id", id);
            startActivity(intent);
        });
        rvProduct.setAdapter(productAdapter);
    }

    @Override
    public void onLoadMore(Product product) {
        if (product.getData() == null) {
            return;
        }
        productAdapter.addMore(product);
    }

    @Override
    public void onLoadHotProductsSuccess(List<ProductDatum> hotProducts) {
        hotProductAdapter = new HotProductAdapter(getContext(), hotProducts, new AdapterClickListener() {
            @Override
            public void onItemClickListener(long id) {
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        rvHotProduct.setAdapter(hotProductAdapter);
    }

    @Override
    public void onLoadHotProductsFail(String message) {

    }

    @Override
    public void onLoadProductsFail() {

    }

    @Override
    public void onLoadBannerSuccess(List<String> banners) {
        bannerAdapter = new BannerAdapter(getContext(), banners);
        SnapHelper snap = new PagerSnapHelper();
        snap.attachToRecyclerView(rvBanner);
        rvBanner.smoothScrollBy(5, 0);
        rvBanner.setAdapter(bannerAdapter);
        rvBannerAutoScroll = new AutoScroll(rvBanner, rvBannerManager);
        rvBannerAutoScroll.runAutoScrollBanner();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (rvBannerAutoScroll == null) return;
        rvBannerAutoScroll.stopAutoScrollBanner();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (rvBannerAutoScroll == null) return;
        rvBannerAutoScroll.stopAutoScrollBanner();
    }
}