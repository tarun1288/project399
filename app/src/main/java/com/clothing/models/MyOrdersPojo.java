package com.clothing.models;

import com.google.gson.annotations.SerializedName;

public class MyOrdersPojo {


    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @SerializedName("order_id")
    public String order_id;

    @SerializedName("uname")
    public String uname;

    @SerializedName("order_date")
    public String order_date;

    @SerializedName("status")
    public String status;
}
