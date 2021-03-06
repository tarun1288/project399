package com.clothing.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.clothing.R;
import com.clothing.Utils;
import com.clothing.adapters.MyOrdersAdapter;
import com.clothing.api.ApiService;
import com.clothing.api.RetroClient;
import com.clothing.models.MyOrdersPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyOrdersActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    List<MyOrdersPojo> a1;
    MyOrdersAdapter myOrdersAdapter;
    RecyclerView RV_MY_ORDERS;
    SharedPreferences sharedPreferences;
    String session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);


        getSupportActionBar().setTitle("My Orders");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        session = sharedPreferences.getString("user_uname", "def-val");
        RV_MY_ORDERS=(RecyclerView)findViewById(R.id.RV_MY_ORDERS);

        a1 = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
        RV_MY_ORDERS.setLayoutManager(linearLayoutManager);
        serverData();

    }
    public void serverData(){
        progressDialog = new ProgressDialog(MyOrdersActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<MyOrdersPojo>> call = service.getmyorders(session);
        call.enqueue(new Callback<List<MyOrdersPojo>>() {
            @Override
            public void onResponse(Call<List<MyOrdersPojo>> call, Response<List<MyOrdersPojo>> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(MyOrdersActivity.this,"No data found", Toast.LENGTH_SHORT).show();
                }else {
                    a1=response.body();
                    myOrdersAdapter=new MyOrdersAdapter(MyOrdersActivity.this,a1);  //attach adapter class with therecyclerview
                    RV_MY_ORDERS.setAdapter(myOrdersAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<MyOrdersPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MyOrdersActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
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