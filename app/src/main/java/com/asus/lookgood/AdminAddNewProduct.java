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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AdminAddNewProduct extends AppCompatActivity {


    private String CategoryName,description,price,pname,saveCurrentDate,saveCurrentTime;
    private Button AddNewProduct;
    private ImageView InputProductimg;
    private EditText InputProductName,InputProductdes,InputProductprice;
    private static final int Gallerypic = 1;
    private Uri imageuri;
    private String productkey,downloadimageurl;
    private StorageReference productimgref;
    private DatabaseReference ProductRef;
    private ProgressDialog loadingbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new_product);

        Toast.makeText(this,"Welcome Admin...", Toast.LENGTH_SHORT).show();



        /*Get the values form AdminCategory=================================================*/
        CategoryName = getIntent().getExtras().get("category").toString();
        productimgref = FirebaseStorage.getInstance().getReference().child("Product Images");
        ProductRef = FirebaseDatabase.getInstance().getReference().child("Mentshirts");

        AddNewProduct = (Button)findViewById(R.id.add_product);
        InputProductimg = (ImageView) findViewById(R.id.product_image);
        InputProductName = (EditText) findViewById(R.id.product_name);
        InputProductdes = (EditText) findViewById(R.id.product_description);
        InputProductprice = (EditText) findViewById(R.id.product_price);
        loadingbar = new ProgressDialog(this);



        InputProductimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();
            }
        });

        AddNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Validataenterddata();
            }
        });

    }

    private void Validataenterddata()
    {
        description = InputProductdes.getText().toString();
        price = InputProductprice.getText().toString();
        pname = InputProductName.getText().toString();

        if(imageuri == null)
        {
            Toast.makeText(this, "Product image not found", Toast.LENGTH_SHORT).show();

        }
        else if(TextUtils.isEmpty(description))
        {
            Toast.makeText(this, "Please enter description", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(price))
        {
            Toast.makeText(this, "Please enter price", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(pname))
        {
            Toast.makeText(this, "Please enter name", Toast.LENGTH_SHORT).show();
        }
        else
        {
            storeproduct();
        }

    }



    private void storeproduct()
    {
        loadingbar.setTitle("Adding new product");
        loadingbar.setMessage("give us a minute");
        loadingbar.setCanceledOnTouchOutside(false);
        loadingbar.show();


        Calendar calander = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM,dd,yyy");
        saveCurrentDate = currentDate.format(calander.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calander.getTime());

        productkey = saveCurrentDate + saveCurrentTime;

        StorageReference filePath = productimgref.child(imageuri.getLastPathSegment() + productkey);
        final  UploadTask uploadTask = filePath.putFile(imageuri);



        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                String message = e.toString();
                Toast.makeText(AdminAddNewProduct.this,"error:" + message,Toast.LENGTH_SHORT).show();
                loadingbar.dismiss();
            }




        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
            {
                Toast.makeText(AdminAddNewProduct.this,"Image uploaded successfully",Toast.LENGTH_SHORT).show();
                Task<Uri> urltask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
                    {
                        if(!task.isSuccessful())
                        {
                            throw task.getException();

                        }

                        downloadimageurl = filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();

                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task)
                    {
                        if(!task.isSuccessful())
                        {
                            try {
                                throw task.getException();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        downloadimageurl = filePath.getDownloadUrl().toString();
                        filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task)
                    {
                        if(task.isSuccessful())
                        {
                            downloadimageurl = task.getResult().toString();
                            Toast.makeText(AdminAddNewProduct.this,"Product image saved to database",Toast.LENGTH_SHORT).show();

                            SaveProductinfo();
                        }
                    }
                });

            }
        });

    }


    private void SaveProductinfo()
    {



        HashMap<String,Object> productMap = new HashMap<>();
        productMap.put("pid",productkey);
        productMap.put("date",saveCurrentDate);
        productMap.put("time",saveCurrentTime);
        productMap.put("description",description);
        productMap.put("image",downloadimageurl);
        productMap.put("category",CategoryName);
        productMap.put("price",price);
        productMap.put("pname",pname);

        ProductRef.child(productkey).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if(task.isSuccessful())
                        {
                            loadingbar.dismiss();
                            Toast.makeText(AdminAddNewProduct.this,"Product is added successfully",Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            loadingbar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(AdminAddNewProduct.this,"error" + message,Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }











    /*Access the phones gallery==============================================================*/

    private void OpenGallery()

    {
        Intent galleryintent = new Intent();
        galleryintent.setAction(Intent.ACTION_GET_CONTENT);
        galleryintent.setType("image/*");
        startActivityForResult(galleryintent,Gallerypic);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode==Gallerypic && resultCode==RESULT_OK && data!=null);
        {
            imageuri = data.getData();
            InputProductimg.setImageURI(imageuri);
        }
    }
}