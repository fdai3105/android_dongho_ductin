package com.example.doan_android_2021.screens.brand;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.doan_android_2021.R;
import com.example.doan_android_2021.adapters.AdapterClickListener;
import com.example.doan_android_2021.adapters.ProductAdapter;
import com.example.doan_android_2021.models.ProductDatum;
import com.example.doan_android_2021.screens.detail.DetailActivity;

import java.util.List;

public class BrandActivity extends AppCompatActivity implements BrandContact.BrandView {
    private BrandContact.BrandPresent present;

    private ProgressBar pb;
    private TextView tvBrand;
    private RecyclerView rvProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand);

        Toolbar myToolbar = findViewById(R.id.brand_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        myToolbar.setNavigationIcon(R.drawable.ic_back);
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        pb = findViewById(R.id.brand_pb);
        tvBrand = findViewById(R.id.brand_name);
        rvProduct = findViewById(R.id.brand_rv);

        present = new BrandPresent(this);
        present.getProductsByBrand(getIntent().getLongExtra("id", 0));

        tvBrand.setText(getIntent().getStringExtra("name"));
    }

    @Override
    public void onLoadProductSuccess(List<ProductDatum> products) {
        ProductAdapter adapter = new ProductAdapter(this, products, new AdapterClickListener() {
            @Override
            public void onItemClickListener(long id) {
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        rvProduct.setLayoutManager(new GridLayoutManager(this, 2));
        rvProduct.setAdapter(adapter);
    }

    @Override
    public void onLoadProductFail(String message) {

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