package com.example.doan_android_2021.screens.dashboard.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.doan_android_2021.R;
import com.example.doan_android_2021.adapters.BannerAdapter;
import com.example.doan_android_2021.adapters.ProductAdapter;
import com.example.doan_android_2021.models.Product;
import com.example.doan_android_2021.models.ProductDatum;
import com.example.doan_android_2021.screens.detail.DetailActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment implements HomeContact.HomeView {
    private HomePresent present;
    private ProgressBar pb;
    private RecyclerView rvProduct;
    private RecyclerView rvBanner;
    private ProductAdapter productAdapter;
    private BannerAdapter bannerAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        rvProduct = root.findViewById(R.id.home_cv);
        rvBanner = root.findViewById(R.id.home_rv_banner);
        pb = root.findViewById(R.id.home_pb);

        present = new HomePresent(this);
        present.init();

        rvProduct.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvBanner.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        rvProduct.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == productAdapter.getItemCount() - 1) {
                        present.loadMore();
                    }
                }
            }
        });
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
    public void onLoadProductsFail() {

    }

    @Override
    public void onLoadBannerSuccess(List<String> banners) {
        bannerAdapter = new BannerAdapter(getContext(), banners);
        SnapHelper snap = new PagerSnapHelper();
        snap.attachToRecyclerView(rvBanner);
        rvBanner.smoothScrollBy(5, 0);
        rvBanner.setAdapter(bannerAdapter);
        runAutoScrollBanner();
    }

    private void runAutoScrollBanner() {
        Timer timer = null;
        TimerTask timerTask = null;
        final int[] position = {0};
        if (timer == null && timerTask == null) {
            timer = new Timer();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    if (position[0] == Integer.MAX_VALUE) {
                        position[0] = Integer.MAX_VALUE / 2;
                        rvBanner.scrollToPosition(position[0]);
                        rvBanner.smoothScrollBy(5, 0);
                    } else {
                        position[0]++;
                        rvBanner.smoothScrollToPosition(position[0]);
                    }
                }
            };
            timer.schedule(timerTask, 3000, 3000);
        }
    }
}