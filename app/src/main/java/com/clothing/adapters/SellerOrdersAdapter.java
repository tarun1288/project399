package com.clothing.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.clothing.R;
import com.clothing.activities.SellerOrderStatusActivity;
import com.clothing.models.MyOrdersPojo;

import java.util.List;

public class SellerOrdersAdapter extends RecyclerView.Adapter<SellerOrdersAdapter.MyviewHolder> {

    Context context;
    List<MyOrdersPojo> a1;
    String session;

    public SellerOrdersAdapter(Context context, List<MyOrdersPojo> categoty) {
        this.context = context;
        this.a1 = categoty;

    }

    public void setMovieList(List<MyOrdersPojo> a1) {
        this.a1 = a1;
        notifyDataSetChanged();
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_seller_orders, parent, false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, final int pos) {

        holder.tv_order_id.setText("Order Id #:"+a1.get(pos).getOrder_id());
        holder.tv_order_date.setText("Order date :"+a1.get(pos).getOrder_date());
        holder.tv_order_status.setText("Order Status :"+a1.get(pos).getStatus());

        holder.cv_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, SellerOrderStatusActivity.class);
                intent.putExtra("order_ID",a1.get(pos).getOrder_id());
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
        TextView tv_order_id,tv_order_date,tv_order_status;
        CardView cv_order;

        public MyviewHolder(View itemView) {
            super(itemView);




            tv_order_id=(TextView)itemView.findViewById(R.id.tv_order_id);
            tv_order_date=(TextView)itemView.findViewById(R.id.tv_order_date);
            tv_order_status=(TextView)itemView.findViewById(R.id.tv_order_status);

            cv_order=(CardView)itemView.findViewById(R.id.cv_order);



            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Medium.ttf");
            tv_order_id.setTypeface(custom_font);
            tv_order_date.setTypeface(custom_font);
            tv_order_status.setTypeface(custom_font);


            Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Semibold.ttf");





        }
    }
}