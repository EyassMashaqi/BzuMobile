package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactActivity extends AppCompatActivity {

    private EditText email, message;
    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_container, new NavigationBar())
                .commit();

        getWindow().setStatusBarColor(ContextCompat.getColor(ContactActivity.this, R.color.gre));


        email = findViewById(R.id.email);
        message = findViewById(R.id.message);
        send = findViewById(R.id.send);
        send.setOnClickListener(e-> {

            String emailid = email.getText().toString();
            String msg = message.getText().toString();

            if (emailid.isEmpty() || msg.isEmpty()) {
                Toast.makeText(ContactActivity.this, "Please fill all fields",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            String url=url=ContactActivity.this.getString(R.string.ip)+"/contactUs";
            RequestQueue queue = Volley.newRequestQueue(this);
            JSONObject jsonParams = new JSONObject();

            try {
                jsonParams.put("user_id", LoginActivity.userId_global);
                jsonParams.put("email", emailid);
                jsonParams.put("message", msg);

            }catch (JSONException x){
                x.printStackTrace();
            }
            String regex = "^(.+)@(.+)$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(emailid);
            if (!matcher.matches()) {
                Toast.makeText(ContactActivity.this, "Invalid email address",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST,url, jsonParams,
                    new Response.Listener<JSONObject>(){
                        public void onResponse(JSONObject response){
                            String str="";
                            try {
                                str = response.getString("result");

                                Toast.makeText(ContactActivity.this, "Message sent, Check your email for a response.",
                                        Toast.LENGTH_SHORT).show();
                                ContactActivity.this.finish();
                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                            Toast.makeText(ContactActivity.this, str,
                                    Toast.LENGTH_SHORT).show();
                        }
                    },new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(ContactActivity.this, "Error sending message.",
                            Toast.LENGTH_SHORT).show();
                    Log.d("VolleyError", error.toString());
                }
            });
            queue.add(request);

        });
        }


}