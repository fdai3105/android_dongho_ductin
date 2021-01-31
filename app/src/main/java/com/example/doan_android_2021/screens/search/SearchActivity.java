package com.example.doan_android_2021.screens.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.doan_android_2021.R;
import com.example.doan_android_2021.adapters.AdapterClickListener;
import com.example.doan_android_2021.adapters.ProductAdapter;
import com.example.doan_android_2021.models.ProductDatum;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchContact.SearchView {
    private EditText input;
    private ProgressBar pb;
    private RecyclerView rv;
    private TextView empty;

    private SearchPresent present;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        input = findViewById(R.id.search_input);
        pb = findViewById(R.id.search_pb);
        rv = findViewById(R.id.search_rv);
        empty = findViewById(R.id.search_empty);

        Toolbar tb = findViewById(R.id.search_tb);
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
        rv.setLayoutManager(new GridLayoutManager(this, 2));

        present = new SearchPresent(this);

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                present.onSearching(editable.toString());
            }
        });

    }

    @Override
    public void onSearchSuccess(List<ProductDatum> products) {
        if (products.isEmpty()) {
            empty.setVisibility(View.VISIBLE);
            return;
        }
        empty.setVisibility(View.GONE);
        ProductAdapter adapter = new ProductAdapter(this, products, new AdapterClickListener() {
            @Override
            public void onItemClickListener(long id) {

            }
        });
        rv.setAdapter(adapter);
    }

    @Override
    public void onSearchFail(String message) {

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