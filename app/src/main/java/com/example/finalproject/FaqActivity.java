package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FaqActivity extends AppCompatActivity {

    private CardView ContactUsCard;
    private CardView MajorsCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_container, new NavigationBar())
                .commit();

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

    }

}