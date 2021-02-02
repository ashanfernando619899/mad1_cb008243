package com.asus.lookgood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.asus.lookgood.model.users;
import com.asus.lookgood.prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import io.paperdb.Paper;

public class loginact extends AppCompatActivity {

    private EditText inputnum,inputpass;
    private Button loginbtn;
    private ProgressDialog loadingbar;
    private TextView AdminLink;

    private String parentdb = "Users";
    private CheckBox rememberme_chkb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginact);

        loginbtn = (Button) findViewById(R.id.login_btn);
        inputnum = (EditText) findViewById(R.id.login_input1);
        inputpass = (EditText) findViewById(R.id.login_input2);


        loadingbar = new ProgressDialog(this);

        rememberme_chkb = (CheckBox) findViewById(R.id.rememberme_chkb);
        Paper.init(this);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                loginuser();
            }
        });




    }



    private void loginuser()
    {
        String phone = inputnum.getText().toString();
        String password = inputpass.getText().toString();

        if (TextUtils.isEmpty(phone))
        {
            Toast.makeText(this,"Please enter phone number",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT).show();
        }
        else {
            loadingbar.setTitle("Login Account");
            loadingbar.setMessage("Logging in stand by");
            loadingbar.setCanceledOnTouchOutside(false);
            loadingbar.show();
            logintoacc(phone,password);
        }
    }


    private void logintoacc(String phone, String password)
    {
        if(rememberme_chkb.isChecked())
        {
            Paper.book().write(Prevalent.UserPhoneKey,phone);
            Paper.book().write(Prevalent.UserPasswordKey,password);
        }



        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.child(parentdb).child(phone).exists())
                {
                    users userdata = dataSnapshot.child(parentdb).child(phone).getValue(users.class);


                    if(userdata.getPhone().equals(phone))
                    {
                        if(userdata.getPassword().equals(password))
                        {
                            if(parentdb.equals("Admins"))
                            {
                                loadingbar.dismiss();
                                Intent intent = new Intent(loginact.this,AdminCategory.class);
                                startActivity(intent);
                            }
                            else if (parentdb.equals("Users"))
                            {
                                loadingbar.dismiss();
                                Intent intent = new Intent(loginact.this,HomeActivity.class);
                                Prevalent.CurrentOnlineUser = userdata;
                                startActivity(intent);
                            }
                        }
                        else
                        {
                            loadingbar.setTitle("Alert");
                            loadingbar.setMessage("Please check your password");
                            loadingbar.setCanceledOnTouchOutside(true);

                        }
                    }
                    else
                    {
                        loadingbar.setTitle("Alert");
                        loadingbar.setMessage("Please enter Phone number");
                        loadingbar.setCanceledOnTouchOutside(true);

                    }
                }
                else
                {
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