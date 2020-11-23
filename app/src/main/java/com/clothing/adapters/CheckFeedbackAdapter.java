package com.clothing.adapters;

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

import com.clothing.R;
import com.clothing.models.FeedbackPojo;

import java.util.List;

public class CheckFeedbackAdapter extends RecyclerView.Adapter<CheckFeedbackAdapter.MyviewHolder> {

    Context context;
    List<FeedbackPojo> a1;
    String session;

    public CheckFeedbackAdapter(Context context, List<FeedbackPojo> categoty) {
        this.context = context;
        this.a1 = categoty;

    }

    public void setMovieList(List<FeedbackPojo> a1) {
        this.a1 = a1;
        notifyDataSetChanged();
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_check_feedback, parent, false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, final int pos) {

        holder.tv_name.setText(a1.get(pos).getName());
        holder.tv_email.setText(a1.get(pos).getEmail());
        holder.tv_reason.setText(a1.get(pos).getReason());

    }

    @Override
    public int getItemCount() {
        if (a1 != null) {
            return a1.size();
        }
        return 0;

    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView tv_name,tv_email,tv_reason;
        ImageView image_view;
        Button btn_add_to_cart;


        public MyviewHolder(View itemView) {
            super(itemView);


            image_view=(ImageView)itemView.findViewById(R.id.image_view);

            tv_name=(TextView)itemView.findViewById(R.id.tv_name);
            tv_email=(TextView)itemView.findViewById(R.id.tv_email);
            tv_reason=(TextView)itemView.findViewById(R.id.tv_reason);


            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Medium.ttf");
            tv_email.setTypeface(custom_font);
            tv_reason.setTypeface(custom_font);


            Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Semibold.ttf");
            tv_name.setTypeface(font);
        }
    }
}