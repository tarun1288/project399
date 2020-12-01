package com.clothing.models;

import com.google.gson.annotations.SerializedName;

public class GetAllProductsPojo {


    @SerializedName("cid")
    public String cid;

    @SerializedName("description")
    public String description;

    @SerializedName("photo")
    public String photo;

    @SerializedName("pid")
    public String pid;

    @SerializedName("price")
    public String price;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSeller_email() {
        return seller_email;
    }

    public void setSeller_email(String seller_email) {
        this.seller_email = seller_email;
    }

    @SerializedName("productname")
    public String productname;

    @SerializedName("quantity")
    public String quantity;

    @SerializedName("status")
    public String status;

    @SerializedName("email")
    public String email;


    @SerializedName("seller_email")
    public String seller_email;

}
