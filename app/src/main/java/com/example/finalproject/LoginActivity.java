package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userid=findViewById(R.id.iduser);
        password=findViewById(R.id.pass);
        check=findViewById(R.id.Remember);
        signup=findViewById(R.id.signbtn);
        Login=findViewById(R.id.loginbtn);



//        Login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity.this, MapActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();

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
                 finish();
             }

         }
     });

    }

    public void checkUser(String idUser,String userpass){
        String url="http://10.0.2.2:5000/login";
        RequestQueue queue = Volley.newRequestQueue(this);
        JSONObject postData = new JSONObject();
        try {
            postData.put("user_id", idUser);
            postData.put("password", userpass);
        }catch (JSONException e){

        }
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST,url,postData,response -> {
            Log.d("LoginResponse", response.toString());
            try {
                boolean success = response.getBoolean("success");
                if (success){//200
                    //String user_name = response.getString("user_name");
                    Intent intent=new Intent(LoginActivity.this,FirstPageActivity.class);
                    //intent.putExtra("user_name", user_name);
                    startActivity(intent);

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




}