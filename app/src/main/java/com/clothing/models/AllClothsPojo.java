package com.clothing.models;

import com.google.gson.annotations.SerializedName;

public class AllClothsPojo {


    @SerializedName("image")
    private String image;

    @SerializedName("name")
    private String name;

    @SerializedName("price")
    private String price;

    @SerializedName("category")
    private String category;

    @SerializedName("description")
    private String description;

    @SerializedName("status")
    private String status;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public AllClothsPojo(String image, String name, String price, String category, String description, String status) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
        this.status = status;
    }



}
