package com.example.doan_android_2021.screens.detail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.doan_android_2021.R;
import com.example.doan_android_2021.models.Cart;
import com.example.doan_android_2021.models.ProductDatum;
import com.example.doan_android_2021.screens.cart.CartActivity;
import com.example.doan_android_2021.utlis.SharedPref;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class DetailActivity extends AppCompatActivity implements DetailContact.DetailView {
    private DetailPresent detailPresent;
    private ImageView image;
    private TextView tvName;
    private TextView tvPrice;
    private TextView tvBrand;
    private TextView tvCategory;
    private TextView tvMaterial;
    private TextView tvBandMaterial;
    private TextView tvStyle;
    private TextView tvGender;
    private TextView tvSize;
    private TextView tvWaterproof;
    private TextView tvDesc;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        findViews();

        Toolbar tb = findViewById(R.id.detail_toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        tb.setNavigationIcon(R.drawable.ic_back);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        getSupportActionBar().setTitle("");

        /*---*/
        detailPresent = new DetailPresent(this, new SharedPref(getApplicationContext()));
        detailPresent.getDetail(getIntent().getLongExtra("id", 0));
    }

    private void findViews() {
        image = findViewById(R.id.detail_image);
        tvName = findViewById(R.id.detail_name);
        tvPrice = findViewById(R.id.detail_price);
        tvBrand = findViewById(R.id.detail_brandName);
        tvCategory = findViewById(R.id.detail_categoryName);
        tvSize = findViewById(R.id.detail_size);
        tvGender = findViewById(R.id.detail_gender);
        tvMaterial = findViewById(R.id.detail_material);
        tvBandMaterial = findViewById(R.id.detail_band_material);
        tvSize = findViewById(R.id.detail_size);
        tvStyle = findViewById(R.id.detail_style);
        tvWaterproof = findViewById(R.id.detail_waterproof);
        tvDesc = findViewById(R.id.detail_desc);
        btnAdd = findViewById(R.id.detail_btn_add);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.detail_ab_cart:
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onLoadProductSuccess(ProductDatum product) {
        NumberFormat format = new DecimalFormat("#,###");

        Glide.with(getApplicationContext()).load(product.getImages().get(0).getImage()).into(image);
        tvName.setText(product.getName());
        tvPrice.setText(format.format(product.getPrice()) + "₫");
        tvBrand.setText(product.getBrand().getName());
        tvCategory.setText(product.getCategory().getName());
        tvSize.setText(product.getSize() + "mm");
        tvGender.setText(product.getGender().getName());
        tvMaterial.setText(product.getMaterial().getName());
        tvBandMaterial.setText(product.getBandMaterial().getName());
        tvStyle.setText(product.getStyle().getName());
        tvWaterproof.setText(product.getWaterproof() + " ATM");
        tvDesc.setText(product.getDesc());

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detailPresent.addToCart(product.getId(), 1);
            }
        });
    }

    @Override
    public void onLoadProductFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAuthFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}