package com.asus.lookgood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdminCategory extends AppCompatActivity {

    private ImageView mentshirt,mentrousers,menshoes,menhats,menjackets,menglasses,menwatches;
    private ImageView wshirt,dresses,wshoes,whats,whandbags,wglasses,wwatches,wtrousers;
    private ImageView cshirts,ctrousers,cshoes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        mentshirt = (ImageView)findViewById(R.id.tshirts);
        mentrousers = (ImageView)findViewById(R.id.trousers);
        menshoes = (ImageView)findViewById(R.id.shoes);
        menhats = (ImageView)findViewById(R.id.hats);
        menglasses = (ImageView)findViewById(R.id.glasses);
        menwatches = (ImageView)findViewById(R.id.watches);
        menjackets = (ImageView)findViewById(R.id.jackets);

        wshirt = (ImageView)findViewById(R.id.wtshirts);
        dresses = (ImageView)findViewById(R.id.dresses);
        wshoes = (ImageView)findViewById(R.id.wshoes);
        whats = (ImageView)findViewById(R.id.whats);
        whandbags = (ImageView)findViewById(R.id.purse);
        wglasses = (ImageView)findViewById(R.id.wglasses);
        wwatches = (ImageView)findViewById(R.id.wwatches);
        wtrousers = (ImageView)findViewById(R.id.wtrousers);

        cshirts = (ImageView)findViewById(R.id.cshirts);
        ctrousers = (ImageView)findViewById(R.id.cpants);
        cshoes = (ImageView)findViewById(R.id.cshoes);


        /* MEN products=============================================================================*/
        mentshirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategory.this,AdminAddNewProduct.class);
                intent.putExtra("category","tShirts");
                startActivity(intent);
            }
        });

        mentrousers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategory.this,AdminAddNewProduct.class);
                intent.putExtra("category","Trousers");
                startActivity(intent);
            }
        });

        menshoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategory.this,AdminAddNewProduct.class);
                intent.putExtra("category","Shoes");
                startActivity(intent);
            }
        });

        menhats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategory.this,AdminAddNewProduct.class);
                intent.putExtra("category","Hats");
                startActivity(intent);
            }
        });

        menjackets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategory.this,AdminAddNewProduct.class);
                intent.putExtra("category","Jackets");
                startActivity(intent);
            }
        });

        menglasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategory.this,AdminAddNewProduct.class);
                intent.putExtra("category","Glasses");
                startActivity(intent);
            }
        });

        menwatches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategory.this,AdminAddNewProduct.class);
                intent.putExtra("category","Watches");
                startActivity(intent);
            }
        });






        /* WOMEN products=============================================================================*/


        wshirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategory.this,AdminAddNewProduct.class);
                intent.putExtra("category","Female Shirts");
                startActivity(intent);
            }
        });

        dresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategory.this,AdminAddNewProduct.class);
                intent.putExtra("category","Female Dresses");
                startActivity(intent);
            }
        });

        wshoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategory.this,AdminAddNewProduct.class);
                intent.putExtra("category","Female Shoes");
                startActivity(intent);
            }
        });

        whats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategory.this,AdminAddNewProduct.class);
                intent.putExtra("category","Female Hats");
                startActivity(intent);
            }
        });

        whandbags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategory.this,AdminAddNewProduct.class);
                intent.putExtra("category","Hand bags");
                startActivity(intent);
            }
        });

        wglasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategory.this,AdminAddNewProduct.class);
                intent.putExtra("category","Female Glasses");
                startActivity(intent);
            }
        });

        wwatches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategory.this,AdminAddNewProduct.class);
                intent.putExtra("category","Female Watches");
                startActivity(intent);
            }
        });

        wtrousers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategory.this,AdminAddNewProduct.class);
                intent.putExtra("category","Female Trousers");
                startActivity(intent);
            }
        });


        /* Children products=============================================================================*/

        cshirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategory.this,AdminAddNewProduct.class);
                intent.putExtra("category","Kids Shirts");
                startActivity(intent);
            }
        });

        ctrousers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategory.this,AdminAddNewProduct.class);
                intent.putExtra("category","Kids Trousers");
                startActivity(intent);
            }
        });

        cshoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategory.this,AdminAddNewProduct.class);
                intent.putExtra("category","Kids Shoes");
                startActivity(intent);
            }
        });


    }
}