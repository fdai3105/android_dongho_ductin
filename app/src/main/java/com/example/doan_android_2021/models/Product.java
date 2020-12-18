package com.example.doan_android_2021.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("desc")
    @Expose
    public String desc;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("price")
    @Expose
    public int price;
    @SerializedName("vote")
    @Expose
    public Integer vote;
    @SerializedName("brand")
    @Expose
    public Brand brand;
    @SerializedName("category")
    @Expose
    public Category category;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", vote=" + vote +
                ", brand=" + brand +
                ", category=" + category +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}