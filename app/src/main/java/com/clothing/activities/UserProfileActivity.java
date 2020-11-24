package com.clothing.activities;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.clothing.R;
import com.clothing.Utils;
import com.clothing.api.ApiService;
import com.clothing.api.RetroClient;
import com.clothing.models.EditProfilePojo;
import com.clothing.models.ResponseData;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserProfileActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{
    ImageView iv_image_view;
    TextView tv_name,tv_dob;
    EditText et_email,et_phone_no,et_password;
    RadioButton radioMale,radioFemale;
    RadioGroup radioSex;
    Button btn_update;
    SharedPreferences sharedPreferences;
    String session;
    ProgressDialog progressDialog;
    List<EditProfilePojo> a1;
    private static final String TAG = RegistrationActivity.class.getSimpleName();
    private static final int REQUEST_GALLERY_CODE = 200;
    private static final int READ_REQUEST_CODE = 300;
    private static final String SERVER_PATH = "http://cegepfashionstore.com/";
    private Uri uri;
    String malefemale;
    int mYear,mMonth,mDay;
    String DAY,MONTH,YEAR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        getSupportActionBar().setTitle("My Profile");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        session = sharedPreferences.getString("user_uname", "def-val");

        iv_image_view=(ImageView)findViewById(R.id.iv_image_view);

        tv_name=(TextView)findViewById(R.id.tv_name);
        tv_dob=(TextView)findViewById(R.id.tv_dob);

        et_email=(EditText)findViewById(R.id.et_email);
        et_email.setEnabled(false);

        et_phone_no=(EditText)findViewById(R.id.et_phone_no);
        et_password=(EditText)findViewById(R.id.et_password);

        radioMale=(RadioButton)findViewById(R.id.radioMale);
        radioFemale=(RadioButton)findViewById(R.id.radioFemale);
        radioSex=(RadioGroup)findViewById(R.id.radioSex);
        tv_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                datepicker();
            }
        });

        iv_image_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK);
                openGalleryIntent.setType("image/*");
                startActivityForResult(openGalleryIntent, REQUEST_GALLERY_CODE);

            }
        });



        btn_update=(Button)findViewById(R.id.btn_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                genderselect();
                uploadImageToServer();
            }
                //startActivity(new Intent(UserProfileActivity.this,EditProfileActivity.class));

        });
        progressDialog = new ProgressDialog(UserProfileActivity.this);
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
                if(user.getGender().equals("Male")) {
                    radioMale.setChecked(true);
                }
                else{
                    radioFemale.setChecked(true);
                }

                Toast.makeText(UserProfileActivity.this, ""+user.getPic(), Toast.LENGTH_SHORT).show();
                tv_name.setText(user.getName());
                et_email.setText(user.getEmail());
                et_password.setText(user.getPassword());
                et_phone_no.setText(user.getPhone());
                tv_dob.setText(user.getDob());
                Glide.with(UserProfileActivity.this).load(user.getPic()).into(iv_image_view);

            }

            @Override
            public void onFailure(Call<List<EditProfilePojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(UserProfileActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, UserProfileActivity.this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_GALLERY_CODE && resultCode == Activity.RESULT_OK){
            uri = data.getData();
            if(EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                String filePath = getRealPathFromURIPath(uri, UserProfileActivity.this);
                file = new File(filePath);

            }else{
                EasyPermissions.requestPermissions(this, getString(R.string.read_file), READ_REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        }
    }
    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }
    File file;
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if(uri != null){
            String filePath = getRealPathFromURIPath(uri, UserProfileActivity.this);
            file = new File(filePath);
            // uploadImageToServer();
        }
    }
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d(TAG, "Permission has been denied");

    }

    private void uploadImageToServer(){
        progressDialog=new ProgressDialog(UserProfileActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        Map<String, String> map = new HashMap<>();
        map.put("name",tv_name.getText().toString());
        map.put("gender",malefemale);
        map.put("dob",tv_dob.getText().toString());
        map.put("email",session);
        map.put("password",et_password.getText().toString());
        map.put("phone",et_phone_no.getText().toString());

        RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), mFile);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService uploadImage = retrofit.create(ApiService.class);
        Call<ResponseData> fileUpload = uploadImage.user_update_profile(fileToUpload, map);
        fileUpload.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                progressDialog.dismiss();
                Toast.makeText(UserProfileActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                startActivity(new Intent(UserProfileActivity.this, UserDashBoardActivity.class));
                finish();
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(UserProfileActivity.this, "Error"+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public void genderselect() {

        if (radioMale.isChecked()) {
            malefemale = "Male";
        } else {
            malefemale = "Female";
        }
    }
    public void datepicker() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        DAY = dayOfMonth + "";
                        MONTH = monthOfYear + 1 + "";
                        YEAR = year + "";

                        tv_dob.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
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