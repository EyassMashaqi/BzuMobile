package com.example.finalproject;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;

public class MapActivity extends AppCompatActivity {

    public MapView map = null;
    private ListView filterList;
    private ArrayList<AllMarks> allMarks;
    private final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;
    private double userLat, userLon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //create map and put all markers on it
        //make enum class, with list of markers,with name to easy fetching from database
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_container, new NavigationBar())
                .commit();
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.gre));

        Context ctx = this.getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        try {
            setContentView(R.layout.activity_map);
        } catch (Exception e) {
            //  e.printStackTrace();
        }


        map = findViewById(R.id.mapview);

        filterList = findViewById(R.id.filterList);

        map.setTileSource(TileSourceFactory.MAPNIK);
        map.getController().setZoom(18.0);

        requestPermissionsIfNecessary(new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.INTERNET
        });
        map.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.ALWAYS);
        map.setMultiTouchControls(true);


        CompassOverlay compassOverlay = new CompassOverlay(this, map);
        compassOverlay.enableCompass();
        map.getOverlays().add(compassOverlay);

        GeoPoint point = new GeoPoint(31.9589, 35.1814);

        Marker startMarker = new Marker(map);
        startMarker.setPosition(point);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER);


        map.getController().setCenter(point);
        allMarks = new ArrayList<>();


        String url = url=MapActivity.this.getString(R.string.ip)+"/marker";
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    public void onResponse(JSONArray response) {
                        String str = "";
                        try {
                            str = response.getString(0);
                            for (int i = 0; i < response.length(); i++) {
                                JSONArray temp = response.getJSONArray(i);
                                String type = temp.getString(1);
                                boolean isExist = false;
                                for (AllMarks mark : allMarks) {
                                    if (mark.getType().equals(type)) {
                                        isExist = true;
                                    }
                                }
                                if (!isExist) {
                                    Log.d("newone", type);
                                    AllMarks tempMark = new AllMarks(map, type);
                                    allMarks.add(tempMark);
                                }
                            }
                            for (int i = 0; i < response.length(); i++) {
                                JSONArray temp = response.getJSONArray(i);
                                String type = temp.getString(1);
                                double lat = temp.getDouble(2);
                                double lon = temp.getDouble(3);
                                String name = temp.getString(0);
                                // AllMarks tempMark = new AllMarks(map,type);
                                //search for the type if in the list
                                AllMarks tempMark = null;
                                for (AllMarks mark : allMarks) {
                                    if (mark.getType().equals(type)) {
                                        tempMark = mark;
                                        break;
                                    }
                                }
                                if (tempMark == null) {
                                    tempMark = new AllMarks(map, "NA");
                                    allMarks.add(tempMark);
                                }
                                if (type.equals("Other"))
                                    type = "";
                                tempMark.addMarker(lat, lon, name + " " + type, type);
                            }
                            ArrayAdapter<AllMarks> adapter = new ArrayAdapter<>(MapActivity.this, android.R.layout.simple_list_item_1, allMarks);
                            filterList.setAdapter(adapter);
                            Log.d("size_marker", allMarks.size() + "");
                            filterList.setOnItemClickListener((parent, view, position, id) -> {
                                AllMarks tempMarker = (AllMarks) parent.getItemAtPosition(position);
                                tempMarker.toggleMarker(tempMarker.getMarkers());
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MapActivity.this, "error fetching data ",
                        Toast.LENGTH_SHORT).show();
                Log.d("VolleyError", error.toString());
            }
        });
        queue.add(request);
        final Handler handler = new Handler();
        MyLocationNewOverlay mLocationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(this), map);
        mLocationOverlay.enableMyLocation();

        handler.post(new Runnable() {
            @Override
            public void run() {


        userLat=31.958198;
        userLon=35.184281;
                if (31.964218 > userLat && 32 > userLat && 35.187734 > userLon && userLon > 35.176713) {
                    map.getOverlays().add(mLocationOverlay);
            //new geo point for demo
                    GeoPoint userPoint = new GeoPoint(userLat, userLon);
                    Marker userMarker = new Marker(map);
                    userMarker.setPosition(userPoint);
                   // userMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER);
                    userMarker.setIcon(getResources().getDrawable(org.osmdroid.library.R.drawable.person));
                    userMarker.setTitle("You are here");
                    map.getOverlays().add(userMarker);
            //map.getController().setCenter(userPoint);
                    Log.d("location", "in the area");
                } else
                    Toast.makeText(MapActivity.this, "You are not in the University area",
                            Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            permissionsToRequest.add(permissions[i]);
        }
        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    this,
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    private void requestPermissionsIfNecessary(String[] permissions) {
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                permissionsToRequest.add(permission);
            }
        }
        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    this,
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }
}