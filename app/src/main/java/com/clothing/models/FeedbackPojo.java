package com.clothing.models;

import com.google.gson.annotations.SerializedName;

public class FeedbackPojo {
    @SerializedName("name")
    public String name;

    @SerializedName("email")
    public String email;

    @SerializedName("reason")
    public String reason;

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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public FeedbackPojo(String name, String email, String reason) {
        this.name = name;
        this.email = email;
        this.reason = reason;
    }
}
