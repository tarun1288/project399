package com.clothing.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
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

import static android.widget.Toast.LENGTH_SHORT;

public class LoginActivity extends AppCompatActivity {
    Button login_btn;
    RadioButton radioadmin,radiouser;
    CheckBox cb_admin,cb_user;
    TextView tv_sign_up,tv_forget_pass;
    String str;
    ProgressDialog pd;
    EditText et_email,et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle("Login");
        init();

        tv_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));

            }
        });
        tv_forget_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            accounttype();

            }
        });

    }
    public void accounttype(){
        if (cb_admin.isChecked() && cb_user.isChecked()) {
            Toast.makeText(this, "Please Choose only One Role", LENGTH_SHORT).show();
            return;
        }

        if (cb_admin.isChecked()==false && cb_user.isChecked()==false) {
            Toast.makeText(this, "Please select any one Role", LENGTH_SHORT).show();
            return;
        }
        if(et_email.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter email ID", LENGTH_SHORT).show();
            return;
        }
        if(et_password.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter password", LENGTH_SHORT).show();
            return;
        }
        if(cb_admin.isChecked()){
            str="Seller";
            //startActivity(new Intent(LoginActivity.this, SellerDashBoardActivity.class));
            sellerLogindata();


        }
        if (cb_user.isChecked()){
            str="Customer";
            //startActivity(new Intent(LoginActivity.this,UserDashBoardActivity.class));
            userLogindata();

        }
    }
    public void userLogindata(){
        pd= new ProgressDialog(LoginActivity.this);
        pd.setTitle("Loading...");
        pd.show();
        ApiService apiService = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = apiService.userLogin(et_email.getText().toString(),et_password.getText().toString(),str);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                pd.dismiss();
                if (response.body().status.equals("true")) {
                    SharedPreferences sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
                    SharedPreferences.Editor et=sharedPreferences.edit();
                    et.putString("user_uname",et_email.getText().toString());
                    et.commit();
                    Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this, UserDashBoardActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public void sellerLogindata(){
        pd= new ProgressDialog(LoginActivity.this);
        pd.setTitle("Loading...");
        pd.show();
        ApiService apiService = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = apiService.seller_login(et_email.getText().toString(),et_password.getText().toString(),str);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                pd.dismiss();
                if (response.body().status.equals("true")) {
                    SharedPreferences sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
                    SharedPreferences.Editor et=sharedPreferences.edit();
                    et.putString("user_uname",et_email.getText().toString());
                    et.commit();
                    Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this, SellerDashBoardActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public void init(){
        et_email=(EditText)findViewById(R.id.et_email);
        et_password=(EditText)findViewById(R.id.et_password);
        login_btn=(Button)findViewById(R.id.login_btn);
        cb_admin=(CheckBox)findViewById(R.id.cb_admin);
        cb_user=(CheckBox)findViewById(R.id.cb_user);

        tv_sign_up=(TextView)findViewById(R.id.tv_sign_up);
        tv_forget_pass=(TextView)findViewById(R.id.tv_forget_pass);
    }
}