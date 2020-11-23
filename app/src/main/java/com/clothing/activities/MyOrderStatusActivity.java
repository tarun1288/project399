package com.clothing.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.clothing.R;
import com.clothing.adapters.MyOrderStatusAdapter;
import com.clothing.api.ApiService;
import com.clothing.api.RetroClient;
import com.clothing.models.MyOrderStatusPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyOrderStatusActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    List<MyOrderStatusPojo> a1;
    RecyclerView RV_MY_ORDERS;
    MyOrderStatusAdapter myOrderStatusAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order_status);

        getSupportActionBar().setTitle("My Orders");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RV_MY_ORDERS=(RecyclerView)findViewById(R.id.RV_MY_ORDERS);

        a1 = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
        RV_MY_ORDERS.setLayoutManager(linearLayoutManager);
        serverData();

    }
    public void serverData(){
        progressDialog = new ProgressDialog(MyOrderStatusActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<MyOrderStatusPojo>> call = service.myorderstatus(getIntent().getStringExtra("order_ID"));
        call.enqueue(new Callback<List<MyOrderStatusPojo>>() {
            @Override
            public void onResponse(Call<List<MyOrderStatusPojo>> call, Response<List<MyOrderStatusPojo>> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(MyOrderStatusActivity.this,"No data found", Toast.LENGTH_SHORT).show();
                }else {
                    a1=response.body();
                    myOrderStatusAdapter=new MyOrderStatusAdapter(MyOrderStatusActivity.this,a1);  //attach adapter class with therecyclerview
                    RV_MY_ORDERS.setAdapter(myOrderStatusAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<MyOrderStatusPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MyOrderStatusActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
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