package com.clothing.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.clothing.R;
import com.clothing.activities.CheckFeedbacksActivity;
import com.clothing.activities.UserDashBoardActivity;
import com.clothing.activities.UserProductDetailsActivity;
import com.clothing.api.ApiService;
import com.clothing.api.RetroClient;
import com.clothing.models.GetAllProductsPojo;
import com.clothing.models.ResponseData;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDashBoardAdapter extends RecyclerView.Adapter<UserDashBoardAdapter.MyviewHolder> {

    Context context;
    List<GetAllProductsPojo> a1,searchproduct;
    String session;
    Boolean clicked=false;
    String s;

    public UserDashBoardAdapter(Context context, List<GetAllProductsPojo> sproduct, String session) {
        this.searchproduct=sproduct;
        this.context = context;
        this.session = session;
        this.a1 = new ArrayList<GetAllProductsPojo>();
        this.a1.addAll(sproduct);

    }

    public void setMovieList(List<GetAllProductsPojo> a1) {
        this.a1 = a1;
        notifyDataSetChanged();
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_user_dashboard, parent, false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, final int pos) {

        holder.tv_name.setText("Name :"+a1.get(pos).getProductname());
        holder.tv_price.setText("Price :"+a1.get(pos).getPrice()+" CAD");
        holder.tv_desc.setText("Description :"+a1.get(pos).getDescription());
        Glide.with(context).load(a1.get(pos).getPhoto()).into(holder.image_view);

        holder.iv_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(a1.get(pos).getStatus().equals("Like")) {
                    s="Unlike";
                    serverData(a1.get(pos).getPid(),session,s);

                }
                else
                {
                    s="Like";
                    serverData(a1.get(pos).getPid(),session,s);


                }

            }
        });

        if(a1.get(pos).getStatus().equals("Unlike"))
        {

            holder.iv_fav.setImageResource(R.drawable.ic_heart1);
        }
        if (a1.get(pos).getStatus().equals("Like") && a1.get(pos).getEmail().equals(session))
        {
            holder.iv_fav.setImageResource(R.drawable.ic_heart2);

        }

        holder.btn_buy_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, UserProductDetailsActivity.class);
                intent.putExtra("image",a1.get(pos).getPhoto());
                intent.putExtra("name",a1.get(pos).getProductname());
                intent.putExtra("price",a1.get(pos).getPrice());
                intent.putExtra("category",a1.get(pos).getCid());
                intent.putExtra("description",a1.get(pos).getDescription());
                intent.putExtra("pid",a1.get(pos).getPid());
                context.startActivity(intent);
            }
        });

        holder.btn_reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, CheckFeedbacksActivity.class);
                intent.putExtra("pid",a1.get(pos).getPid());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (a1 != null) {
            return a1.size();
        }
        return 0;

    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView tv_name,tv_price,tv_desc;
        ImageView image_view,iv_fav;
        Button btn_buy_now,btn_reviews;


        public MyviewHolder(View itemView) {
            super(itemView);

            image_view=(ImageView)itemView.findViewById(R.id.image_view);
            iv_fav=(ImageView)itemView.findViewById(R.id.iv_fav);
            tv_name=(TextView)itemView.findViewById(R.id.tv_name);
            tv_price=(TextView)itemView.findViewById(R.id.tv_price);
            tv_desc=(TextView)itemView.findViewById(R.id.tv_desc);

            btn_buy_now=(Button)itemView.findViewById(R.id.btn_buy_now);
            btn_reviews=(Button)itemView.findViewById(R.id.btn_reviews);

            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Medium.ttf");
            tv_price.setTypeface(custom_font);
            tv_desc.setTypeface(custom_font);

            Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Semibold.ttf");
            tv_name.setTypeface(font);

        }
    }
    ProgressDialog progressDialog;
    public void serverData(String pid,String email,String s){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.addfavlist(email,pid,s);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(context,"Server issue",Toast.LENGTH_SHORT).show();
                }
                else {
                    context.startActivity(new Intent(context, UserDashBoardActivity.class));
                    Toast.makeText(context,response.body().message,Toast.LENGTH_SHORT).show();
                    //((Activity)context).finish();
                }
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void productFilter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        a1.clear();
        if (charText.length() == 0) {
            a1.addAll(searchproduct);
        } else {
            for (GetAllProductsPojo wp : searchproduct) {
                if (wp.getProductname().toLowerCase(Locale.getDefault()).contains(charText) || wp.getPrice().toLowerCase(Locale.getDefault()).contains(charText)) {
                    a1.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}