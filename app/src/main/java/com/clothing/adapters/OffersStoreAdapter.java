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
import com.clothing.models.AllClothsPojo;

import java.util.List;

public class OffersStoreAdapter extends RecyclerView.Adapter<OffersStoreAdapter.MyviewHolder> {

    Context context;
    List<AllClothsPojo> a1;
    String session;

    public OffersStoreAdapter(Context context, List<AllClothsPojo> categoty) {
        this.context = context;
        this.a1 = categoty;

    }

    public void setMovieList(List<AllClothsPojo> a1) {
        this.a1 = a1;
        notifyDataSetChanged();
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_offers_store, parent, false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, final int pos) {

        holder.tv_name.setText(a1.get(pos).getName());
        holder.tv_price.setText(a1.get(pos).getPrice());
        //holder.tv_status.setText(a1.get(pos).getStatus());
        Glide.with(context).load(a1.get(pos).getImage()).into(holder.image_view);

        holder.btn_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, CustomersCartActivity.class);
                intent.putExtra("image",a1.get(pos).getImage());
                intent.putExtra("name",a1.get(pos).getName());
                intent.putExtra("price",a1.get(pos).getPrice());
                intent.putExtra("category",a1.get(pos).getCategory());
                intent.putExtra("description",a1.get(pos).getDescription());
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
        TextView tv_name,tv_price,tv_status;
        ImageView image_view;
        Button btn_add_to_cart;


        public MyviewHolder(View itemView) {
            super(itemView);


            image_view=(ImageView)itemView.findViewById(R.id.image_view);

            tv_name=(TextView)itemView.findViewById(R.id.tv_name);
            tv_price=(TextView)itemView.findViewById(R.id.tv_price);
            tv_status=(TextView)itemView.findViewById(R.id.tv_status);

            btn_add_to_cart=(Button)itemView.findViewById(R.id.btn_add_to_cart);

            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Medium.ttf");
            tv_price.setTypeface(custom_font);
            tv_status.setTypeface(custom_font);


            Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Semibold.ttf");
            tv_name.setTypeface(font);





        }
    }
}