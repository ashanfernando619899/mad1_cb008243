package com.asus.lookgood.model;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.asus.lookgood.Catagory_men_tshirts;
import com.asus.lookgood.Catagory_menu;
import com.asus.lookgood.MainActivity;
import com.asus.lookgood.Product_details;
import com.asus.lookgood.R;
import com.bumptech.glide.Glide;

import java.net.CookieHandler;
import java.util.ArrayList;
import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<com.asus.lookgood.model.RecyclerAdapter.ViewHolder>
 {
    private static final String tag = "RecyclerView";
    private Context mContext;
    private List<Messages> messagesList;

    public RecyclerAdapter(Context mContext, List<Messages> messagesList)
    {
        this.mContext = mContext;
        this.messagesList = messagesList;
    }

    @NonNull
    @Override
    public com.asus.lookgood.model.RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mentshirt_category,parent, false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.asus.lookgood.model.RecyclerAdapter.ViewHolder holder, int position)
    {

        Glide.with(mContext).load(messagesList.get(position).getImageUrl())
                .into(holder.imageView);

        holder.textView1.setText(messagesList.get(position).getPname());
        holder.textView2.setText(messagesList.get(position).getDescription());
        holder.textView3.setText(messagesList.get(position).getPrice());
        holder.imageView.setImageURI(Uri.parse(messagesList.get(position).imageUrl));
    }

    @Override
    public int getItemCount() {
        return messagesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {

        ImageView imageView;
        TextView textView1;
        TextView textView2;
        TextView textView3;
        Button btn1;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView1 = itemView.findViewById(R.id.textview1);
            textView2 = itemView.findViewById(R.id.textview2);
            textView3 = itemView.findViewById(R.id.textview3);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(),Product_details.class);

                    Bitmap bmp = BitmapFactory.decodeResource(Resources.getSystem(), imageView.getImageAlpha());
                    i.putExtra("p_img",bmp);
                    i.putExtra("p_id",textView1.getText() );
                    i.putExtra("p_des",textView2.getText() );
                    i.putExtra("p_price",textView3.getText() );
                    v.getContext() .startActivity(i);
                }
            });
        }
    }


 }





