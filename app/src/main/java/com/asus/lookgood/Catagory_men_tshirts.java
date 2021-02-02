package com.asus.lookgood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.asus.lookgood.model.Messages;
import com.asus.lookgood.model.RecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Catagory_men_tshirts extends AppCompatActivity  {

    RecyclerView recyclerView;

    private DatabaseReference myref;
    private ArrayList<Messages> messageslist;
    private RecyclerAdapter recyclerAdapter;
    private Context mContext;
    private ImageView imageView;
    private TextView productname,productdes,productprice;
    private Button addtocart;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagory_men_tshirts);

        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager LayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setHasFixedSize(true);
        imageView = (ImageView)findViewById(R.id.imageView);
        productname = (TextView)findViewById(R.id.textview1);
        productdes = (TextView)findViewById(R.id.textview2);
        productprice = (TextView)findViewById(R.id.textview3);
        relativeLayout = (RelativeLayout)findViewById(R.id.catagory_relative);

        myref = FirebaseDatabase.getInstance().getReference();
        messageslist = new ArrayList<>();
        ClearAll();

        GetDataFromFirebase();
    }

    private void GetDataFromFirebase() {

        Query query = myref.child("Mentshirts");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                ClearAll();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Messages messages = new Messages();
                    messages.setImageUrl(snapshot.child("image").getValue().toString());
                    messages.setPname(" "+(snapshot.child("pname").getValue().toString()));
                    messages.setDescription(" "+(snapshot.child("description").getValue().toString()));
                    messages.setPrice(" "+(snapshot.child("price").getValue().toString()) + " LKR");

                    messageslist.add(messages);
                }
                recyclerAdapter = new RecyclerAdapter(getApplicationContext(), messageslist);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void ClearAll() {
        if (messageslist != null) {
            messageslist.clear();

            if (recyclerAdapter != null) {
                recyclerAdapter.notifyDataSetChanged();
            }
        } else {
            messageslist = new ArrayList<>();
        }
    }



}
