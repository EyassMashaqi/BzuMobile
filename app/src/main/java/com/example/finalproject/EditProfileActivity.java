package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class EditProfileActivity extends AppCompatActivity {

    private EditText pass;
    private EditText conpass;
    private Button updtbtn;
    private Button delbtn;
    private Button logoutbtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_container, new NavigationBar())
                .commit();
        getWindow().setStatusBarColor(ContextCompat.getColor(EditProfileActivity.this, R.color.gre));

        pass=findViewById(R.id.pass1);
        conpass=findViewById(R.id.conpass2);
        updtbtn=findViewById(R.id.changebtn);
        delbtn=findViewById(R.id.Delete);
        logoutbtn=findViewById(R.id.logout);

        updtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPass=pass.getText().toString();
                String confirmPass=conpass.getText().toString();

                if (newPass.isEmpty() || newPass.isEmpty()) {
                    Toast.makeText(EditProfileActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!newPass.equals(confirmPass)) {
                    Toast.makeText(EditProfileActivity.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (newPass.length() < 8) {
                    Toast.makeText(EditProfileActivity.this, "Password must be at least 8 characters long", Toast.LENGTH_SHORT).show();
                    return;
                }
                updatePassword("user_id",newPass);
            }
        });

        delbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAccount(LoginActivity.userId_global);
            }
        });

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.userId_global="";
                LoginActivity.userName_global="";
                startActivity(new Intent(EditProfileActivity.this, LoginActivity.class));
                finish();
            }
        });


    }
    public void updatePassword(String userId, String newPass){
        String url = EditProfileActivity.this.getString(R.string.ip) +"/update_user";
        JSONObject postData = new JSONObject();
        try {
            postData.put("user_id",LoginActivity.userId_global);
            postData.put("password", newPass);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, postData,
                response -> {
                    try {
                        boolean success = response.getBoolean("success");
                        if (success) {
                            Toast.makeText(EditProfileActivity.this, "Password updated successfully.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(EditProfileActivity.this, "Failed to update password.", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> Toast.makeText(EditProfileActivity.this, "Error: " + error.toString(), Toast.LENGTH_SHORT).show()
        );
        queue.add(jsonObjectRequest);
    }

    public void deleteAccount(String userId){
        String url=EditProfileActivity.this.getString(R.string.ip)+"/delete/"+userId;
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, url, null,
                response -> {
                    try {
                        boolean success = response.getBoolean("success");
                        if (success){
                            Toast.makeText(EditProfileActivity.this, "Account deleted successfully.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(EditProfileActivity.this, LoginActivity.class));
                            finish();
                        }else {
                            Toast.makeText(EditProfileActivity.this, "Failed to delete account.", Toast.LENGTH_SHORT).show();
                        }
                    }catch (JSONException e){
                        e.printStackTrace();
                    }

                },error -> Toast.makeText(EditProfileActivity.this, "Error: " + error.toString(), Toast.LENGTH_SHORT).show());
        queue.add(jsonObjectRequest);
    }



}