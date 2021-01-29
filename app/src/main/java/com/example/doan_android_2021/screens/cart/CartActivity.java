package com.example.doan_android_2021.screens.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan_android_2021.R;
import com.example.doan_android_2021.adapters.CartAdapter;
import com.example.doan_android_2021.models.Cart;
import com.example.doan_android_2021.models.ProductDatum;
import com.example.doan_android_2021.utlis.SharedPref;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class CartActivity extends AppCompatActivity implements CartContact.CartView {
    private CartContact.CartPresent present;

    private RelativeLayout view;
    private LinearLayout empty;
    private RecyclerView rvCart;
    private TextView tvTotal;
    private LinearLayout btnCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Toolbar toolbar = findViewById(R.id.cart_toolbar);
        view = findViewById(R.id.cart_view);
        empty = findViewById(R.id.cart_empty);
        rvCart = findViewById(R.id.cart_rv);
        tvTotal = findViewById(R.id.cart_total);
        btnCheckout = findViewById(R.id.cart_checkout);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        present = new CartPresent(this, new SharedPref(this));
        present.getCart(new SharedPref(this).getToken());

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CartActivity.this, "Checkout", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onGetCardSuccess(List<Cart> carts) {
        if (carts.size() == 0) {
            view.setVisibility(View.GONE);
            empty.setVisibility(View.VISIBLE);
            return;
        }
        CartAdapter cartAdapter = new CartAdapter(this, carts, new CartAdapter.CartClickListener() {
            @Override
            public void onRemoveClick(int position) {
                present.removeCart(position);
            }

            @Override
            public void ItemClick(int position) {

            }
        });
        rvCart.setLayoutManager(new LinearLayoutManager(this));
        rvCart.setAdapter(cartAdapter);

        long total = 0;
        for (int i = 0; i < carts.size(); i++) {
            ProductDatum product = carts.get(i).getProduct();
            total += product.getPrice().longValue() * carts.get(i).getQuantity();
        }
        NumberFormat format = new DecimalFormat("#,###");
        tvTotal.setText(format.format(total) + " vnd");
    }

    @Override
    public void onGetCartFail(String message) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}