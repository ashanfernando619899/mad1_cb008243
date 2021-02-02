package com.asus.lookgood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.asus.lookgood.model.Messages;
import com.asus.lookgood.prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Product_details extends AppCompatActivity
 {

    private ImageView imageView;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    EditText quty;
    private Button addtocartbtn;
    private String productid="";

    private String product_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        productid = getIntent().getStringExtra("p_id");
        /*product_name = getIntent().getStringExtra("p_id");*/

        addtocartbtn = (Button)findViewById(R.id.add_cart_btn);
        imageView = (ImageView)findViewById(R.id.productimg_details);
        textView1 = (TextView)findViewById(R.id.productname);
        textView2 = (TextView)findViewById(R.id.productdes);
        textView3 =(TextView)findViewById(R.id.productprice);
        quty =(EditText) findViewById(R.id.qty_input);

        Intent i =getIntent();
        Bitmap bitmap = (Bitmap) i.getParcelableExtra("p_img");

        String p_id = i.getStringExtra("p_id");
        String p_des = i.getStringExtra("p_des");
        String p_price = i.getStringExtra("p_price");

        imageView.setImageBitmap(bitmap);
        textView1.setText(p_id);
        textView2.setText(p_des);
        textView3.setText(p_price);

        addtocartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                addingtocart();
            }
        });



    }

    private void addingtocart()
    {
        String savetime,savedate;

        Calendar calfordate = Calendar.getInstance();
        SimpleDateFormat currentdate = new SimpleDateFormat("MM dd, yyyy");
        savedate = currentdate.format(calfordate.getTime());

        SimpleDateFormat currenttime = new SimpleDateFormat("HH:mm:ss a");
        savetime = currentdate.format(calfordate.getTime());

        final DatabaseReference cartlistref = FirebaseDatabase.getInstance().getReference().child("Cart list");
        final HashMap<String,Object> cartMap = new HashMap<>();
        cartMap.put("p_id", textView1.getText().toString());
        cartMap.put("p_des", textView2.getText().toString());
        cartMap.put("p_price", textView3.getText().toString());
        cartMap.put("p_date",savedate);
        cartMap.put("p_time",savetime);
        cartMap.put("p_qty",quty.getText().toString());

        cartlistref.child(Prevalent.CurrentOnlineUser.getPhone()).child(productid)
                .updateChildren(cartMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(Product_details.this, "Added to Cart", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }


}