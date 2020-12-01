package com.clothing.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.clothing.R;
import com.clothing.Utils;
import com.clothing.api.ApiService;
import com.clothing.api.RetroClient;
import com.clothing.models.EditProfilePojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerProfileActivity extends AppCompatActivity {
    Button btn_update;
    TextView tv_check_feedback;
    ImageView iv_image_view;
    SharedPreferences sharedPreferences;
    String session;
    List<EditProfilePojo> a1;
    TextView tv_name;
    EditText et_email,et_phone_no;
    ProgressDialog progressDialog;
    String name,gender,email,password,phoneno,image,dateofbirth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_profile);

        getSupportActionBar().setTitle("My Profile");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        session = sharedPreferences.getString("user_uname", "def-val");
        editprofile();

        et_email=(EditText)findViewById(R.id.et_email);
        et_email.setEnabled(false);
        et_phone_no=(EditText)findViewById(R.id.et_phone_no);
        tv_name=(TextView)findViewById(R.id.tv_name);
        iv_image_view=(ImageView) findViewById(R.id.iv_image_view);

        tv_check_feedback=(TextView)findViewById(R.id.tv_check_feedback);
        tv_check_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SellerProfileActivity.this, CheckFeedbacksActivity.class));
            }
        });

        btn_update=(Button)findViewById(R.id.btn_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(SellerProfileActivity.this,EditProfileActivity.class));
                Intent intent=new Intent(SellerProfileActivity.this, EditProfileActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("email",email);
                intent.putExtra("gender",gender);
                intent.putExtra("phoneno",phoneno);
                intent.putExtra("password",password);
                intent.putExtra("image",image);
                intent.putExtra("dob",dateofbirth);
                startActivity(intent);
            }
        });
    }


    public void editprofile(){
        progressDialog = new ProgressDialog(SellerProfileActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<EditProfilePojo>> call = service.get_user_profile(session);

        call.enqueue(new Callback<List<EditProfilePojo>>() {
            @Override
            public void onResponse(Call<List<EditProfilePojo>> call, Response<List<EditProfilePojo>> response) {

                progressDialog.dismiss();
                a1 = response.body();
                EditProfilePojo user = a1.get(0);
                name=user.getName();
                gender=user.getGender();
                email=user.getEmail();
                phoneno=user.getPhone();
                password=user.getPassword();
                image=user.getPic();
                dateofbirth=user.getDob();

               /* if(user.getGender().equals("Male")) {
                    radioMale.setChecked(true);
                }
                else{
                    radioFemale.setChecked(true);
                }*/

                Toast.makeText(SellerProfileActivity.this, ""+user.getPic(), Toast.LENGTH_SHORT).show();
                tv_name.setText(user.getName());
                et_email.setText(user.getEmail());
                //et_password.setText(user.getPassword());
                et_phone_no.setText(user.getPhone());
                //tv_dob.setText(user.getDob());
                Glide.with(SellerProfileActivity.this).load(user.getPic()).into(iv_image_view);

            }

            @Override
            public void onFailure(Call<List<EditProfilePojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(SellerProfileActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
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