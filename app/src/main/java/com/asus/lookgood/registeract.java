package com.asus.lookgood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class registeract extends AppCompatActivity
{
    private Button CreateAccountButton;
    private EditText inputname,inputnum,inputpass;
    private ProgressDialog loadingbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeract);


        CreateAccountButton = (Button) findViewById(R.id.register_btn);
        inputname = (EditText) findViewById(R.id.register_input1);
        inputnum = (EditText) findViewById(R.id.register_input2);
        inputpass = (EditText) findViewById(R.id.register_input3);
        loadingbar = new ProgressDialog(this);

        /*get resgister details=====================================*/

        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CreateAccount();

            }
        });

    }

    private void CreateAccount()
    {
        String name = inputname.getText().toString();
        String phone = inputnum.getText().toString();
        String pass = inputpass.getText().toString();

        /*check whether all the details are filled===========================*/

        if (TextUtils.isEmpty(name))
        {
            Toast.makeText(this,"Please enter name",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(phone))
        {
            Toast.makeText(this,"Please enter phone number",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(pass))
        {
            Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingbar.setTitle("Create Account");
            loadingbar.setMessage("Creating your account stand by");
            loadingbar.setCanceledOnTouchOutside(false);
            loadingbar.show();

            Validatephone(name,phone,pass);


        }

    }

    /*checking the database whether the records are there =============================*/

    private  void Validatephone(String name, String phone, String pass)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(!(dataSnapshot.child("Users").child(phone).exists()))
                {
                    HashMap<String,Object> userdatamap = new HashMap<>();
                    userdatamap.put("name",name);
                    userdatamap.put("phone",phone);
                    userdatamap.put("password",pass);


                    RootRef.child("Users").child(phone).updateChildren(userdatamap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if(task.isSuccessful())
                                    {
                                        loadingbar.setTitle("Account Created...");
                                        loadingbar.setMessage("press anywhere to continue");
                                        loadingbar.setCanceledOnTouchOutside(true);

                                        ((inputname)).setText("");
                                        ((inputnum)).setText("");
                                        ((inputpass)).setText("");

                                    }
                                }
                            });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}