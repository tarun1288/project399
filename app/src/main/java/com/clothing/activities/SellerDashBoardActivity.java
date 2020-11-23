package com.clothing.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.clothing.R;
import com.clothing.adapters.AdminAllClothessAdapter;
import com.clothing.api.ApiService;
import com.clothing.api.RetroClient;
import com.clothing.models.GetAllProductsPojo;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerDashBoardActivity extends AppCompatActivity {
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private DrawerLayout dl;
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;
    String session;
    RecyclerView all_cloths;
    List<GetAllProductsPojo> a1;
    AdminAllClothessAdapter adminAllClothessAdapter;
    Button btn_add_product;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_dash_board);
        getSupportActionBar().setTitle("Seller Dashboard");
        navigationView();

        all_cloths = (RecyclerView) findViewById(R.id.all_cloths);
        btn_add_product = (Button) findViewById(R.id.btn_add_product);
        btn_add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SellerDashBoardActivity.this,AddProductActivity.class));
            }
        });

        a1 = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        all_cloths.setLayoutManager(linearLayoutManager);

        getAllProducts();


    }

    public void getAllProducts(){
        pd= new ProgressDialog(SellerDashBoardActivity.this);
        pd.setTitle("Loading....");
        pd.show();
        ApiService apiService = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<GetAllProductsPojo>> call = apiService.get_allproducts();
        call.enqueue(new Callback<List<GetAllProductsPojo>>() {
            @Override
            public void onResponse(Call<List<GetAllProductsPojo>> call, Response<List<GetAllProductsPojo>> response) {
                Toast.makeText(SellerDashBoardActivity.this, ""+response.body().size(), Toast.LENGTH_SHORT).show();
                pd.dismiss();
                if (response.body()==null) {
                    Toast.makeText(SellerDashBoardActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                }
                else{
                    a1 = response.body();
                    adminAllClothessAdapter = new AdminAllClothessAdapter(SellerDashBoardActivity.this, a1);  //attach adapter class with therecyclerview
                    all_cloths.setAdapter(adminAllClothessAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<GetAllProductsPojo>> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(SellerDashBoardActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void navigationView() {
        dl = (DrawerLayout) findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);
        dl.addDrawerListener(t);
        t.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nv = (NavigationView) findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.home:
                        Intent home = new Intent(getApplicationContext(), SellerAllClothsActivity.class);
                        startActivity(home);
                        break;

                    case R.id.myprofile:
                        Intent myprofile = new Intent(getApplicationContext(), SellerProfileActivity.class);
                        startActivity(myprofile);
                        break;

                    case R.id.add_cloths:
                        Intent add_cloths = new Intent(getApplicationContext(), AddProductActivity.class);
                        startActivity(add_cloths);
                        break;


                    case R.id.logout:
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        finish();
                        break;

                    default:
                        return true;
                }
                dl.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (dl.isDrawerOpen(GravityCompat.START)) {
            dl.closeDrawer(GravityCompat.START);
        } else {
            // super.onBackPressed();
            alertDiolouge();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (dl.isDrawerOpen(GravityCompat.START)) {
            dl.closeDrawer(GravityCompat.START);
        } else {
            dl.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
    public void alertDiolouge() {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(SellerDashBoardActivity.this);
        builder1.setTitle("Alert !!!");
        builder1.setMessage("Do you want to close the Application.");  //message we want to show the end user
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel(); //cancle the alert dialog box
                        finish();//finish the process
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
}
