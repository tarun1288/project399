package com.clothing.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.clothing.R;

import static android.widget.Toast.LENGTH_SHORT;

public class LoginActivity extends AppCompatActivity {
    Button login_btn;
    RadioButton radioadmin,radiouser;
    RadioButton cb_admin,cb_user;
    TextView tv_sign_up,tv_forget_pass;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle("Login");
        init();

        tv_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));

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
        /*if(et.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter email ID", LENGTH_SHORT).show();
            return;
        }
        if(et_password.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter password", LENGTH_SHORT).show();
            return;
        }*/
        if(cb_admin.isChecked()){
            str="Admin";
            startActivity(new Intent(LoginActivity.this, SellerDashBoardActivity.class));

        }
        if (cb_user.isChecked()){
            str="User";
            startActivity(new Intent(LoginActivity.this,UserDashBoardActivity.class));

        }
    }
    public void init(){
        login_btn=(Button)findViewById(R.id.login_btn);
        cb_admin=(RadioButton)findViewById(R.id.cb_admin);
        cb_user=(RadioButton)findViewById(R.id.cb_user);

        tv_sign_up=(TextView)findViewById(R.id.tv_sign_up);
        tv_forget_pass=(TextView)findViewById(R.id.tv_forget_pass);
    }
}