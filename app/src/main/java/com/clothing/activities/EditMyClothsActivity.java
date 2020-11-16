package com.clothing.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.clothing.R;

public class EditMyClothsActivity extends AppCompatActivity {
    EditText et_name,et_price,et_category,et_description;
    Button btn_update;
    ImageView img_product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_cloths);

        getSupportActionBar().setTitle("Edit Product");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        et_name=(EditText)findViewById(R.id.et_name);
        et_price=(EditText)findViewById(R.id.et_price);
        et_category=(EditText)findViewById(R.id.et_category);
        et_description=(EditText)findViewById(R.id.et_description);

        img_product=(ImageView)findViewById(R.id.img_product);

        Glide.with(EditMyClothsActivity.this).load(getIntent().getStringExtra("image")).into(img_product);
        et_name.setText(getIntent().getStringExtra("name"));
        et_price.setText(getIntent().getStringExtra("price"));
        et_category.setText(getIntent().getStringExtra("category"));
        et_description.setText(getIntent().getStringExtra("description"));

        btn_update=(Button)findViewById(R.id.btn_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EditMyClothsActivity.this, "Updated", Toast.LENGTH_SHORT).show();
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