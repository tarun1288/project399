package com.clothing.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.clothing.R;
import com.clothing.adapters.AdvancedSearchAdapter;
import com.clothing.api.ApiService;
import com.clothing.api.RetroClient;
import com.clothing.models.GetAllProductsPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdvancedSearchActivity extends AppCompatActivity {
    TextView tv_price_range,tv_price;
    Button btn_submit;
    ProgressDialog progressDialog;
    List<GetAllProductsPojo> a1;
    RecyclerView rv_search_product;
    EditText et_name;
    AdvancedSearchAdapter advancedSearchAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_search);

        getSupportActionBar().setTitle("Advanced Search");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rv_search_product=(RecyclerView)findViewById(R.id.rv_search_product);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
        rv_search_product.setLayoutManager(linearLayoutManager);

        serverData();



    }
    public void serverData(){
        progressDialog = new ProgressDialog(AdvancedSearchActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<GetAllProductsPojo>> call = service.advenced_search(getIntent().getStringExtra("price"),getIntent().getStringExtra("pname"));
        call.enqueue(new Callback<List<GetAllProductsPojo>>() {
            @Override
            public void onResponse(Call<List<GetAllProductsPojo>> call, Response<List<GetAllProductsPojo>> response) {
                Toast.makeText(AdvancedSearchActivity.this, ""+response.body().size(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(AdvancedSearchActivity.this,"No data found",Toast.LENGTH_SHORT).show();
                }else {
                    a1=response.body();
                    advancedSearchAdapter=new AdvancedSearchAdapter(AdvancedSearchActivity.this,a1);  //attach adapter class with therecyclerview
                    rv_search_product.setAdapter(advancedSearchAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<GetAllProductsPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(AdvancedSearchActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
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