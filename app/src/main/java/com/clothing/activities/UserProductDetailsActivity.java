package com.clothing.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.clothing.R;
import com.clothing.Utils;

public class UserProductDetailsActivity extends AppCompatActivity {
    TextView tv_name,tv_price,tv_des,tv_category,tv_available;
    ImageView image_view;
    Button btn_add_to_cart,btn_go_to_cart;
    SQLiteDatabase sqLiteDatabaseObj;
    String SQLiteDataBaseQueryHolder;
    Spinner spin_quantity;
    int total;
    SharedPreferences sharedPreferences;
    String user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_product_details);

        getSupportActionBar().setTitle("Product Details");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        user_name = sharedPreferences.getString("user_uname", "def-val");

        sqLiteHelper = new SQLiteHelper(this);


        tv_name=(TextView)findViewById(R.id.tv_name);
        tv_price=(TextView)findViewById(R.id.tv_price);
        tv_des=(TextView)findViewById(R.id.tv_des);
        tv_category=(TextView)findViewById(R.id.tv_category);
        tv_available=(TextView)findViewById(R.id.tv_available);
        btn_add_to_cart=(Button)findViewById(R.id.btn_add_to_cart);
        btn_go_to_cart=(Button)findViewById(R.id.btn_go_to_cart);

        spin_quantity=(Spinner)findViewById(R.id.spin_quantity);

        btn_go_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProductDetailsActivity.this, DisplaySQLiteDataActivity.class);
                startActivity(intent);

            }
        });

        image_view=(ImageView)findViewById(R.id.image_view);

        Glide.with(UserProductDetailsActivity.this).load(getIntent().getStringExtra("image")).into(image_view);

        tv_name.setText(getIntent().getStringExtra("name"));
        tv_price.setText(getIntent().getStringExtra("price"));
        tv_category.setText(getIntent().getStringExtra("category"));
        tv_available.setText("Available Count :"+getIntent().getStringExtra("available_count"));
        tv_des.setText("Description  :"+getIntent().getStringExtra("description"));

        btn_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDataBaseBuild();
                SQLiteTableBuild();
                int count=isExsitingProduct();
                if(count==0){
                    InsertDataIntoSQLiteDatabase();
                }
                else {
                    Toast.makeText(UserProductDetailsActivity.this, "This Product is Already added to the cart", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void SQLiteDataBaseBuild(){

        //sqLiteDatabaseObj = openOrCreateDatabase("AndroidJSonDataBase", Context.MODE_PRIVATE, null);
        sqLiteDatabaseObj = openOrCreateDatabase("FashionClothing", Context.MODE_PRIVATE, null);

    }
    public void SQLiteTableBuild(){

        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS FashionClothingTable(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR,price VARCHAR,category VARCHAR,description VARCHAR,photo VARCHAR,total_price VARCHAR,quantity VARCHAR,username VARCHAR,productid VARCHAR);");

    }


    public void InsertDataIntoSQLiteDatabase(){
        total = Integer.parseInt(getIntent().getStringExtra("price"))*Integer.parseInt(spin_quantity.getSelectedItem().toString());

            SQLiteDataBaseQueryHolder = "INSERT INTO FashionClothingTable (name,price,category,description,photo,total_price,quantity,username,productid) VALUES('"+tv_name.getText().toString()+"', '"+tv_price.getText().toString()+"', '"+tv_category.getText().toString()+"', '"+tv_des.getText().toString()+"', '"+getIntent().getStringExtra("image")+"','"+total+""+"','"+spin_quantity.getSelectedItem().toString()+"','"+user_name+"', '"+getIntent().getStringExtra("pid")+"');";

            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);

            Toast.makeText(UserProductDetailsActivity.this,"Item Added Successfully", Toast.LENGTH_LONG).show();

    }
    SQLiteDatabase sqLiteDatabase;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;
    private  int isExsitingProduct(){
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+SQLiteHelper.TABLE_NAME+" where productid ='"+getIntent().getStringExtra("pid")+"' and username='"+user_name+"'", null);
        //Toast.makeText(this, ""+cursor.getCount(), Toast.LENGTH_SHORT).show();
        return cursor.getCount();
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

}