package com.clothing.models;

import com.google.gson.annotations.SerializedName;

public class MyOrderStatusPojo {

    @SerializedName("id")
    public String id;

    @SerializedName("description")
    public String description;

    @SerializedName("name")
    public String name;

    @SerializedName("order_date")
    public String order_date;

    @SerializedName("order_id")
    public String order_id;

    @SerializedName("photo")
    public String photo;

    @SerializedName("pid")
    public String pid;


    @SerializedName("price")
    public String price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
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

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    @SerializedName("quantity")
    public String quantity;

    @SerializedName("status")
    public String status;

    @SerializedName("total_price")
    public String total_price;

    @SerializedName("uname")
    public String uname;

}
