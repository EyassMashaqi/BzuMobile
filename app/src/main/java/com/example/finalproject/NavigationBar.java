package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.fragment.app.Fragment;


public class NavigationBar extends Fragment {
    private String user_name;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.navigation_bar, container, false);
        ImageView calendar_button = view.findViewById(R.id.calendar_button);
        ImageView map_button = view.findViewById(R.id.map_button);
        ImageView home_button = view.findViewById(R.id. home_button);
        TextView username_textview=view.findViewById(R.id.username_textview);
        try{
            Intent intent = getActivity().getIntent();
            user_name = intent.getStringExtra("user_name");
            username_textview.setText("Welcome " + user_name + "!");
        }catch (Exception e){
            e.printStackTrace();
        }

        calendar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), CalendarActivity.class).putExtra("user_name", user_name));
            }
        });

        map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MapActivity.class).putExtra("user_name", user_name));
            }
        });

        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HomeActivity.class).putExtra("user_name", user_name));
            }
        });

        username_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HomeActivity.class).putExtra("user_name", user_name));

            }
        });

        return view;
    }
}
