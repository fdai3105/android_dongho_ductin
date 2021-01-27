package com.example.doan_android_2021.models;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDatum {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("material")
    @Expose
    private Material material;
    @SerializedName("style")
    @Expose
    private Style style;
    @SerializedName("waterproof")
    @Expose
    private Integer waterproof;
    @SerializedName("band_material")
    @Expose
    private BandMaterial bandMaterial;
    @SerializedName("warranty")
    @Expose
    private Integer warranty;
    @SerializedName("gender")
    @Expose
    private Gender gender;
    @SerializedName("discount")
    @Expose
    private Integer discount;
    @SerializedName("brand")
    @Expose
    private Brand brand;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public Integer getWaterproof() {
        return waterproof;
    }

    public void setWaterproof(Integer waterproof) {
        this.waterproof = waterproof;
    }

    public BandMaterial getBandMaterial() {
        return bandMaterial;
    }

    public void setBandMaterial(BandMaterial bandMaterial) {
        this.bandMaterial = bandMaterial;
    }

    public Integer getWarranty() {
        return warranty;
    }

    public void setWarranty(Integer warranty) {
        this.warranty = warranty;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "ProductDatum{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", size=" + size +
                ", price=" + price +
                ", material=" + material +
                ", style=" + style +
                ", waterproof=" + waterproof +
                ", bandMaterial=" + bandMaterial +
                ", warranty=" + warranty +
                ", gender=" + gender +
                ", discount=" + discount +
                ", brand=" + brand +
                ", category=" + category +
                ", images=" + images +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}