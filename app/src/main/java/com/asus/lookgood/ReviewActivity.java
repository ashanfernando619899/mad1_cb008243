package com.asus.lookgood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.asus.lookgood.prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ReviewActivity extends AppCompatActivity {

    private Button send_msgbtn,view_revbtn;
    private EditText inputemail,inputname,message;
    private ProgressDialog loadingbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        send_msgbtn = (Button) findViewById(R.id.register_btn);
        view_revbtn = (Button) findViewById(R.id.register_btn2);
        inputemail = (EditText) findViewById(R.id.register_input1);
        inputname = (EditText) findViewById(R.id.register_input2);
        message = (EditText) findViewById(R.id.register_input3);
        loadingbar = new ProgressDialog(this);

        send_msgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                savemsg();

            }
        });
    }

    private void savemsg()
    {
        String pname = inputemail.getText().toString();
        String name = inputname.getText().toString();
        String msg = message.getText().toString();

        /*check whether all the details are filled===========================*/

        if (TextUtils.isEmpty(pname))
        {
            Toast.makeText(this,"Please enter product name",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(name))
        {
            Toast.makeText(this,"Please enter name",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(msg))
        {
            Toast.makeText(this,"Please enter message",Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingbar.setTitle("Sending message");
            loadingbar.setMessage("Stand by");
            loadingbar.setCanceledOnTouchOutside(false);
            loadingbar.show();

            Validatephone(pname,name,msg);


        }

    }

    private void Validatephone(String pname, String name, String msg)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(!(dataSnapshot.child("product reviwes").child(name).exists()))
                {
                    HashMap<String,Object> userdatamap = new HashMap<>();
                    userdatamap.put("product name",pname);
                    userdatamap.put("name",name);
                    userdatamap.put("message",msg);


                    RootRef.child("product reviwes").child(Prevalent.CurrentOnlineUser.getPhone()).updateChildren(userdatamap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if(task.isSuccessful())
                                    {
                                        loadingbar.setTitle("Rewiew sent...");
                                        loadingbar.setMessage("Thankyou for your feedback... Touch anywhere to continue");
                                        loadingbar.setCanceledOnTouchOutside(true);

                                        ((inputemail)).setText("");
                                        ((inputname)).setText("");
                                        ((message)).setText("");

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