package com.clothing.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.clothing.R;
import com.clothing.api.ApiService;
import com.clothing.api.RetroClient;
import com.clothing.models.ResponseData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerClothsDetailsActivity extends AppCompatActivity {
    TextView tv_name,tv_price,tv_des,tv_category;
    ImageView image_view;
    Button btn_edit,btn_delete;

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
        btn_delete=(Button)findViewById(R.id.btn_delete);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SellerClothsDetailsActivity.this, EditMyClothsActivity.class);
                intent.putExtra("image",getIntent().getStringExtra("image"));
                intent.putExtra("name",getIntent().getStringExtra("name"));
                intent.putExtra("price",getIntent().getStringExtra("price"));
                intent.putExtra("category",getIntent().getStringExtra("category"));
                intent.putExtra("description",getIntent().getStringExtra("description"));
                intent.putExtra("quantity",getIntent().getStringExtra("quantity"));
                intent.putExtra("id",getIntent().getStringExtra("id"));
                startActivity(intent);

            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDioluge(getIntent().getStringExtra("id"));
            }
        });
    }
    ProgressDialog progressDialog;
    public void serverData(String id){
        progressDialog = new ProgressDialog(SellerClothsDetailsActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.delete_product(id);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(SellerClothsDetailsActivity.this,"Server issue",Toast.LENGTH_SHORT).show();
                }else {
                    startActivity(new Intent(SellerClothsDetailsActivity.this, SellerDashBoardActivity.class));
                    Toast.makeText(SellerClothsDetailsActivity.this,"Product Deleted successfully",Toast.LENGTH_SHORT).show();
                     finish();

                }
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(SellerClothsDetailsActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void alertDioluge(final String id){

        AlertDialog.Builder builder1 = new AlertDialog.Builder(SellerClothsDetailsActivity.this);
        builder1.setTitle("Alert !!!");
        builder1.setMessage("Do you want to Delete the Player.");  //message we want to show the end user
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int i) {
                        serverData(id);
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
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