package com.clothing.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.clothing.R;
import com.clothing.adapters.CheckFeedbackAdapter;
import com.clothing.api.ApiService;
import com.clothing.api.RetroClient;
import com.clothing.models.ProductFeedbackPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckFeedbacksActivity extends AppCompatActivity {
    RecyclerView rv_check_feedback;
    List<ProductFeedbackPojo> a1;
    CheckFeedbackAdapter checkFeedbackAdapter;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_feedbacks);

        getSupportActionBar().setTitle(" Feedbacks");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rv_check_feedback = (RecyclerView) findViewById(R.id.rv_check_feedback);

        a1 = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rv_check_feedback.setLayoutManager(linearLayoutManager);

        Toast.makeText(this, ""+getIntent().getStringExtra("pid"), Toast.LENGTH_SHORT).show();

        ProductReviews();

    }

    public void ProductReviews(){
        pd= new ProgressDialog(CheckFeedbacksActivity.this);
        pd.setTitle("Loading...");
        pd.show();
        ApiService apiService = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<ProductFeedbackPojo>> call = apiService.getfeedback(getIntent().getStringExtra("pid"));
        call.enqueue(new Callback<List<ProductFeedbackPojo>>() {
            @Override
            public void onResponse(Call<List<ProductFeedbackPojo>> call, Response<List<ProductFeedbackPojo>> response) {
                pd.dismiss();
                if (response.body()==null) {
                    Toast.makeText(CheckFeedbacksActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                }
                if (response.body().size()==0) {
                    Toast.makeText(CheckFeedbacksActivity.this, "No Reviews for this product", Toast.LENGTH_SHORT).show();
                }
                else{
                    a1 = response.body();
                    checkFeedbackAdapter = new CheckFeedbackAdapter(CheckFeedbacksActivity.this, a1);  //attach adapter class with therecyclerview
                    rv_check_feedback.setAdapter(checkFeedbackAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<ProductFeedbackPojo>> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(CheckFeedbacksActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
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