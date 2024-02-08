package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class StudyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_container, new NavigationBar())
                .commit();

    }
}