package com.clothing.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.clothing.R;
import com.clothing.Utils;
import com.clothing.api.ApiService;
import com.clothing.api.RetroClient;
import com.clothing.models.ResponseData;

import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisplaySQLiteDataActivity extends AppCompatActivity {
    SQLiteHelper sqLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    ListAdapter listAdapter ;
    ListView LISTVIEW;
    String SQLiteDataBaseQueryHolder;
    SQLiteDatabase sqLiteDatabaseObj;
    ProgressDialog pd;

    ArrayList<String> ID_Array;
    ArrayList<String> NAME_Array;
    ArrayList<String> Price_Array,Total_Price_Array;
    ArrayList<String> Category_Array;
    ArrayList<String> Description_Array;
    ArrayList<String> Image_Array;
    ArrayList<String> Product_Quantity;
    ArrayList<String> User_name;
    ArrayList<String> Product_ID;
    TextView tv_total_price;

    ArrayList<String> ListViewClickItemArray = new ArrayList<String>();
    String TempHolder ;
    int total_price = 0;
    SharedPreferences sharedPreferences;
    String uname;
    Button btn_check_out;
    int oid;
    String OrderID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_s_q_lite_data);

        getSupportActionBar().setTitle(" Product Details");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        uname = sharedPreferences.getString("user_uname", "def-val");
        LISTVIEW = (ListView) findViewById(R.id.listView1);
        tv_total_price=(TextView)findViewById(R.id.tv_total_price);

        btn_check_out=(Button)findViewById(R.id.btn_check_out);
        buttoncheckout();

        ID_Array = new ArrayList<String>();
        NAME_Array = new ArrayList<String>();
        Price_Array = new ArrayList<String>();
        Total_Price_Array = new ArrayList<String>();
        Category_Array = new ArrayList<String>();
        Description_Array = new ArrayList<String>();
        Image_Array = new ArrayList<String>();
        Product_Quantity = new ArrayList<String>();
        User_name = new ArrayList<String>();
        Product_ID = new ArrayList<String>();

        sqLiteHelper = new SQLiteHelper(this);

        Random r = new Random();
        oid= r.nextInt(100 - 5) + 5;
        OrderID="FS"+oid;


    }

    @Override
    protected void onResume() {

        ShowSQLiteDBdata() ;

        super.onResume();
    }

    private void ShowSQLiteDBdata() {

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+SQLiteHelper.TABLE_NAME+" where username='"+uname+"'", null);

        ID_Array.clear();
        NAME_Array.clear();
        Price_Array.clear();
        Category_Array.clear();
        Description_Array.clear();
        Image_Array.clear();
        Total_Price_Array.clear();
        Product_Quantity.clear();
        User_name.clear();
        Product_ID.clear();

        if (cursor.moveToFirst()) {
            do {

                ID_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_ID)));
                NAME_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_1_Name)));
                Price_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_2_Price)));
                Category_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_3_Category)));
                Description_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_4_Description)));
                Image_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_5_Photo)));
                Total_Price_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_6_Total_price)));
                Product_Quantity.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_7_Quantity)));
                User_name.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_8_Username)));
                Product_ID.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_9_ProductID)));

                //Inserting Column ID into Array to Use at ListView Click Listener Method.
                ListViewClickItemArray.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_ID)));


            }
            while (cursor.moveToNext());
        }

        if(SQLiteHelper.DATABASE_NAME.isEmpty()){
            Toast.makeText(this, "Your Cart is Empty", Toast.LENGTH_SHORT).show();
        }
        else {
            listAdapter = new com.clothing.adapters.ListAdapter(DisplaySQLiteDataActivity.this, ID_Array, NAME_Array, Price_Array, Category_Array, Description_Array, Image_Array, Total_Price_Array, Product_Quantity, User_name);
            LISTVIEW.setAdapter(listAdapter);
        }

        cursor.close();

        for(String price: Total_Price_Array){
            total_price = total_price+Integer.parseInt(price);
        }
        tv_total_price.setText("Total Price is :"+total_price);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void buttoncheckout(){
        btn_check_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitData();
            }
        });
    }

    private void submitData(){
        for(int i=0;i<NAME_Array.size();i++){

            String name=NAME_Array.get(i);
            String priceamount=Price_Array.get(i);
            String description =Description_Array.get(i);
            String quantity =Product_Quantity.get(i);
            String photo =Image_Array.get(i);
            String category =Category_Array.get(i);
            String total_price =Total_Price_Array.get(i);
            String uname =User_name.get(i);
            String pid =Product_ID.get(i);

            //Toast.makeText(this, ""+priceamount, Toast.LENGTH_SHORT).show();
            //surverSubmit(name,price,pid);



            surverSubmit(name,priceamount,description,quantity,photo,category,total_price,uname,pid);
        }
        deletedata();

    }


    private void surverSubmit(String name,String priceamount,String description,String quantity,String photo,String category,String totalprice,String uname,String pid){

        ApiService apiService = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = apiService.add_cart(name,priceamount,description,quantity,photo,category,totalprice,uname,pid,OrderID);


        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {


                if (response.body().status.equals("true")) {
                    Toast.makeText(DisplaySQLiteDataActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    startActivity(new Intent(DisplaySQLiteDataActivity.this, ConfirmationPageActivity.class));
                    //finish();

                } else {
                    Toast.makeText(DisplaySQLiteDataActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                Toast.makeText(DisplaySQLiteDataActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private void deletedata(){
        OpenSQLiteDataBase();
        SQLiteDataBaseQueryHolder = "DELETE FROM "+SQLiteHelper.TABLE_NAME+" WHERE username = \'"+uname+"\'";

        sqLiteDatabase.execSQL(SQLiteDataBaseQueryHolder);
        sqLiteDatabase.close();

        finish();


    }

    public void OpenSQLiteDataBase(){

        sqLiteDatabaseObj = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }
    public void deleatesingleproduct(String productid){

        OpenSQLiteDataBase();
        SQLiteDataBaseQueryHolder = "DELETE FROM "+SQLiteHelper.TABLE_NAME+" WHERE id = \'"+productid+"\'";
        sqLiteDatabase.execSQL(SQLiteDataBaseQueryHolder);
        sqLiteDatabase.close();

        Intent refresh = new Intent(this, DisplaySQLiteDataActivity.class);
        startActivity(refresh);//Start the same Activity
        finish();
    }

}