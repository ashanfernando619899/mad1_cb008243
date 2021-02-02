package com.asus.lookgood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.ViewGroup;

import com.asus.lookgood.model.Cart;
import com.asus.lookgood.model.Cartadapter;
import com.asus.lookgood.model.Messages;
import com.asus.lookgood.model.RecyclerAdapter;
import com.asus.lookgood.prevalent.Prevalent;
import com.bumptech.glide.load.model.ModelLoader;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.widget.TextView;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference myref;
    private Cartadapter cartadapter;
    private ArrayList<Cart> list;
    private TextView checkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        recyclerView = findViewById(R.id.cartrecycleview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myref = FirebaseDatabase.getInstance().getReference();
        /*checkout = (TextView)findViewById(R.id.delete);*/

        list = new ArrayList<>();
        cartadapter = new Cartadapter(this, list);
        recyclerView.setAdapter(cartadapter);

        GetcartDataFromFirebase();


    }

    private void GetcartDataFromFirebase() {
        Query query = myref.child("Cart list").child(Prevalent.CurrentOnlineUser.getPhone());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ClearAll();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Cart messages = new Cart();

                    messages.setP_id(" " + (snapshot.child("p_id").getValue().toString()));
                    messages.setP_price(" " + "Price= " +(snapshot.child("p_price").getValue().toString()));
                    messages.setP_qty(" " + "Quantity=" +(snapshot.child("p_qty").getValue().toString()));

                    list.add(messages);
                }
                cartadapter = new Cartadapter(getApplicationContext(),list);
                recyclerView.setAdapter(cartadapter);
                recyclerView.setAdapter(cartadapter);
                cartadapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
            }
        });
    }
    private void ClearAll()
    {
        if (list != null) {
            list.clear();

            if (cartadapter != null) {
                cartadapter.notifyDataSetChanged();
            }
        } else {
            list = new ArrayList<>();
        }
    }


}








