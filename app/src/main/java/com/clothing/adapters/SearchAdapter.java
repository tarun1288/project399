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
import com.clothing.activities.UserProductDetailsActivity;
import com.clothing.models.GetAllProductsPojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyviewHolder> {

    Context context;
    List<GetAllProductsPojo> a1,searchproduct;
    String session;

    public SearchAdapter(Context context, List<GetAllProductsPojo> sproduct) {
        this.searchproduct=sproduct;
        this.context = context;
        this.a1 = new ArrayList<GetAllProductsPojo>();
        this.a1.addAll(sproduct);

    }

    public void setMovieList(List<GetAllProductsPojo> a1) {
        this.a1 = a1;
        notifyDataSetChanged();
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_search, parent, false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, final int pos) {

        holder.tv_name.setText("Name :"+a1.get(pos).getProductname());
        holder.tv_price.setText("Price :"+a1.get(pos).getPrice());
        holder.tv_desc.setText("Description :"+a1.get(pos).getDescription());
        Glide.with(context).load(a1.get(pos).getPhoto()).into(holder.image_view);

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
        ImageView image_view;
        Button btn_buy_now;


        public MyviewHolder(View itemView) {
            super(itemView);


            image_view=(ImageView)itemView.findViewById(R.id.image_view);

            tv_name=(TextView)itemView.findViewById(R.id.tv_name);
            tv_price=(TextView)itemView.findViewById(R.id.tv_price);
            tv_desc=(TextView)itemView.findViewById(R.id.tv_desc);

            btn_buy_now=(Button)itemView.findViewById(R.id.btn_buy_now);

            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Medium.ttf");
            tv_price.setTypeface(custom_font);
            tv_desc.setTypeface(custom_font);


            Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Semibold.ttf");
            tv_name.setTypeface(font);

        }
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