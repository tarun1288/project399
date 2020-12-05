package com.clothing.activities;

import android.Manifest;
import android.app.Activity;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.clothing.R;
import com.clothing.Utils;
import com.clothing.api.ApiService;
import com.clothing.api.RetroClient;
import com.clothing.models.CategoryPojo;
import com.clothing.models.ResponseData;

import java.io.File;
import java.util.ArrayList;
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

public class EditMyClothsActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{
    EditText et_product_name,et_price,et_category,et_description,et_quantity;
    Button btn_update;
    ImageView img_product;
    Spinner sp_product_cat;
    String[] category;
    String cid;
    private static final String TAG = RegistrationActivity.class.getSimpleName();
    private static final int REQUEST_GALLERY_CODE = 200;
    private static final int READ_REQUEST_CODE = 300;
    private static final String SERVER_PATH = "http://cegepfashionstore.com/";
    private Uri uri;
    String malefemale;
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;
    String session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_cloths);

        getSupportActionBar().setTitle("Edit Product");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        session = sharedPreferences.getString("user_uname", "def-val");
        loadAllCategories();

        category=getResources().getStringArray(R.array.product_category);


        et_product_name=(EditText)findViewById(R.id.et_product_name);
        et_price=(EditText)findViewById(R.id.et_price);
        sp_product_cat=(Spinner)findViewById(R.id.sp_product_cat);
        et_description=(EditText)findViewById(R.id.et_description);
        et_quantity=(EditText)findViewById(R.id.et_quantity);

        img_product=(ImageView)findViewById(R.id.img_product);

        Glide.with(EditMyClothsActivity.this).load(getIntent().getStringExtra("image")).into(img_product);
        et_product_name.setText(getIntent().getStringExtra("name"));
        et_price.setText(getIntent().getStringExtra("price"));
        et_quantity.setText(getIntent().getStringExtra("quantity"));

        /*int category1 = new ArrayList<String>(Arrays.asList(category)).indexOf(getIntent().getStringExtra("category"));
        sp_product_cat.setSelection(category1);*/
        et_description.setText(getIntent().getStringExtra("description"));


        img_product.setOnClickListener(new View.OnClickListener() {
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
                //Toast.makeText(EditMyClothsActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                uploadImageToServer();
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, EditMyClothsActivity.this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_GALLERY_CODE && resultCode == Activity.RESULT_OK){
            uri = data.getData();
            if(EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                String filePath = getRealPathFromURIPath(uri, EditMyClothsActivity.this);
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
            String filePath = getRealPathFromURIPath(uri, EditMyClothsActivity.this);
            file = new File(filePath);
            // uploadImageToServer();
        }
    }
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d(TAG, "Permission has been denied");

    }

    private void uploadImageToServer(){
        progressDialog=new ProgressDialog(EditMyClothsActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        Map<String, String> map = new HashMap<>();
        map.put("pid",getIntent().getStringExtra("id"));
        map.put("name",et_product_name.getText().toString());
        map.put("price",et_price.getText().toString());
        map.put("description",et_description.getText().toString());
        map.put("quantity",et_quantity.getText().toString());
        map.put("seller_email",session);


        RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), mFile);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService uploadImage = retrofit.create(ApiService.class);
        Call<ResponseData> fileUpload = uploadImage.update_product_details(fileToUpload, map);
        fileUpload.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                progressDialog.dismiss();
                Toast.makeText(EditMyClothsActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                startActivity(new Intent(EditMyClothsActivity.this, SellerDashBoardActivity.class));
                finish();
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(EditMyClothsActivity.this, "Error"+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private void loadAllCategories() {
        ApiService apiService = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<CategoryPojo>> call = apiService.getcategory();
        call.enqueue(new Callback<List<CategoryPojo>>() {
            @Override
            public void onResponse(Call<List<CategoryPojo>> call, Response<List<CategoryPojo>> response) {
                if (response.isSuccessful()) {
                    final List<CategoryPojo> seasons=response.body();
                    if(seasons!=null) {
                        if(seasons.size()>0) {
                            //sp_product_cat=(Spinner)findViewById(R.id.spin_selectseason);
                            ArrayList<String> categoryname = new ArrayList<String>();
                            final ArrayList<String> category_ID = new ArrayList<String>();
                            categoryname.add("Select Category");
                            category_ID.add("-1");
                            for (int i = 0; i < seasons.size(); i++) {
                                categoryname.add(seasons.get(i).getCname());
                                category_ID.add(seasons.get(i).getCid());
                            }
                            ArrayAdapter<String> adp = new ArrayAdapter<String>(EditMyClothsActivity.this, android.R.layout.simple_spinner_dropdown_item, categoryname);
                            sp_product_cat.setAdapter(adp);
                            sp_product_cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    cid= category_ID.get(sp_product_cat.getSelectedItemPosition());

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {

                                }
                            });

                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CategoryPojo>> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
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