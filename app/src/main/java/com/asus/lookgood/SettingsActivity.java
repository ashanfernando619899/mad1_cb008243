package com.asus.lookgood;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.asus.lookgood.prevalent.Prevalent;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsActivity extends AppCompatActivity
{
    private CircleImageView profileImage;
    private EditText fullname, userphone, userpass;
    private TextView profilechangebtn, closebtn, savetxtbtn;

    private Uri imageUri;
    private String muUrl = "";
    private StorageTask uploadtask;
    private StorageReference storageprofilepic;
    private String checker ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        storageprofilepic = FirebaseStorage.getInstance().getReference().child("Profile pics");

        profileImage = (CircleImageView)findViewById(R.id.profileimg_settings);
        fullname = (EditText)findViewById(R.id.settings_full_name);
        userphone = (EditText)findViewById(R.id.settings_phone_number);
        userpass = (EditText)findViewById(R.id.settings_pass);
        profilechangebtn = (TextView)findViewById(R.id.profile_imagechange_btn);
        closebtn = (TextView)findViewById(R.id.close_settingsbtn);
        savetxtbtn = (TextView)findViewById(R.id.update_settingsbtn);

         userInfoDisplay(profileImage, fullname, userphone, userpass);

         closebtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v)
             {
                 finish();
             }
         });

        savetxtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(checker.equals("clicked"))
                {
                    userInfoSaved();
                }
                else
                {
                    UpdateOnlyUserinfo();
                }
            }
        });
        profilechangebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                checker = "clicked";
                CropImage.activity(imageUri)
                        .setAspectRatio(1,1)
                        .start(SettingsActivity.this);
            }
        });
    }




    private void UpdateOnlyUserinfo()
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");
        HashMap<String,Object>useMap = new HashMap<>();
        useMap.put("name", fullname.getText().toString());
        useMap.put("phone", userphone.getText().toString());
        useMap.put("password", userpass.getText().toString());
        ref.child(Prevalent.CurrentOnlineUser.getPhone()).updateChildren(useMap);



        startActivity(new Intent(SettingsActivity.this,HomeActivity.class));
        Toast.makeText(SettingsActivity.this, "Profile info updated successfully",Toast.LENGTH_SHORT).show();
        finish();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode==RESULT_OK && data!=null)
        {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            imageUri = result.getUri();

            profileImage.setImageURI(imageUri);
        }
        else
        {
            Toast.makeText(this,"Error,Try again",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SettingsActivity.this, SettingsActivity.class));
            finish();
        }
    }




    private void userInfoSaved()
    {
        if(TextUtils.isEmpty(fullname.getText().toString()))
        {
            Toast.makeText(this, "Name is required",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(userphone.getText().toString()))
        {
            Toast.makeText(this, "phone is required",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(userpass.getText().toString()))
        {
            Toast.makeText(this, "password is required",Toast.LENGTH_SHORT).show();
        }
        else if(checker.equals("clicked"))
        {
            uploadimg();
        }
    }
    private void uploadimg()
    {
         final ProgressDialog progressDialog = new ProgressDialog(this);
         progressDialog.setTitle("Updating Profile");
         progressDialog.setMessage("Please wait your account info is being updated");
         progressDialog.setCanceledOnTouchOutside(false);
         progressDialog.show();

         if(imageUri != null)
         {
             final StorageReference fileRef = storageprofilepic.child(Prevalent.CurrentOnlineUser.getPhone() + ".jpg");
             uploadtask = fileRef.putFile(imageUri);

             uploadtask.continueWithTask(new Continuation() {
                 @Override
                 public Object then(@NonNull Task task) throws Exception
                 {
                     if(!task.isSuccessful())
                     {
                         throw task.getException();
                     }
                     return fileRef.getDownloadUrl();
                 }
             })
                     .addOnCompleteListener(new OnCompleteListener<Uri>() {
                         @Override
                         public void onComplete(@NonNull Task<Uri> task)
                         {
                            if(task.isSuccessful())
                            {
                                Uri downloaduri = task.getResult();
                                muUrl = downloaduri.toString();

                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");
                                HashMap<String,Object>useMap = new HashMap<>();
                                useMap.put("name", fullname.getText().toString());
                                useMap.put("phone", userphone.getText().toString());
                                useMap.put("password", userpass.getText().toString());
                                useMap.put("image", muUrl);
                                ref.child(Prevalent.CurrentOnlineUser.getPhone()).updateChildren(useMap);

                                progressDialog.dismiss();
                                startActivity(new Intent(SettingsActivity.this,HomeActivity.class));
                                Toast.makeText(SettingsActivity.this, "Profile info updated successfully",Toast.LENGTH_SHORT).show();
                                finish();
                            }

                         }
                     });
         }
    }


    private void userInfoDisplay(CircleImageView profileImage, EditText fullname, EditText userphone, EditText userpass)

    {
        DatabaseReference UserRef = FirebaseDatabase.getInstance().getReference().child("Users").child(Prevalent.CurrentOnlineUser.getPhone());
        UserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
               if(dataSnapshot.exists())
               {
                   if(dataSnapshot.child("image").exists())
                   {
                       String password = dataSnapshot.child("password").getValue().toString();
                       String image = dataSnapshot.child("image").getValue().toString();
                       String name = dataSnapshot.child("name").getValue().toString();
                       String phone = dataSnapshot.child("phone").getValue().toString();


                       Picasso.get().load(image).into(profileImage);
                       fullname.setText(name);
                       userphone.setText(phone);
                       userpass.setText(password);


                   }
               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}