package com.clothing.models;

import com.google.gson.annotations.SerializedName;

public class CategoryPojo {


    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @SerializedName("cid")
    public String cid;

    @SerializedName("cname")
    public String cname;
}
