package com.clothing.models;

import com.google.gson.annotations.SerializedName;

public class ProductFeedbackPojo {
    @SerializedName("pid")
    public String pid;

    @SerializedName("name")
    public String name;

    @SerializedName("email")
    public String email;

    @SerializedName("rating")
    public String rating;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @SerializedName("reason")
    public String reason;


}
