package com.clothing.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.clothing.R;
import com.clothing.adapters.UserDashBoardAdapter;
import com.clothing.api.ApiService;
import com.clothing.api.RetroClient;
import com.clothing.models.GetAllProductsPojo;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDashBoardActivity extends AppCompatActivity {
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private DrawerLayout dl;
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;
    String session;
    RecyclerView rv_cust_products;
    List<GetAllProductsPojo> a1;
    UserDashBoardAdapter userDashBoardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dash_board);
        getSupportActionBar().setTitle("Home");
        navigationView();

        rv_cust_products=(RecyclerView)findViewById(R.id.rv_cust_products);

        a1 = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
        rv_cust_products.setLayoutManager(linearLayoutManager);
        serverData();


    }
    private void navigationView(){
        dl = (DrawerLayout)findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);
        dl.addDrawerListener(t);
        t.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
              /*      case R.id.home:
                        Intent home=new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(home);
                        break;*/

                    case R.id.myprofile:
                        Intent myprofile=new Intent(getApplicationContext(), UserProfileActivity.class);
                        startActivity(myprofile);
                        break;

                        case R.id.home:
                        Intent offers_store=new Intent(getApplicationContext(), UserDashBoardActivity.class);
                        startActivity(offers_store);
                        break;

                    case R.id.search_product:
                        Intent search_product=new Intent(getApplicationContext(), SearchProductActivity.class);
                        startActivity(search_product);
                        break;



                        case R.id.my_cart:
                        Intent my_cart=new Intent(getApplicationContext(), DisplaySQLiteDataActivity.class);
                        startActivity(my_cart);
                        break;

                    case R.id.my_order:
                        Intent my_order=new Intent(getApplicationContext(), MyOrdersActivity.class);
                        startActivity(my_order);
                        break;



                    case R.id.logout:
                        Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
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
    public void serverData(){
        progressDialog = new ProgressDialog(UserDashBoardActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<GetAllProductsPojo>> call = service.get_allproducts();
        call.enqueue(new Callback<List<GetAllProductsPojo>>() {
            @Override
            public void onResponse(Call<List<GetAllProductsPojo>> call, Response<List<GetAllProductsPojo>> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(UserDashBoardActivity.this,"No data found",Toast.LENGTH_SHORT).show();
                }else {
                    a1=response.body();
                    userDashBoardAdapter=new UserDashBoardAdapter(UserDashBoardActivity.this,a1);  //attach adapter class with therecyclerview
                    rv_cust_products.setAdapter(userDashBoardAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<GetAllProductsPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(UserDashBoardActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void alertDiolouge(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(UserDashBoardActivity.this);
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