package com.clothing.api;

import com.clothing.models.AllClothsPojo;
import com.clothing.models.CategoryPojo;
import com.clothing.models.EditProfilePojo;
import com.clothing.models.FeedbackPojo;
import com.clothing.models.GetAllProductsPojo;
import com.clothing.models.MyOrderStatusPojo;
import com.clothing.models.MyOrdersPojo;
import com.clothing.models.ProductFeedbackPojo;
import com.clothing.models.ResponseData;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface ApiService {

    @Multipart
    @POST("/fashionstore/user_registration.php")
    Call<ResponseData> userRegistration(
            @Part MultipartBody.Part file,
            @PartMap Map<String, String> partMap

    );

    @Multipart
    @POST("/fashionstore/add_product.php")
    Call<ResponseData> add_product(
            @Part MultipartBody.Part file,
            @PartMap Map<String, String> partMap

    );

    @GET("/fashionstore/customer_login.php")
    Call<ResponseData> userLogin(
            @Query("email") String email,
            @Query("pwd") String pwd,
            @Query("role") String role
    );

    @GET("/fashionstore/seller_login.php")
    Call<ResponseData> seller_login(
            @Query("email") String email,
            @Query("pwd") String pwd,
            @Query("role") String role


    );

    @GET("/fashionstore/getcategory.php")
    Call<List<CategoryPojo>> getcategory();


    @GET("/fashionstore/getallproducts.php")
    Call<List<GetAllProductsPojo>> get_allproducts();


    @GET("/fashionstore/getfavlist.php")
    Call<List<GetAllProductsPojo>> getfavlist( @Query("email") String email);

    @GET("/fashionstore/search.php")
    Call<List<GetAllProductsPojo>> advenced_search(@Query("price") String price,@Query("pname") String pname);

    @GET("/fashionstore/getfeedback.php")
    Call<List<ProductFeedbackPojo>> getfeedback
            (@Query("pid") String pid);



    @GET("/fashionstore/getmyorders.php")
    Call<List<MyOrdersPojo>> getmyorders( @Query("email") String email);


    @GET("/fashionstore/add_cart.php?")
    Call<ResponseData> add_cart(
            @Query("name") String name,
            @Query("price") String price,
            @Query("description") String description,
            @Query("quantity") String quantity,
            @Query("photo") String photo,
            @Query("category") String category,
            @Query("total_price") String total_price,
            @Query("uname") String uname,
            @Query("pid") String pid,
            @Query("oid") String oid
    );

    @GET("/fashionstore/feedback.php?")
    Call<ResponseData> add_feedback(
            @Query("pid") String pid,
            @Query("pname") String pname,
            @Query("email") String email,
            @Query("rating") String rating,
            @Query("reason") String reason
    );

    @GET("/fashionstore/get_user_profile.php?")
    Call<List<EditProfilePojo>> get_user_profile(
            @Query("uname") String uname
    );

    @GET("/fashionstore/myorderstatus.php?")
    Call<List<MyOrderStatusPojo>> myorderstatus(
            @Query("oid") String oid
    );

    @GET("/fashionstore/getallorders.php?")
    Call<List<MyOrdersPojo>> getallorders();

    @Multipart
    @POST("/fashionstore/user_update_profile.php")
    Call<ResponseData> user_update_profile(
            @Part MultipartBody.Part file,
            @PartMap Map<String, String> partMap

    );

    @Multipart
    @POST("/fashionstore/update_product_details.php")
    Call<ResponseData> update_product_details(
            @Part MultipartBody.Part file,
            @PartMap Map<String, String> partMap

    );

    @GET("/fashionstore/deleteproduct.php")
    Call<ResponseData> delete_product(
            @Query("id") String id

    );

    @GET("/fashionstore/updatestatus.php")
    Call<ResponseData> updatestatus(
            @Query("oid") String id,
            @Query("status") String status

    );

    @GET("/fashionstore/updateorderstats.php")
    Call<ResponseData> updateorderstats(
            @Query("id") String id

    );

    @GET("/fashionstore/forgotpassword.php")
    Call<ResponseData> forgotpassword(@Query("emailid") String emailid);

    @GET("fashionstore/getallproductsfav.php?")
    Call<List<GetAllProductsPojo>> getallproductsfav(
            @Query("email") String email
    );

    @GET("fashionstore/addfavlist.php?")
    Call<ResponseData> addfavlist(
            @Query("email") String email,
            @Query("p_id") String p_id,
            @Query("s") String s
    );

    @GET("/fashionstore/getfeedbacks.php")
    Call<List<FeedbackPojo>> getfeedbacks();

}
