package com.example.doan_android_2021.screens.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.doan_android_2021.R;
import com.example.doan_android_2021.adapters.ProductAdapter;
import com.example.doan_android_2021.models.Product;
import com.example.doan_android_2021.screens.detail.DetailActivity;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements HomeContact.HomeView {
    private HomePresent homePresent;
    private ProgressBar homePB;
    private RecyclerView homeRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homePresent = new HomePresent(this);
        homePresent.init();

        homeRV = findViewById(R.id.home_cv);
        homePB = findViewById(R.id.home_pb);
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
    public void onLoadProductsSuccess(ArrayList<Product> products) {
        homeRV.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        ProductAdapter productAdapter = new ProductAdapter(getApplicationContext(), products, id -> {
            System.out.println(id);
            Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
            intent.putExtra("id",id);
            startActivity(intent);
        });
        homeRV.setAdapter(productAdapter);
    }

    @Override
    public void onLoadProductsFail() {

    }
}