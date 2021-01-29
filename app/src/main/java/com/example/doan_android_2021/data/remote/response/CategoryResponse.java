package com.example.doan_android_2021.data.remote.response;

import com.example.doan_android_2021.models.Category;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;

public class CategoryResponse {
    @SerializedName("data")
    public List<Category> category;
}
