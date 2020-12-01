package com.clothing.models;

public class FavListModel {



    public String getID_Array() {
        return ID_Array;
    }

    public void setID_Array(String ID_Array) {
        this.ID_Array = ID_Array;
    }

    public String getNAME_Array() {
        return NAME_Array;
    }

    public void setNAME_Array(String NAME_Array) {
        this.NAME_Array = NAME_Array;
    }

    public String getCategory_Array() {
        return Category_Array;
    }

    public void setCategory_Array(String category_Array) {
        Category_Array = category_Array;
    }

    public String getPrice_Array() {
        return Price_Array;
    }

    public void setPrice_Array(String price_Array) {
        Price_Array = price_Array;
    }

    public String getDescription_Array() {
        return Description_Array;
    }

    public void setDescription_Array(String description_Array) {
        Description_Array = description_Array;
    }

    public String getImage_Array() {
        return Image_Array;
    }

    public void setImage_Array(String image_Array) {
        Image_Array = image_Array;
    }

    public String getUser_name() {
        return User_name;
    }

    public void setUser_name(String user_name) {
        User_name = user_name;
    }

    public String getProduct_ID() {
        return Product_ID;
    }

    public void setProduct_ID(String product_ID) {
        Product_ID = product_ID;
    }

    String ID_Array;
    String NAME_Array;
    String Category_Array;
    String Price_Array;
    String Description_Array;
    String Image_Array;
    String User_name;

    public FavListModel(String ID_Array, String NAME_Array, String category_Array, String price_Array, String description_Array, String image_Array, String user_name, String product_ID) {
        this.ID_Array = ID_Array;
        this.NAME_Array = NAME_Array;
        Category_Array = category_Array;
        Price_Array = price_Array;
        Description_Array = description_Array;
        Image_Array = image_Array;
        User_name = user_name;
        Product_ID = product_ID;
    }

    String Product_ID;

}
