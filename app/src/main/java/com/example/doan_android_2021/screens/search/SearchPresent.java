package com.example.doan_android_2021.screens.search;

import com.example.doan_android_2021.data.remote.ApiClient;
import com.example.doan_android_2021.data.remote.response.SearchResponse;
import com.example.doan_android_2021.data.remote.services.ProductService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresent implements SearchContact.SearchPresent {
    private final SearchContact.SearchView view;
    private final ProductService service;

    public SearchPresent(SearchContact.SearchView view) {
        this.view = view;
        service = ApiClient.getProductService();
    }

    @Override
    public void onSearching(String keyword) {
        if (keyword == null) return;
        if (keyword.length() == 0) return;
        view.showProgress();
        service.getSearchProduct(keyword).enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.isSuccessful()) {
                    view.onSearchSuccess(response.body().products);
                } else {
                    view.onSearchFail("fail");
                }
                view.hideProgress();
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                view.onSearchFail("fail");
                view.hideProgress();
            }
        });
    }
}
