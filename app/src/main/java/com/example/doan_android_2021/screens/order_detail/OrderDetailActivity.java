package com.example.doan_android_2021.screens.order_detail;

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
import com.example.doan_android_2021.adapters.OrderDetailAdapter;
import com.example.doan_android_2021.models.OrderDetail;
import com.example.doan_android_2021.screens.detail.DetailActivity;
import com.example.doan_android_2021.utlis.SharedPref;

import java.util.List;

public class OrderDetailActivity extends AppCompatActivity implements OrderDetailContact.OrderDetailView {
    private OrderDetailContact.OrderDetailPresent present;

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        Toolbar tb = findViewById(R.id.order_toolbar);
        rv = findViewById(R.id.order_detail_rv);

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

        present = new OrderDetailPresent(this);
        present.getOrderDetail(new SharedPref(this).getToken(), getIntent().getLongExtra("id", 0));
    }

    @Override
    public void onGetOrderDetailSuccess(List<OrderDetail> orderDetails) {
        OrderDetailAdapter adapter = new OrderDetailAdapter(this, orderDetails, new AdapterClickListener() {
            @Override
            public void onItemClickListener(long id) {
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        rv.setAdapter(adapter);
    }

    @Override
    public void onGetOrderDetailFail(String message) {
        Log.e("TAG", "onGetOrderDetailFail: " + message);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}