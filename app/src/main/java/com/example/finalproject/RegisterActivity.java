package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class RegisterActivity extends AppCompatActivity {

   private EditText userid;
   private EditText user_name;
   private EditText pass1;
   private EditText pass2;
   private Button signupuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userid=findViewById(R.id.Userid);
        user_name=findViewById(R.id.Username);
        pass1=findViewById(R.id.crepass);
        pass2=findViewById(R.id.crepass2);
        signupuser = findViewById(R.id.signupbtn);

        signupuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID=userid.getText().toString();
                String userName=user_name.getText().toString();
                String passuser=pass1.getText().toString();
                String passuser2=pass2.getText().toString();

                if (userID.isEmpty() || userName.isEmpty() || passuser.isEmpty() || passuser2.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!passuser.equals(passuser2)) {
                    Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!userID.matches("\\d+")) {
                    Toast.makeText(RegisterActivity.this, "UserID must contain only numbers", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (userID.length() != 7 ) {
                    Toast.makeText(RegisterActivity.this, "User id must be 8 digits", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (passuser.length() < 8) {
                    Toast.makeText(RegisterActivity.this, "Password must be at least 8 characters long", Toast.LENGTH_SHORT).show();
                    return;
                }

                addUser(userID, userName, passuser);

            }
        });
    }
    public void addUser(String userid,String username,String password){
        String url="http://10.0.2.2:5000/add";
        RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
        JSONObject jsonParams = new JSONObject();

        try {
            jsonParams.put("user_id", userid);
            jsonParams.put("user_name", username);
            jsonParams.put("password", password);

        }catch (JSONException e){
            e.printStackTrace();
        }
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST,url, jsonParams,
                new Response.Listener<JSONObject>(){
                    public void onResponse(JSONObject response){
                        String str="";
                        try {
                            str = response.getString("result");
                            Intent intent=new Intent(RegisterActivity.this,HomeActivity.class);
                            intent.putExtra("user_name", username);
                            startActivity(intent);
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                        Toast.makeText(RegisterActivity.this, str,
                                Toast.LENGTH_SHORT).show();
                    }
                },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this, "This user is registered",
                        Toast.LENGTH_SHORT).show();
                Log.d("VolleyError", error.toString());
            }
        });
        queue.add(request);
    }

}