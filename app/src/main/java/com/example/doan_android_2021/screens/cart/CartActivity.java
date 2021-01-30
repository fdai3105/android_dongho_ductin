package com.example.doan_android_2021.screens.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan_android_2021.R;
import com.example.doan_android_2021.adapters.CartAdapter;
import com.example.doan_android_2021.models.Address;
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

    private Dialog checkoutDialog;
    private Dialog addAddressDialog;
    private RadioGroup rgAddress;

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

        checkoutDialog = new Dialog(this);
        addAddressDialog = new Dialog(this);
        present = new CartPresent(this, new SharedPref(this));
        present.getCart(new SharedPref(this).getToken());

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogCheckout();
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

    private void showDialogCheckout() {
        present.getAddressUser();
        checkoutDialog.setContentView(R.layout.dialog_checkout);
        checkoutDialog.setCancelable(true);

        EditText etCheckoutNote = checkoutDialog.findViewById(R.id.dialog_checkout_note);
        rgAddress = checkoutDialog.findViewById(R.id.dialog_checkout_rg);
        Button btnCheckoutAddAddress = checkoutDialog.findViewById(R.id.dialog_checkout_add_address);
        Button btnSubmit = checkoutDialog.findViewById(R.id.dialog_checkout_submit);

        btnCheckoutAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //checkoutDialog.dismiss();
                showAddAddressDialog();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                present.checkout(rgAddress.getCheckedRadioButtonId(), etCheckoutNote.getText().toString());
            }
        });
        checkoutDialog.show();
    }

    private void showAddAddressDialog() {
        addAddressDialog.setContentView(R.layout.dialog_add_address);
        addAddressDialog.setCancelable(true);

        addAddressDialog.show();
    }


    @Override
    public void onGetAddressSuccess(List<Address> address) {
        for (int i = 0; i < address.size(); i++) {
            RadioButton rdb = new RadioButton(this);
            rdb.setId(address.get(i).getId());
            rdb.setText(address.get(i).getCity() + ", " + address.get(i).getDistrict() + "," + address.get(i).getWard() + ", " + address.get(i).getAddress());
            rgAddress.addView(rdb);
        }
    }

    @Override
    public void onCheckoutSuccess() {
        Toast.makeText(this, "Checkout thành công", Toast.LENGTH_SHORT).show();
        checkoutDialog.dismiss();
        onBackPressed();
    }

    @Override
    public void onCheckoutFail(String message) {
        Toast.makeText(this, "Lỗi checkout", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}