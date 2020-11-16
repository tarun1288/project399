package com.clothing.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.clothing.R;
import com.clothing.adapters.OffersStoreAdapter;
import com.clothing.models.AllClothsPojo;

import java.util.ArrayList;
import java.util.List;

public class OffersStoreActivity extends AppCompatActivity {
    RecyclerView rv_offers_store;
    List<AllClothsPojo> a1;
    OffersStoreAdapter offersStoreAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers_store);

        getSupportActionBar().setTitle("Offers Store!!");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rv_offers_store=(RecyclerView)findViewById(R.id.rv_offers_store);

        a1 = new ArrayList<>();
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/12529532/2020/10/13/ba5950c3-e148-43a7-a295-25dfc687b1931602590996414-United-Colors-of-Benetton-Men-Purple-Slim-Fit-Solid-Casual-S-6.jpg","United Colors of Benetton","CAD. 1349","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/6832572/2018/8/24/aa5fad0c-2e08-4fcb-9e36-4979251d22eb1535109119327-WROGN-Men-Red--Navy-Blue-Slim-Fit-Checked-Casual-Shirt-3891535109119109-4.jpg","HIGHLANDER","CAD. 1229","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/11780922/2020/4/3/0fe1f83f-4a92-4a9e-ac95-3559e4b978e61585905004166HIGHLANDERMenWhiteGreySlimFitPrintedCasualShirt1.jpg","WROGN","CAD. 999","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/productimage/2020/3/5/c0dba4a9-674d-425b-9da0-9d446157d1b21583362590845-1.jpg","Reebook","CAD. 1349","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/productimage/2020/2/6/a34cb78a-5ddd-4645-9bd4-33a90c4622f31580943922465-1.jpg","Lives","CAD. 1349","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/2478210/2019/11/8/85bba7bd-b3f3-4ea3-a063-5890340301471573214255868-WROGN-Men-Pink-Slim-Fit-Solid-Casual-Shirt-7431573214250598-1.jpg","PUMA","CAD. 799","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/12529532/2020/10/13/ba5950c3-e148-43a7-a295-25dfc687b1931602590996414-United-Colors-of-Benetton-Men-Purple-Slim-Fit-Solid-Casual-S-6.jpg","Peter England","CAD. 1999","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/6832572/2018/8/24/aa5fad0c-2e08-4fcb-9e36-4979251d22eb1535109119327-WROGN-Men-Red--Navy-Blue-Slim-Fit-Checked-Casual-Shirt-3891535109119109-4.jpg","United Colors of Benetton","CAD. 2499","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/11780922/2020/4/3/0fe1f83f-4a92-4a9e-ac95-3559e4b978e61585905004166HIGHLANDERMenWhiteGreySlimFitPrintedCasualShirt1.jpg","United Colors of Benetton","CAD. 1999","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/productimage/2020/3/5/c0dba4a9-674d-425b-9da0-9d446157d1b21583362590845-1.jpg","United Colors of Benetton","CAD. 1349","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/productimage/2020/2/6/a34cb78a-5ddd-4645-9bd4-33a90c4622f31580943922465-1.jpg","United Colors of Benetton","CAD. 1349","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/2478210/2019/11/8/85bba7bd-b3f3-4ea3-a063-5890340301471573214255868-WROGN-Men-Pink-Slim-Fit-Solid-Casual-Shirt-7431573214250598-1.jpg","United Colors of Benetton","CAD. 1888","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/12529532/2020/10/13/ba5950c3-e148-43a7-a295-25dfc687b1931602590996414-United-Colors-of-Benetton-Men-Purple-Slim-Fit-Solid-Casual-S-6.jpg","United Colors of Benetton","CAD. 1799","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/6832572/2018/8/24/aa5fad0c-2e08-4fcb-9e36-4979251d22eb1535109119327-WROGN-Men-Red--Navy-Blue-Slim-Fit-Checked-Casual-Shirt-3891535109119109-4.jpg","United Colors of Benetton","CAD. 1349","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/11780922/2020/4/3/0fe1f83f-4a92-4a9e-ac95-3559e4b978e61585905004166HIGHLANDERMenWhiteGreySlimFitPrintedCasualShirt1.jpg","United Colors of Benetton","CAD. 1349","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/productimage/2020/3/5/c0dba4a9-674d-425b-9da0-9d446157d1b21583362590845-1.jpg","United Colors of Benetton","CAD. 1349","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/productimage/2020/2/6/a34cb78a-5ddd-4645-9bd4-33a90c4622f31580943922465-1.jpg","United Colors of Benetton","CAD. 1349","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/2478210/2019/11/8/85bba7bd-b3f3-4ea3-a063-5890340301471573214255868-WROGN-Men-Pink-Slim-Fit-Solid-Casual-Shirt-7431573214250598-1.jpg","United Colors of Benetton","CAD. 1349","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","Available")); a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/12529532/2020/10/13/ba5950c3-e148-43a7-a295-25dfc687b1931602590996414-United-Colors-of-Benetton-Men-Purple-Slim-Fit-Solid-Casual-S-6.jpg","United Colors of Benetton","Rs. 1349","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/6832572/2018/8/24/aa5fad0c-2e08-4fcb-9e36-4979251d22eb1535109119327-WROGN-Men-Red--Navy-Blue-Slim-Fit-Checked-Casual-Shirt-3891535109119109-4.jpg","United Colors of Benetton","CAD. 1349","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/11780922/2020/4/3/0fe1f83f-4a92-4a9e-ac95-3559e4b978e61585905004166HIGHLANDERMenWhiteGreySlimFitPrintedCasualShirt1.jpg","United Colors of Benetton","CAD. 1349","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/productimage/2020/3/5/c0dba4a9-674d-425b-9da0-9d446157d1b21583362590845-1.jpg","United Colors of Benetton","CAD. 1349","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/productimage/2020/2/6/a34cb78a-5ddd-4645-9bd4-33a90c4622f31580943922465-1.jpg","United Colors of Benetton","CAD. 1349","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));
        a1.add(new AllClothsPojo("https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/2478210/2019/11/8/85bba7bd-b3f3-4ea3-a063-5890340301471573214255868-WROGN-Men-Pink-Slim-Fit-Solid-Casual-Shirt-7431573214250598-1.jpg","United Colors of Benetton","CAD. 1349","Men Purple Slim Fit Solid Casual Shirt","Purple solid casual shirt, has a mandarin collar, long sleeves, button placket, curved hem, and 1 patch pocket","ON Sale"));



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
        rv_offers_store.setLayoutManager(linearLayoutManager);

        offersStoreAdapter=new OffersStoreAdapter(OffersStoreActivity.this,a1);  //attach adapter class with therecyclerview
        rv_offers_store.setAdapter(offersStoreAdapter);

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