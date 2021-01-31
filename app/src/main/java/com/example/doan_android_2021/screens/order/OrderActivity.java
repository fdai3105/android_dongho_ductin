package com.example.doan_android_2021.screens.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.doan_android_2021.R;
import com.example.doan_android_2021.adapters.AdapterClickListener;
import com.example.doan_android_2021.adapters.OrderAdapter;
import com.example.doan_android_2021.models.Order;
import com.example.doan_android_2021.screens.order_detail.OrderDetailActivity;
import com.example.doan_android_2021.utlis.SharedPref;

import java.util.List;

public class OrderActivity extends AppCompatActivity implements OrderContact.OrderView {
    private OrderContact.OrderPresent present;

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Toolbar tb = findViewById(R.id.order_toolbar);
        rv = findViewById(R.id.order_rv);

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
        rv.setLayoutManager(new LinearLayoutManager(this));

        present = new OrderPresent(this);
        present.getOrder(new SharedPref(this).getToken());
    }

    @Override
    public void onGetOrderSuccess(List<Order> orders) {
        OrderAdapter adapter = new OrderAdapter(this, orders, new AdapterClickListener() {
            @Override
            public void onItemClickListener(long id) {
                Intent intent = new Intent(getApplicationContext(), OrderDetailActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        rv.setAdapter(adapter);
    }

    @Override
    public void onGetOrderFail(String message) {
        Log.e("TAG", "onGetOrderFail: " + message);
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}