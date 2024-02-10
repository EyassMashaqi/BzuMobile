package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudyActivity extends AppCompatActivity implements SelectCourse{

    private RecyclerView recyclerView;
    private RecyclerViewAdapter ViewAdapter;
    private ArrayList<String> idd = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_container, new NavigationBar())
                .commit();

        recyclerView = findViewById(R.id.m_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getCourses();
    }

    private void getCourses() {
        String url = StudyActivity.this.getString(R.string.ip)+"/courses";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        List<course> cour = new ArrayList<>();

                        for (int i = 0; i < response.length(); i++) {
                            try {
//                                JSONObject jsonObject = response.getJSONObject(i);
                                JSONArray jsonObject = response.getJSONArray(i);

                                String id = jsonObject.getString(0);
                                String name = jsonObject.getString(1);
                                String link = jsonObject.getString(2);
                                idd.add(link);

                                cour.add(new course(id, name, link));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        ViewAdapter = new RecyclerViewAdapter(cour, StudyActivity.this);
                        recyclerView.setAdapter(ViewAdapter);
                        Log.d("hh", "hih");

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onItemClick(int pos) {
//        Log.d("hh", "hi");
        String url = idd.get(pos);
        Log.d("hh", url);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
