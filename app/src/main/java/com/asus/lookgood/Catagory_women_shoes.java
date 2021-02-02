package com.asus.lookgood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.asus.lookgood.model.Messages;
import com.asus.lookgood.model.RecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Catagory_women_shoes extends AppCompatActivity {

    RecyclerView recyclerView;

    private DatabaseReference myref;

    private ArrayList<Messages> messageslist;
    private RecyclerAdapter recyclerAdapter;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagory_women_shoes);


        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager LayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setHasFixedSize(true);


        myref = FirebaseDatabase.getInstance().getReference();

        messageslist = new ArrayList<>();
        ClearAll();

        GetDataFromFirebase();
    }


    private void GetDataFromFirebase() {
        Query query = myref.child("Womenshoes");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
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

