package com.clothing.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.clothing.R;
import com.clothing.adapters.SearchAdapter;
import com.clothing.api.RetroClient;
import com.clothing.models.GetAllProductsPojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchProductActivity extends AppCompatActivity {
    RecyclerView rv_search_product;
    List<GetAllProductsPojo> a1;
    ProgressDialog progressDialog;
    EditText et_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);


        getSupportActionBar().setTitle("Search Product");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rv_search_product=(RecyclerView)findViewById(R.id.rv_search_product);

        a1 = new ArrayList<>();
        et_search=(EditText)findViewById(R.id.et_search);
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = et_search.getText().toString().toLowerCase(Locale.getDefault());
                searchAdapter.productFilter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
        rv_search_product.setLayoutManager(linearLayoutManager);
        serverData();
    }

    SearchAdapter searchAdapter;
    public void serverData(){
        progressDialog = new ProgressDialog(SearchProductActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<GetAllProductsPojo>> call = service.get_allproducts();
        call.enqueue(new Callback<List<GetAllProductsPojo>>() {
            @Override
            public void onResponse(Call<List<GetAllProductsPojo>> call, Response<List<GetAllProductsPojo>> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(SearchProductActivity.this,"No data found",Toast.LENGTH_SHORT).show();
                }else {
                    a1=response.body();
                    searchAdapter=new SearchAdapter(SearchProductActivity.this,a1);  //attach adapter class with therecyclerview
                    rv_search_product.setAdapter(searchAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<GetAllProductsPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(SearchProductActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
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