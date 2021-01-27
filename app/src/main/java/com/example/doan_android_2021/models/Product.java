package com.example.doan_android_2021.models;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("data")
    @Expose
    private ArrayList<ProductDatum> data = null;
    @SerializedName("links")
    @Expose
    private Links links;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public ArrayList<ProductDatum> getData() {
        return data;
    }

    public void setData(ArrayList<ProductDatum> data) {
        this.data = data;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    @Override
    public String toString() {
        return "Product{" +
                "data=" + data +
                ", links=" + links +
                ", meta=" + meta +
                '}';
    }
}