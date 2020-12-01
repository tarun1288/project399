package com.clothing.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.clothing.R;
import com.clothing.Utils;
import com.clothing.api.ApiService;
import com.clothing.api.RetroClient;
import com.clothing.models.GetAllProductsPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteProductsActiviy extends AppCompatActivity {
    ListView listView1;
    SQLiteHelper sqLiteHelper;
    FavProductAdapter favProductAdapter ;
    SharedPreferences sharedPreferences;
    String uname;
    List<GetAllProductsPojo> a1;
    RecyclerView rv_favproduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_products_activiy);

        getSupportActionBar().setTitle("Favorite List");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        uname = sharedPreferences.getString("user_uname", "def-val");

        rv_favproduct=(RecyclerView)findViewById(R.id.rv_favproduct);

        a1 = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rv_favproduct.setLayoutManager(linearLayoutManager);

        serverData();

    }

    ProgressDialog progressDialog;
    public void serverData(){
        progressDialog = new ProgressDialog(FavoriteProductsActiviy.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<GetAllProductsPojo>> call = service.getfavlist(uname);
        call.enqueue(new Callback<List<GetAllProductsPojo>>() {
            @Override
            public void onResponse(Call<List<GetAllProductsPojo>> call, Response<List<GetAllProductsPojo>> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(FavoriteProductsActiviy.this,"No data found",Toast.LENGTH_SHORT).show();
                }else {
                    a1=response.body();
                    favProductAdapter=new FavProductAdapter(FavoriteProductsActiviy.this,a1);  //attach adapter class with therecyclerview
                    rv_favproduct.setAdapter(favProductAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<GetAllProductsPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(FavoriteProductsActiviy.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
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