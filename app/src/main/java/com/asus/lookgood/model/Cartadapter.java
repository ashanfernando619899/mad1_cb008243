package com.asus.lookgood.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asus.lookgood.Product_details;
import com.asus.lookgood.R;
import com.asus.lookgood.prevalent.Prevalent;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Cartadapter extends RecyclerView.Adapter<Cartadapter.MyViewHolder> {

    ArrayList<Cart> Clist;
    Context context;
    private DatabaseReference myref;
    int overtotprice = 0;
    public Cartadapter(Context context , ArrayList<Cart> Clist){
        this.Clist = Clist;
        this.context = context;
        myref = FirebaseDatabase.getInstance().getReference();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.cartview, parent , false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

         Cart cart = Clist.get(position);

         holder.p_id.setText(cart.getP_id());
        holder.p_price.setText(cart.getP_price());
        holder.p_qty.setText(cart.getP_qty());





        holder.delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myref.child("Cart list")
                        .child(Prevalent.CurrentOnlineUser.getPhone()).removeValue();
            }
        });
    }

    @Override
    public int getItemCount() {
        return Clist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView p_id, p_price;
        TextView p_qty;
        TextView delete_btn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            p_id = itemView.findViewById(R.id.cart_pname);
            p_price = itemView.findViewById(R.id.cart_pprice);
            p_qty = itemView.findViewById(R.id.cart_qty);
            delete_btn = itemView.findViewById(R.id.delete_btn);
        }
    }


}
