package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Locale;

public class CalculateCostActivity extends AppCompatActivity {

    EditText hourlyRateEditText, hourEditText;
    TextView resultTextView, shekelRateTextView;
    double nisRate = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_cost);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_container, new NavigationBar())
                .commit();

        hourlyRateEditText = findViewById(R.id.hourlyRateEditText);
        hourEditText = findViewById(R.id.hoursEditText);
        resultTextView = findViewById(R.id.resultTextView);
        shekelRateTextView = findViewById(R.id.shekelRate);

        Button calculateButton = findViewById(R.id.calculateButton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CoinRateApi();
            }
        });


    }

    private void calculateCost() {
        try {
            double hourlyRate = Double.parseDouble(hourlyRateEditText.getText().toString());
            double hoursWorked = Double.parseDouble(hourEditText.getText().toString());

            // Calculate total cost in JD
            double totalCostJD = (hourlyRate * hoursWorked) + 70;

            // Display results
            resultTextView.setText(String.format(Locale.getDefault(), "Total Cost(JD): %.2f ", totalCostJD));
            shekelRateTextView.setText(String.format(Locale.getDefault(), "Total Cost(NIS): %.2f", (totalCostJD * nisRate)));

        } catch (NumberFormatException e) {
            resultTextView.setText("Invalid input. Please enter valid numbers.");
        }
    }

    private void CoinRateApi() {
        String url = "https://v6.exchangerate-api.com/v6/322f44514f19a32be5b7be57/latest/JOD";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject rates = response.getJSONObject("conversion_rates");

                    // Retrieve exchange rates
                    nisRate = rates.getDouble("ILS");
                    Log.d("CalculateCostActivity", "Shekel Rate: " + nisRate);
                    calculateCost();
                    Log.d("Response", response.toString());
                } catch (JSONException e) {
                    Log.d("json_error", e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("volley_error", error.toString());
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(request);
    }
}
