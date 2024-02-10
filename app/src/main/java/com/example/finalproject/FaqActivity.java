package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FaqActivity extends AppCompatActivity {

    private CardView ContactUsCard;
    private CardView MajorsCard;

    private CardView ElectivesCard;

    private CardView socialMedia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_container, new NavigationBar())
                .commit();

        getWindow().setStatusBarColor(ContextCompat.getColor(FaqActivity.this, R.color.gre));


        ContactUsCard = findViewById(R.id.contacts_us);
        ContactUsCard.setOnClickListener(e->{
                Intent intent = new Intent(FaqActivity.this, ContactActivity.class);
                startActivity(intent);
        });

        MajorsCard = findViewById(R.id.major);
        MajorsCard.setOnClickListener(e->{
            Intent intent = new Intent(FaqActivity.this, MajorsActivity.class);
            startActivity(intent);
        });
        ElectivesCard = findViewById(R.id.electives);
        ElectivesCard.setOnClickListener(e->{
            Intent intent = new Intent(FaqActivity.this, ElectivesActivity.class);
            startActivity(intent);
        });
        socialMedia = findViewById(R.id.SocialMedia);
        socialMedia.setOnClickListener(e->{
            Intent intent = new Intent(FaqActivity.this, SocialMediaAct.class);
            startActivity(intent);
        });
    }

}