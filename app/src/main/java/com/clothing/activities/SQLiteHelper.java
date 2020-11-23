package com.clothing.activities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    static String DATABASE_NAME="FashionClothing";

    public static final String TABLE_NAME="FashionClothingTable";

    public static final String Table_Column_ID="id";
    public static final String Table_Column_1_Name="name";
    public static final String Table_Column_2_Price="price";
    public static final String Table_Column_3_Category="category";
    public static final String Table_Column_4_Description="description";
    public static final String Table_Column_5_Photo="photo";
    public static final String Table_Column_6_Total_price="total_price";
    public static final String Table_Column_7_Quantity="quantity";
    public static final String Table_Column_8_Username="username";
    public static final String Table_Column_9_ProductID="productid";

    public SQLiteHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+Table_Column_ID+" INTEGER PRIMARY KEY, "+Table_Column_1_Name+" VARCHAR, "+Table_Column_2_Price+" VARCHAR," +
                " "+Table_Column_3_Category+" VARCHAR, "+Table_Column_4_Description+" VARCHAR, "+Table_Column_5_Photo+" VARCHAR, "+Table_Column_6_Total_price+" VARCHAR, "+Table_Column_7_Quantity+" VARCHAR, "+Table_Column_8_Username+" VARCHAR,"+Table_Column_9_ProductID+" VARCHAR)";
        database.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

}