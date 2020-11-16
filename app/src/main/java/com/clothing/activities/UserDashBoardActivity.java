package com.clothing.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.clothing.R;
import com.clothing.adapters.CustomersCartAdapter;
import com.clothing.adapters.OffersStoreAdapter;
import com.clothing.models.AllClothsPojo;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class UserDashBoardActivity extends AppCompatActivity {
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private DrawerLayout dl;
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;
    String session;
    RecyclerView rv_cust_products;
    List<AllClothsPojo> a1;
    OffersStoreAdapter offersStoreAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dash_board);
        getSupportActionBar().setTitle("User Dashboard");
        navigationView();

        rv_cust_products=(RecyclerView)findViewById(R.id.rv_cust_products);

        a1 = new ArrayList<>();
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/12529532/2020/10/13/ba5950c3-e148-43a7-a295-25dfc687b1931602590996414-United-Colors-of-Benetton-Men-Purple-Slim-Fit-Solid-Casual-S-6.jpg","United Colors of Benetton","Rs. 1349","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/6832572/2018/8/24/aa5fad0c-2e08-4fcb-9e36-4979251d22eb1535109119327-WROGN-Men-Red--Navy-Blue-Slim-Fit-Checked-Casual-Shirt-3891535109119109-4.jpg","HIGHLANDER","Rs. 1229","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/11780922/2020/4/3/0fe1f83f-4a92-4a9e-ac95-3559e4b978e61585905004166HIGHLANDERMenWhiteGreySlimFitPrintedCasualShirt1.jpg","WROGN","Rs. 999","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/productimage/2020/3/5/c0dba4a9-674d-425b-9da0-9d446157d1b21583362590845-1.jpg","Reebook","Rs. 1349","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/productimage/2020/2/6/a34cb78a-5ddd-4645-9bd4-33a90c4622f31580943922465-1.jpg","Lives","Rs. 1349","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/2478210/2019/11/8/85bba7bd-b3f3-4ea3-a063-5890340301471573214255868-WROGN-Men-Pink-Slim-Fit-Solid-Casual-Shirt-7431573214250598-1.jpg","PUMA","Rs. 799","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/12529532/2020/10/13/ba5950c3-e148-43a7-a295-25dfc687b1931602590996414-United-Colors-of-Benetton-Men-Purple-Slim-Fit-Solid-Casual-S-6.jpg","Peter England","Rs. 1999","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/6832572/2018/8/24/aa5fad0c-2e08-4fcb-9e36-4979251d22eb1535109119327-WROGN-Men-Red--Navy-Blue-Slim-Fit-Checked-Casual-Shirt-3891535109119109-4.jpg","United Colors of Benetton","Rs. 2499","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/11780922/2020/4/3/0fe1f83f-4a92-4a9e-ac95-3559e4b978e61585905004166HIGHLANDERMenWhiteGreySlimFitPrintedCasualShirt1.jpg","United Colors of Benetton","Rs. 1999","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/productimage/2020/3/5/c0dba4a9-674d-425b-9da0-9d446157d1b21583362590845-1.jpg","United Colors of Benetton","Rs. 1349","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/productimage/2020/2/6/a34cb78a-5ddd-4645-9bd4-33a90c4622f31580943922465-1.jpg","United Colors of Benetton","Rs. 1349","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/2478210/2019/11/8/85bba7bd-b3f3-4ea3-a063-5890340301471573214255868-WROGN-Men-Pink-Slim-Fit-Solid-Casual-Shirt-7431573214250598-1.jpg","United Colors of Benetton","Rs. 1888","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/12529532/2020/10/13/ba5950c3-e148-43a7-a295-25dfc687b1931602590996414-United-Colors-of-Benetton-Men-Purple-Slim-Fit-Solid-Casual-S-6.jpg","United Colors of Benetton","Rs. 1799","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/6832572/2018/8/24/aa5fad0c-2e08-4fcb-9e36-4979251d22eb1535109119327-WROGN-Men-Red--Navy-Blue-Slim-Fit-Checked-Casual-Shirt-3891535109119109-4.jpg","United Colors of Benetton","Rs. 1349","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/11780922/2020/4/3/0fe1f83f-4a92-4a9e-ac95-3559e4b978e61585905004166HIGHLANDERMenWhiteGreySlimFitPrintedCasualShirt1.jpg","United Colors of Benetton","Rs. 1349","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/productimage/2020/3/5/c0dba4a9-674d-425b-9da0-9d446157d1b21583362590845-1.jpg","United Colors of Benetton","Rs. 1349","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/productimage/2020/2/6/a34cb78a-5ddd-4645-9bd4-33a90c4622f31580943922465-1.jpg","United Colors of Benetton","Rs. 1349","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/2478210/2019/11/8/85bba7bd-b3f3-4ea3-a063-5890340301471573214255868-WROGN-Men-Pink-Slim-Fit-Solid-Casual-Shirt-7431573214250598-1.jpg","United Colors of Benetton","Rs. 1349","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","Available")); a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/12529532/2020/10/13/ba5950c3-e148-43a7-a295-25dfc687b1931602590996414-United-Colors-of-Benetton-Men-Purple-Slim-Fit-Solid-Casual-S-6.jpg","United Colors of Benetton","Rs. 1349","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/6832572/2018/8/24/aa5fad0c-2e08-4fcb-9e36-4979251d22eb1535109119327-WROGN-Men-Red--Navy-Blue-Slim-Fit-Checked-Casual-Shirt-3891535109119109-4.jpg","United Colors of Benetton","Rs. 1349","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/11780922/2020/4/3/0fe1f83f-4a92-4a9e-ac95-3559e4b978e61585905004166HIGHLANDERMenWhiteGreySlimFitPrintedCasualShirt1.jpg","United Colors of Benetton","Rs. 1349","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/productimage/2020/3/5/c0dba4a9-674d-425b-9da0-9d446157d1b21583362590845-1.jpg","United Colors of Benetton","Rs. 1349","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/productimage/2020/2/6/a34cb78a-5ddd-4645-9bd4-33a90c4622f31580943922465-1.jpg","United Colors of Benetton","Rs. 1349","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/2478210/2019/11/8/85bba7bd-b3f3-4ea3-a063-5890340301471573214255868-WROGN-Men-Pink-Slim-Fit-Solid-Casual-Shirt-7431573214250598-1.jpg","United Colors of Benetton","Rs. 1349","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
        rv_cust_products.setLayoutManager(linearLayoutManager);

        offersStoreAdapter=new OffersStoreAdapter(UserDashBoardActivity.this,a1);  //attach adapter class with therecyclerview
        rv_cust_products.setAdapter(offersStoreAdapter);
    }
    private void navigationView(){
        dl = (DrawerLayout)findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);
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
                    case R.id.home:
                        Intent home=new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(home);
                        break;

                    case R.id.myprofile:
                        Intent myprofile=new Intent(getApplicationContext(), UserProfileActivity.class);
                        startActivity(myprofile);
                        break;

                        case R.id.offers_store:
                        Intent offers_store=new Intent(getApplicationContext(), OffersStoreActivity.class);
                        startActivity(offers_store);
                        break;

                        case R.id.my_cart:
                        Intent my_cart=new Intent(getApplicationContext(), CustomersCartActivity.class);
                        startActivity(my_cart);
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