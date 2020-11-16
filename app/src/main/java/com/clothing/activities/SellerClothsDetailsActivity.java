package com.clothing.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.clothing.R;

public class SellerClothsDetailsActivity extends AppCompatActivity {
    TextView tv_name,tv_price,tv_des,tv_category;
    ImageView image_view;
    Button btn_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_cloths_details);

        getSupportActionBar().setTitle("Cloths Details");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv_name=(TextView)findViewById(R.id.tv_name);
        tv_price=(TextView)findViewById(R.id.tv_price);
        tv_des=(TextView)findViewById(R.id.tv_des);
        tv_category=(TextView)findViewById(R.id.tv_category);

        image_view=(ImageView)findViewById(R.id.image_view);

        Glide.with(SellerClothsDetailsActivity.this).load(getIntent().getStringExtra("image")).into(image_view);

        tv_name.setText(getIntent().getStringExtra("name"));
        tv_price.setText(getIntent().getStringExtra("price"));
        tv_category.setText(getIntent().getStringExtra("category"));
        tv_des.setText(getIntent().getStringExtra("description"));

        btn_edit=(Button)findViewById(R.id.btn_edit);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SellerClothsDetailsActivity.this, EditMyClothsActivity.class);
                intent.putExtra("image",getIntent().getStringExtra("image"));
                intent.putExtra("name",getIntent().getStringExtra("name"));
                intent.putExtra("price",getIntent().getStringExtra("price"));
                intent.putExtra("category",getIntent().getStringExtra("category"));
                intent.putExtra("description",getIntent().getStringExtra("description"));
                startActivity(intent);

            }
        });


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