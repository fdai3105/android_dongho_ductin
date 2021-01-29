package com.example.doan_android_2021.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cart {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("product")
    @Expose
    private ProductDatum product;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProductDatum getProduct() {
        return product;
    }

    public void setProduct(ProductDatum product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}