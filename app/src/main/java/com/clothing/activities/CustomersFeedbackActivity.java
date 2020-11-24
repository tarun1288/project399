package com.clothing.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.clothing.R;
import com.clothing.Utils;
import com.clothing.api.ApiService;
import com.clothing.api.RetroClient;
import com.clothing.models.ResponseData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomersFeedbackActivity extends AppCompatActivity {
    EditText et_name,et_email,et_reason;
    Button btn_submit;
    SharedPreferences sharedPreferences;
    String session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers_feedback);


        getSupportActionBar().setTitle("Customers Feedback");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        session = sharedPreferences.getString("user_uname", "def-val");

        et_reason=(EditText)findViewById(R.id.et_reason);
        et_email=(EditText)findViewById(R.id.et_email);
        et_email.setText(session);
        et_name=(EditText)findViewById(R.id.et_name);

        btn_submit=(Button)findViewById(R.id.btn_submit);


    }

    private void surverSubmit(){

        ApiService apiService = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = apiService.add_feedback(et_name.getText().toString(),et_reason.getText().toString(),et_email.getText().toString());
        //Call<ResponseData> call = apiService.add_cart(name,price,"ewewe","2","fffdf","mens","2343","teee",pid);


        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {


                if (response.body().status.equals("true")) {
                    Toast.makeText(CustomersFeedbackActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    startActivity(new Intent(CustomersFeedbackActivity.this, UserDashBoardActivity.class));
                    finish();

                } else {
                    Toast.makeText(CustomersFeedbackActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                Toast.makeText(CustomersFeedbackActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
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