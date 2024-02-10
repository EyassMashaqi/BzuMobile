package com.example.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;


public class NavigationBar extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.navigation_bar, container, false);
        ImageView calendar_button = view.findViewById(R.id.calendar_button);
        ImageView map_button = view.findViewById(R.id.map_button);
        ImageView home_button = view.findViewById(R.id. home_button);
        ImageView faq_button = view.findViewById(R.id.faq_button);
        ImageView smm_button = view.findViewById(R.id.smm_button);
        TextView username_textview=view.findViewById(R.id.username_textview);

        username_textview.setText(LoginActivity.userName_global);

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
                    if (getActivity() != null && !(getActivity() instanceof HomeActivity)) {
                        getActivity().finish();
                    }
                }
            });
        }

        if (!(activity instanceof MapActivity)) {
            map_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), MapActivity.class));
                    if (getActivity() != null && !(getActivity() instanceof HomeActivity)) {
                        getActivity().finish();
                    }
                }
            });
        }

        if (!(activity instanceof HomeActivity)) {
            home_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), HomeActivity.class));
                    if (getActivity() != null) {
                        getActivity().finish();
                    }
                }
            });
        }

        if (!(activity instanceof FaqActivity)) {
            faq_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), FaqActivity.class));
                    if (getActivity() != null && !(getActivity() instanceof HomeActivity)) {
                        getActivity().finish();
                    }
                }
            });
        }

        if (!(activity instanceof EditProfileActivity)) {
            username_textview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), EditProfileActivity.class));
                    if (getActivity() != null && !(getActivity() instanceof HomeActivity)) {
                        getActivity().finish();
                    }
                }
            });
        }
        if (!(activity instanceof StudyActivity)) {
            smm_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), StudyActivity.class));
                    if (getActivity() != null && !(getActivity() instanceof HomeActivity)) {
                        getActivity().finish();
                    }
                }
            });
        }
        if(activity instanceof CalendarActivity){
            calendar_button.setColorFilter(Color.BLACK);
            map_button.setColorFilter(Color.WHITE);
            faq_button.setColorFilter(Color.WHITE);
            smm_button.setColorFilter(Color.WHITE);
        }

        if(activity instanceof FaqActivity){
            calendar_button.setColorFilter(Color.WHITE);
            map_button.setColorFilter(Color.WHITE);
            faq_button.setColorFilter(Color.BLACK);
            smm_button.setColorFilter(Color.WHITE);
        }

        if(activity instanceof MapActivity){
            calendar_button.setColorFilter(Color.WHITE);
            map_button.setColorFilter(Color.BLACK);
            faq_button.setColorFilter(Color.WHITE);
            smm_button.setColorFilter(Color.WHITE);
        }

        if(activity instanceof HomeActivity){
            calendar_button.setColorFilter(Color.WHITE);
            map_button.setColorFilter(Color.WHITE);
            faq_button.setColorFilter(Color.WHITE);
            smm_button.setColorFilter(Color.WHITE);
        }
        if(activity instanceof StudyActivity){
            calendar_button.setColorFilter(Color.WHITE);
            map_button.setColorFilter(Color.WHITE);
            faq_button.setColorFilter(Color.WHITE);
            smm_button.setColorFilter(Color.BLACK);
        }
        return view;
    }

}