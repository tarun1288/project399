package com.clothing.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.clothing.R;
import com.clothing.adapters.AdminAllClothessAdapter;
import com.clothing.api.ApiService;
import com.clothing.api.RetroClient;
import com.clothing.models.GetAllProductsPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerAllClothsActivity extends AppCompatActivity {
    RecyclerView all_cloths;
    List<GetAllProductsPojo> a1;
    AdminAllClothessAdapter adminAllClothessAdapter;
    Button btn_add_product;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_all_cloths);

        getSupportActionBar().setTitle("All Cloths");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn_add_product = (Button) findViewById(R.id.btn_add_product);
        btn_add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SellerAllClothsActivity.this,AddProductActivity.class));
            }
        });

        all_cloths=(RecyclerView)findViewById(R.id.all_cloths);

        a1 = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
        all_cloths.setLayoutManager(linearLayoutManager);

        /*adminAllClothessAdapter=new AdminAllClothessAdapter(SellerAllClothsActivity.this,a1);  //attach adapter class with therecyclerview
        all_cloths.setAdapter(adminAllClothessAdapter);*/
        serverData();

    }
    public void serverData(){
        progressDialog = new ProgressDialog(SellerAllClothsActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<GetAllProductsPojo>> call = service.get_allproducts();
        call.enqueue(new Callback<List<GetAllProductsPojo>>() {
            @Override
            public void onResponse(Call<List<GetAllProductsPojo>> call, Response<List<GetAllProductsPojo>> response) {
                Toast.makeText(SellerAllClothsActivity.this, ""+response.body().size(), Toast.LENGTH_SHORT).show();

                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(SellerAllClothsActivity.this,"No data found",Toast.LENGTH_SHORT).show();
                }else {
                    a1 = response.body();
                    adminAllClothessAdapter = new AdminAllClothessAdapter(SellerAllClothsActivity.this, a1);  //attach adapter class with therecyclerview
                    all_cloths.setAdapter(adminAllClothessAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<GetAllProductsPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(SellerAllClothsActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
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