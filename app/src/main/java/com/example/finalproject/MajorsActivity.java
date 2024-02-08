package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MajorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_majors);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_container, new NavigationBar())
                .commit();


        TextView ritaj_button = findViewById(R.id.ritaj_majorsButton);
        TextView contact_us_button = findViewById(R.id.contact_usButton);
        TextView switch_language_button = findViewById(R.id.language_switch);
        TextView title = findViewById(R.id.title);
        TextView paragraph = findViewById(R.id.paragraph);

        ritaj_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://ritaj.birzeit.edu/acap/application";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        contact_us_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MajorsActivity.this, ContactActivity.class));
                finish();
            }
        });

        switch_language_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switch_language_button.getText().equals("Arabic")){

                    Locale locale = new Locale("AR");
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getResources().updateConfiguration(config, getResources().getDisplayMetrics());

                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                } else{
                    Locale locale = new Locale("EN");
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getResources().updateConfiguration(config, getResources().getDisplayMetrics());

                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            }
        });


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Locale locale = new Locale("EN");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        finish();
    }


}