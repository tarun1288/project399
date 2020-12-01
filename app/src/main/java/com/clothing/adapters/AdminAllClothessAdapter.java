package com.clothing.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.clothing.R;
import com.clothing.activities.CheckFeedbacksActivity;
import com.clothing.activities.SellerClothsDetailsActivity;
import com.clothing.models.GetAllProductsPojo;

import java.util.List;

public class AdminAllClothessAdapter extends RecyclerView.Adapter<AdminAllClothessAdapter.MyviewHolder> {

    Context context;
    List<GetAllProductsPojo> a1;
    String session;

    public AdminAllClothessAdapter(Context context, List<GetAllProductsPojo> categoty) {
        this.context = context;
        this.a1 = categoty;

    }

    public void setMovieList(List<GetAllProductsPojo> a1) {
        this.a1 = a1;
        notifyDataSetChanged();
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_all_cloths, parent, false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, final int pos) {

        holder.tv_name.setText(a1.get(pos).getProductname());
        holder.tv_price.setText(a1.get(pos).getPrice()+ " CAD");
        Glide.with(context).load(a1.get(pos).getPhoto()).into(holder.image_view);

        holder.btn_reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, CheckFeedbacksActivity.class);
                intent.putExtra("pid",a1.get(pos).getPid());
                context.startActivity(intent);


            }
        });

        holder.image_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, SellerClothsDetailsActivity.class);
                intent.putExtra("image",a1.get(pos).getPhoto());
                intent.putExtra("name",a1.get(pos).getProductname());
                intent.putExtra("price",a1.get(pos).getPrice());
                intent.putExtra("category",a1.get(pos).getCid());
                intent.putExtra("description",a1.get(pos).getDescription());
                intent.putExtra("quantity",a1.get(pos).getQuantity());
                intent.putExtra("id",a1.get(pos).getPid());
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
        TextView tv_name,tv_price;
        ImageView image_view;
        Button btn_reviews;


        public MyviewHolder(View itemView) {
            super(itemView);


            image_view=(ImageView)itemView.findViewById(R.id.image_view);

            tv_name=(TextView)itemView.findViewById(R.id.tv_name);
            tv_price=(TextView)itemView.findViewById(R.id.tv_price);

            btn_reviews=(Button)itemView.findViewById(R.id.btn_reviews);

            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Medium.ttf");
            tv_price.setTypeface(custom_font);


            Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Semibold.ttf");
            tv_name.setTypeface(font);





        }
    }
}