package com.asus.lookgood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.asus.lookgood.model.users;
import com.asus.lookgood.prevalent.Prevalent;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    private Button joinnowbtn, loginbtn;
    private ProgressDialog loadingbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        joinnowbtn = (Button) findViewById(R.id.mainjoinnow_btn);
        loginbtn = (Button) findViewById(R.id.mainlogin_btn);

        loadingbar = new ProgressDialog(this);
        Paper.init(this);




        /*log in button =========================================*/
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, loginact.class);
                startActivity(intent);
            }
        });

        /*register button =========================================*/
        joinnowbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, registeract.class);
                startActivity(intent);
            }
        });





        /*Remember me button =========================================*/

        String UserPhoneKey = Paper.book().read(Prevalent.UserPhoneKey);
        String UserPasswordKey = Paper.book().read(Prevalent.UserPasswordKey);

        if (UserPhoneKey != "" && UserPasswordKey != "") {
            if (!TextUtils.isEmpty(UserPhoneKey) && !TextUtils.isEmpty(UserPasswordKey)) {


                AllowAccess(UserPhoneKey, UserPasswordKey);

                loadingbar.setTitle("Already logged in");
                loadingbar.setMessage("Logging into your account");
                loadingbar.setCanceledOnTouchOutside(true);
                loadingbar.show();


            }
        }

    }


    private void AllowAccess(String phone, String password) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("Users").child(phone).exists()) {
                    users userdata = dataSnapshot.child("Users").child(phone).getValue(users.class);


                    if (userdata.getPhone().equals(phone)) {
                        if (userdata.getPassword().equals(password)) {
                            loadingbar.dismiss();
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            Prevalent.CurrentOnlineUser = userdata;
                            startActivity(intent);
                        } else {
                            loadingbar.setTitle("Alert");
                            loadingbar.setMessage("Please check your password");
                            loadingbar.setCanceledOnTouchOutside(true);
                        }
                    } else {
                        loadingbar.setTitle("Alert");
                        loadingbar.setMessage("Please enter Phone number");
                        loadingbar.setCanceledOnTouchOutside(true);
                    }
                } else {
                    loadingbar.setTitle("Account not found");
                    loadingbar.setMessage("Please create an account...");
                    loadingbar.setCanceledOnTouchOutside(true);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}






