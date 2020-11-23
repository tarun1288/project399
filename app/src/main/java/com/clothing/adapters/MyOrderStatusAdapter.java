package com.clothing.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.clothing.R;
import com.clothing.models.MyOrderStatusPojo;

import java.util.List;

public class MyOrderStatusAdapter extends RecyclerView.Adapter<MyOrderStatusAdapter.MyviewHolder> {

    Context context;
    List<MyOrderStatusPojo> a1;
    String session;

    public MyOrderStatusAdapter(Context context, List<MyOrderStatusPojo> categoty) {
        this.context = context;
        this.a1 = categoty;

    }

    public void setMovieList(List<MyOrderStatusPojo> a1) {
        this.a1 = a1;
        notifyDataSetChanged();
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_my_order_status, parent, false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, final int pos) {

        holder.tv_product_name.setText("Name:"+a1.get(pos).getName());
        holder.tv_product_price.setText("Price:"+a1.get(pos).getPrice());
        holder.tv_description.setText(a1.get(pos).getDescription());
        holder.tv_order_id.setText("OrderID  #:"+a1.get(pos).getOrder_id());
        holder.tv_order_status.setText("Order Status  :"+a1.get(pos).getStatus());
        holder.tv_order_date.setText("Order Date:"+a1.get(pos).getOrder_date());

        Glide.with(context).load(a1.get(pos).getPhoto()).into(holder.image_view);




    }

    @Override
    public int getItemCount() {
        if (a1 != null) {
            return a1.size();
        }
        return 0;

    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView tv_product_name,tv_product_price,tv_description,tv_order_id,tv_order_date,tv_order_status;
        ImageView image_view;

        public MyviewHolder(View itemView) {
            super(itemView);




            tv_product_name=(TextView)itemView.findViewById(R.id.tv_product_name);
            tv_product_price=(TextView)itemView.findViewById(R.id.tv_product_price);
            tv_description=(TextView)itemView.findViewById(R.id.tv_description);
            tv_order_id=(TextView)itemView.findViewById(R.id.tv_order_id);
            tv_order_date=(TextView)itemView.findViewById(R.id.tv_order_date);
            tv_order_status=(TextView)itemView.findViewById(R.id.tv_order_status);

            image_view=(ImageView)itemView.findViewById(R.id.image_view);



            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Medium.ttf");
            tv_product_name.setTypeface(custom_font);
            tv_product_price.setTypeface(custom_font);
            tv_description.setTypeface(custom_font);


            Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Semibold.ttf");
            tv_order_id.setTypeface(font);






        }
    }
}