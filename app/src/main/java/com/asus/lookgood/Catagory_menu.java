package com.asus.lookgood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;



public class Catagory_menu extends AppCompatActivity {


    private ImageView mentshirt,mentrousers,menshoes,menhats,menjackets,menglasses,menwatches;
    private ImageView wshirt,dresses,wshoes,whats,whandbags,wglasses,wwatches,wtrousers;
    private ImageView cshirts,ctrousers,cshoes;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagory_menu);

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
                Intent intent = new Intent(Catagory_menu.this, Catagory_men_tshirts.class);
                intent.putExtra("category","tShirts");
                startActivity(intent);
            }
        });

        mentrousers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Catagory_menu.this, Catagory_men_trousers.class);
                intent.putExtra("category","tShirts");
                startActivity(intent);
            }
        });

        menshoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Catagory_menu.this, Catagory_men_shoes.class);
                intent.putExtra("category","tShirts");
                startActivity(intent);
            }
        });

        menhats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Catagory_menu.this, Catagory_men_hats.class);
                intent.putExtra("category","tShirts");
                startActivity(intent);
            }
        });

        menjackets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Catagory_menu.this, Catagory_men_jackets.class);
                intent.putExtra("category","tShirts");
                startActivity(intent);
            }
        });

        menglasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Catagory_menu.this, Catagory_men_glasses.class);
                intent.putExtra("category","tShirts");
                startActivity(intent);
            }
        });

        menwatches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Catagory_menu.this, Catagory_men_watches.class);
                intent.putExtra("category","tShirts");
                startActivity(intent);
            }
        });





        /* WOMEN products=============================================================================*/

        wshirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Catagory_menu.this, Catagory_women_tshirts.class);
                intent.putExtra("category","tShirts");
                startActivity(intent);
            }
        });

        dresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Catagory_menu.this, Catagory_women_dress.class);
                intent.putExtra("category","tShirts");
                startActivity(intent);
            }
        });

        wshoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Catagory_menu.this, Catagory_women_shoes.class);
                intent.putExtra("category","tShirts");
                startActivity(intent);
            }
        });

        whats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Catagory_menu.this, Catagory_women_hats.class);
                intent.putExtra("category","tShirts");
                startActivity(intent);
            }
        });

        wglasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Catagory_menu.this, Catagory_women_glasses.class);
                intent.putExtra("category","tShirts");
                startActivity(intent);
            }
        });

        wwatches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Catagory_menu.this, Catagory_women_watch.class);
                intent.putExtra("category","tShirts");
                startActivity(intent);
            }
        });

        whandbags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Catagory_menu.this, Catagory_women_bag.class);
                intent.putExtra("category","tShirts");
                startActivity(intent);
            }
        });

        wtrousers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Catagory_menu.this, Catagory_women_trousers.class);
                intent.putExtra("category","tShirts");
                startActivity(intent);
            }
        });




        /* Kids products=============================================================================*/

        cshirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Catagory_menu.this, Catagory_kids_tshirts.class);
                intent.putExtra("category","tShirts");
                startActivity(intent);
            }
        });

        ctrousers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Catagory_menu.this, Catagory_kids_trousers.class);
                intent.putExtra("category","tShirts");
                startActivity(intent);
            }
        });

        cshoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Catagory_menu.this, Catagory_kids_shoes.class);
                intent.putExtra("category","tShirts");
                startActivity(intent);
            }
        });



    }


}