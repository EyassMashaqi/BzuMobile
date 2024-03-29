package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.TimeoutError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText userid;
    private EditText password;
    private Button signup;
    private Button Login;
    private CheckBox check;
    private boolean flag = false;
    private SharedPreferences prefs;
    public static SharedPreferences.Editor editor;
    public static final String NAME = "NAME";
    public static final String PASS = "PASS";
    public static final String FLAG = "FLAG";
    public static String userId_global = "";
    public static String userName_global = "";
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        userid=findViewById(R.id.iduser);
        password=findViewById(R.id.pass1);
        check=findViewById(R.id.Remember);
        signup=findViewById(R.id.signbtn);
        Login=findViewById(R.id.loginbtn);
        setupSharedPrefs();
        checkPrefs();

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.gre));


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);

            }

        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID=userid.getText().toString();
                String passuser=password.getText().toString();

                if (userID.isEmpty() || passuser.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Please Fill All Fields",Toast.LENGTH_SHORT).show();
                }else{
                    checkUser(userID,passuser);

                }

            }
        });

    }

    public void checkUser(String idUser,String userpass){

        String url=url=LoginActivity.this.getString(R.string.ip)+"/login";
        RequestQueue queue = Volley.newRequestQueue(this);
        JSONObject postData = new JSONObject();
        try {
            postData.put("user_id", idUser);
            postData.put("password", userpass);
        }catch (JSONException e){
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST,url,postData,response -> {
            Log.d("LoginResponse", response.toString());
            try {
                boolean success = response.getBoolean("success");
                if (success){//200
                    String user_name = response.getString("name");
                    Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                    String pass = user_name.replaceAll("\\p{P}","");
                    if (check.isChecked()) {
                        if (!flag) {
                            editor.putString(NAME, userid.getText().toString());
                            editor.putString(PASS, password.getText().toString());
                            editor.putBoolean(FLAG, true);
                            editor.commit();
                        }
                    } else {
                        editor.putString(NAME, "");
                        editor.putString(PASS, "");
                        editor.putBoolean(FLAG, false);
                        editor.commit();
                    }
                    userId_global = idUser;
                    userName_global = pass;
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this,"Click on your Name\nto edit your profile",Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this,"Invalid ID or Password",Toast.LENGTH_SHORT).show();

                }

            }catch (JSONException e){
                e.printStackTrace();
            }
        },error -> {

            //Toast.makeText(MainActivity.this, "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
            if (error instanceof TimeoutError || error instanceof NoConnectionError){
                Toast.makeText(LoginActivity.this, "No connection or timeout", Toast.LENGTH_SHORT).show();
            }
            else if (error instanceof AuthFailureError){
                Toast.makeText(LoginActivity.this, "Authentication failure", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonObjectRequest);

    }

    private void checkPrefs() {
        flag = prefs.getBoolean(FLAG, false);

        if (flag) {
            String name = prefs.getString(NAME, "");
            String ps = prefs.getString(PASS, "");
            userid.setText(name);
            password.setText(ps);
            check.setChecked(true);
        }
    }

    private void setupSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

}