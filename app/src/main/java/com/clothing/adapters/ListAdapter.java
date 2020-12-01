package com.clothing.adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.clothing.R;
import com.clothing.activities.DisplaySQLiteDataActivity;
import com.clothing.activities.SQLiteHelper;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    SQLiteDatabase sqLiteDatabase;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;
    SQLiteDatabase sqLiteDatabaseObj;
    String SQLiteDataBaseQueryHolder ;

    Context context;
    ArrayList<String> ID;
    ArrayList<String> Name;
    ArrayList<String> Price;
    ArrayList<String> Category;
    ArrayList<String> Description;
    ArrayList<String> Image;
    ArrayList<String> Total_price;
    ArrayList<String> Quantity;
    ArrayList<String> User_name;


    public ListAdapter(Context context2, ArrayList<String> id, ArrayList<String> name, ArrayList<String> price,
                       ArrayList<String> category, ArrayList<String> description, ArrayList<String> image,ArrayList<String> totalprice,ArrayList<String> quantity,ArrayList<String> username)
    {
        this.context = context2;
        this.ID = id;
        this.Name = name;
        this.Price = price;
        this.Category = category;
        this.Description = description;
        this.Image = image;
        this.Total_price = totalprice;
        this.Quantity = quantity;
        this.User_name = username;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return ID.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int position, View child, ViewGroup parent) {
        Holder holder;

        LayoutInflater layoutInflater;

        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            child = layoutInflater.inflate(R.layout.items, null);

            holder = new Holder();

            holder.tv_name = (TextView) child.findViewById(R.id.tv_name);
            holder.tv_price = (TextView) child.findViewById(R.id.tv_price);
            holder.tv_category = (TextView) child.findViewById(R.id.tv_category);
            holder.tv_description = (TextView) child.findViewById(R.id.tv_description);
            holder.tv_total = (TextView) child.findViewById(R.id.tv_total);
            holder.image_view = (ImageView) child.findViewById(R.id.image_view);
            holder.image_delete = (ImageView) child.findViewById(R.id.image_delete);

            child.setTag(holder);

        } else {

            holder = (Holder) child.getTag();
        }
        holder.tv_name.setText(Name.get(position));
        holder.tv_price.setText(Price.get(position)+" CAD");
        holder.tv_category.setText(User_name.get(position));
        holder.tv_description.setText(Description.get(position));
        holder.tv_total.setText("Total "+Price.get(position)+" * "+Quantity.get(position) +" = "+Total_price.get(position));
       Glide.with(context).load(Image.get(position)).into(holder.image_view);
        holder.image_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((DisplaySQLiteDataActivity) context).deleatesingleproduct(ID.get(position));


            }
        });


        return child;
    }

    public class Holder {

        TextView tv_name;
        TextView tv_price;
        TextView tv_category;
        TextView tv_description,tv_total;
        ImageView image_view,image_delete;
    }

}