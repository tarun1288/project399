package com.clothing.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.clothing.R;
import com.clothing.models.AllClothsPojo;

import java.util.ArrayList;
import java.util.List;

public class CustomersCartActivity extends AppCompatActivity {

    RecyclerView rv_customer_cart;
    List<AllClothsPojo> a1;
    CustomersCartAdapter customersCartAdapter;
    Button btn_check_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers);

        getSupportActionBar().setTitle("Customer's Cart");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rv_customer_cart=(RecyclerView)findViewById(R.id.rv_customer_cart);

        a1 = new ArrayList<>();
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/12529532/2020/10/13/ba5950c3-e148-43a7-a295-25dfc687b1931602590996414-United-Colors-of-Benetton-Men-Purple-Slim-Fit-Solid-Casual-S-6.jpg","United Colors of Benetton","Rs. 1349","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/6832572/2018/8/24/aa5fad0c-2e08-4fcb-9e36-4979251d22eb1535109119327-WROGN-Men-Red--Navy-Blue-Slim-Fit-Checked-Casual-Shirt-3891535109119109-4.jpg","HIGHLANDER","Rs. 1229","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
        rv_customer_cart.setLayoutManager(linearLayoutManager);

        customersCartAdapter=new CustomersCartAdapter(CustomersCartActivity.this,a1);  //attach adapter class with therecyclerview
        rv_customer_cart.setAdapter(customersCartAdapter);

        btn_check_out=(Button)findViewById(R.id.btn_check_out);
        btn_check_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomersCartActivity.this,ShippingAddressActivity.class));
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