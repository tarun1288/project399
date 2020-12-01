package com.clothing.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.clothing.R;
import com.clothing.api.ApiService;
import com.clothing.api.RetroClient;
import com.clothing.models.MyOrderStatusPojo;
import com.clothing.models.ResponseData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerOrderStatusActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    List<MyOrderStatusPojo> a1;
    RecyclerView RV_MY_ORDERS;
    SellerOrderStatusAdapter sellerOrderStatusAdapter;
    Spinner spin_update_status;
    Button btn_updatestatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_order_status);

        getSupportActionBar().setTitle("My Orders");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spin_update_status=(Spinner)findViewById(R.id.spin_update_status);

        RV_MY_ORDERS=(RecyclerView)findViewById(R.id.RV_MY_ORDERS);

        btn_updatestatus=(Button)findViewById(R.id.btn_updatestatus);
        btn_updatestatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Updatestatus(getIntent().getStringExtra("order_ID"),spin_update_status.getSelectedItem().toString());
                Toast.makeText(SellerOrderStatusActivity.this, ""+getIntent().getStringExtra("order_ID"), Toast.LENGTH_SHORT).show();
            }
        });

        a1 = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
        RV_MY_ORDERS.setLayoutManager(linearLayoutManager);
        serverData();



    }
    public void serverData(){
        progressDialog = new ProgressDialog(SellerOrderStatusActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<MyOrderStatusPojo>> call = service.myorderstatus(getIntent().getStringExtra("order_ID"));
        call.enqueue(new Callback<List<MyOrderStatusPojo>>() {
            @Override
            public void onResponse(Call<List<MyOrderStatusPojo>> call, Response<List<MyOrderStatusPojo>> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(SellerOrderStatusActivity.this,"No data found", Toast.LENGTH_SHORT).show();
                }else {
                    a1=response.body();
                    sellerOrderStatusAdapter=new SellerOrderStatusAdapter(SellerOrderStatusActivity.this,a1);  //attach adapter class with therecyclerview
                    RV_MY_ORDERS.setAdapter(sellerOrderStatusAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<MyOrderStatusPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(SellerOrderStatusActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void Updatestatus(String id,String status) {
        progressDialog = new ProgressDialog(SellerOrderStatusActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.updatestatus(id,status);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                progressDialog.dismiss();
                if (response.body() == null) {
                    Toast.makeText(SellerOrderStatusActivity.this, "Server issue", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(SellerOrderStatusActivity.this, SellerOrdersActivity.class));
                    Toast.makeText(SellerOrderStatusActivity.this, "Product Status Updated successfully", Toast.LENGTH_SHORT).show();
                    finish();

                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(SellerOrderStatusActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
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