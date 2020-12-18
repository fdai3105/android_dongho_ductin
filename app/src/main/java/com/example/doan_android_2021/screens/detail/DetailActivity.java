package com.example.doan_android_2021.screens.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.doan_android_2021.R;
import com.example.doan_android_2021.models.Product;

public class DetailActivity extends AppCompatActivity implements DetailContact.DetailView {
    private DetailPresent detailPresent;
    private ImageView image;
    private TextView name;
    private TextView price;
    private TextView brandName;
    private TextView categoryName;
    private RatingBar rating;
    private TextView desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        /*---*/
        detailPresent = new DetailPresent(this);
        detailPresent.getDetail(getIntent().getLongExtra("id", 0));
        findViews();
    }

    private void findViews() {
        image = (ImageView) findViewById(R.id.detail_image);
        name = (TextView) findViewById(R.id.detail_name);
        price = (TextView) findViewById(R.id.detail_price);
        brandName = (TextView) findViewById(R.id.detail_brandName);
        categoryName = (TextView) findViewById(R.id.detail_categoryName);
        rating = (RatingBar) findViewById(R.id.detail_rating);
        desc = (TextView) findViewById(R.id.detail_desc);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onLoadProductSuccess(Product product) {
        Glide.with(getApplicationContext()).load(product.image).into(image);
        name.setText(product.name);
        price.setText(product.price + "₫");
        brandName.setText(product.brand.name);
        categoryName.setText(product.category.name);
        rating.setNumStars(product.vote);
        desc.setText(product.desc);
    }

    @Override
    public void onLoadProductFail() {
        System.out.println("Loading fail...");
    }
}