package com.clothing.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
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

public class CustomersCartAdapter extends RecyclerView.Adapter<CustomersCartAdapter.MyviewHolder> {

    Context context;
    List<AllClothsPojo> a1;
    String session;

    public CustomersCartAdapter(Context context, List<AllClothsPojo> categoty) {
        this.context = context;
        this.a1 = categoty;

    }

    public void setMovieList(List<AllClothsPojo> a1) {
        this.a1 = a1;
        notifyDataSetChanged();
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_cart_costomer, parent, false);
        return new MyviewHolder(view);
    }

    int minteger = 0;

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, final int pos) {

        holder.tv_name.setText(a1.get(pos).getName());
        holder.tv_price.setText(a1.get(pos).getPrice());
        //holder.tv_status.setText(a1.get(pos).getStatus());
        Glide.with(context).load(a1.get(pos).getImage()).into(holder.image_view);



       holder.increase.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                int count= Integer.parseInt(String.valueOf(holder.integer_number.getText()));
                count++;
                holder.integer_number.setText("" + count);
            }
        });

        holder.decrease.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                int count= Integer.parseInt(String.valueOf(holder.integer_number.getText()));
                if (count == 1) {
                    holder.integer_number.setText("1");
                } else {
                    count -= 1;
                    holder.integer_number.setText("" + count);
                }
            }
        });
       // holder.integer_number.setText(String.valueOf(minteger));

        /*holder.image_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, AdminClothsDetailsActivity.class);
                intent.putExtra("image",a1.get(pos).getImage());
                intent.putExtra("name",a1.get(pos).getName());
                intent.putExtra("price",a1.get(pos).getPrice());
                intent.putExtra("category",a1.get(pos).getCategory());
                intent.putExtra("description",a1.get(pos).getDescription());
                context.startActivity(intent);
            }
        });
    */
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
        Button btn_edit;
        Button decrease,increase;
        TextView integer_number;


        public MyviewHolder(View itemView) {
            super(itemView);


            image_view=(ImageView)itemView.findViewById(R.id.image_view);

            tv_name=(TextView)itemView.findViewById(R.id.tv_name);
            tv_price=(TextView)itemView.findViewById(R.id.tv_price);
            tv_status=(TextView)itemView.findViewById(R.id.tv_status);
            integer_number=(TextView)itemView.findViewById(R.id.integer_number);

            decrease=(Button)itemView.findViewById(R.id.decrease);
            increase=(Button)itemView.findViewById(R.id.increase);

            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Medium.ttf");
            tv_price.setTypeface(custom_font);
            tv_status.setTypeface(custom_font);


            Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Semibold.ttf");
            tv_name.setTypeface(font);





        }
    }
}