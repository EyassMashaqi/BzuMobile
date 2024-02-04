package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import org.json.JSONArray;
import org.json.JSONException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class CalendarActivity extends AppCompatActivity {
    private RequestQueue queue;
    ArrayList<event> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        events = new ArrayList<>();
        queue = Volley.newRequestQueue(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_container, new NavigationBar())
                .commit();

        CompactCalendarView compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendarView.performClick();
        compactCalendarView.setHorizontalScrollBarEnabled(false);
        compactCalendarView.setVerticalScrollBarEnabled(false);

        TextView textViewMonthYear = findViewById(R.id.textViewMonthYear);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());
        String formattedDate = sdf.format(calendar.getTime());
        textViewMonthYear.setText(formattedDate);

        compactCalendarView.setEnabled(false);
        compactCalendarView.setOnScrollChangeListener(null);
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> events = compactCalendarView.getEvents(dateClicked);
                if (events != null && !events.isEmpty()) {
                    showEventInformationDialog(events);
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());
                textViewMonthYear.setText(sdf.format(firstDayOfNewMonth));
            }
        });
        getEvents();
    }

    public void getEvents() {
        String url = "http://10.0.2.2:5000/events";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONArray eventData = response.getJSONArray(i);

                        String eventType = eventData.getString(0);
                        String dateString = eventData.getString(1);
                        Date eventDate = parseDate(dateString);

                        event currentEvent = new event(eventType, eventDate);
                        events.add(currentEvent);

                        CompactCalendarView compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);

                        com.github.sundeepk.compactcalendarview.domain.Event ev1 = new com.github.sundeepk.compactcalendarview.domain.Event(Color.RED, eventDate.getTime(), currentEvent.getEvent_name());
                        compactCalendarView.addEvent(ev1);
                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("volley_error", "Error: " + error.toString());
            }
        });
        queue.add(request);
    }

    private Date parseDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static long convertDateToEpoch(Date date) {
        return date.getTime();
    }

    private void showEventInformationDialog
            (List<com.github.sundeepk.compactcalendarview.domain.Event> events) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        StringBuilder eventInfo = new StringBuilder();
        for (com.github.sundeepk.compactcalendarview.domain.Event event : events) {
            String eventData = String.valueOf(event.getData());
            eventInfo.append(eventData).append("\n");
        }
        builder.setMessage(eventInfo.toString());
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

}