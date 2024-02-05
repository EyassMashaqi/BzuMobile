package com.example.finalproject;

import android.app.Activity;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.navigation_bar, container, false);
        ImageView calendar_button = view.findViewById(R.id.calendar_button);
        ImageView map_button = view.findViewById(R.id.map_button);
        ImageView home_button = view.findViewById(R.id. home_button);
        TextView username_textview=view.findViewById(R.id.username_textview);

        username_textview.setText("Welcome "+LoginActivity.userName_global);
        Activity activity = null;
        try {
            activity = getActivity();
        }
        catch (Exception e){
            Log.d("Error",e.toString());
        }

        if (!(activity instanceof CalendarActivity)) {
            calendar_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(getActivity(), CalendarActivity.class));
                }
            });
        }
        if (!(activity instanceof MapActivity)) {
            map_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), MapActivity.class));
                }
            });
        }
        if (!(activity instanceof HomeActivity)) {
            home_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), HomeActivity.class));
                }
            });
        }
        username_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HomeActivity.class));

            }
        });

        return view;
    }
}