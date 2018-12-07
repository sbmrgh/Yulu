package com.example.sbmrg.yulu;

import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutput;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    String json;
    private GoogleMap mMap;
    String[] image = new String[8];
    String[] longitude = new String[8];
    String[] latitude = new String[8];
    String[] title = new String[8];
    private ArrayList<String> mTitle = new ArrayList<>();
    private ArrayList<String> mImage = new ArrayList<>();
    private ArrayList<String> Mlocation = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

       // Toast.makeText(this, "on create", Toast.LENGTH_SHORT).show();


    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //Toast.makeText(this, "on map ready 1", Toast.LENGTH_LONG).show();

        Resources res = getResources();
        InputStream inputStream = res.openRawResource(R.raw.data);
        Scanner scanner = new Scanner(inputStream);
        StringBuilder builder = new StringBuilder();
        while (scanner.hasNextLine()) {
            builder.append(scanner.nextLine());
        }

       // Toast.makeText(this, builder.toString(), Toast.LENGTH_SHORT).show();


        StringBuilder stringBuilder = new StringBuilder();
        try {
            JSONObject root = new JSONObject(builder.toString());
            JSONArray data = root.getJSONArray("data");

            for (int i = 0; i < data.length(); i++) {
                JSONObject location = data.getJSONObject(i);
                image[i] = location.getString("image");
                latitude[i] = location.getString("latitude");
                longitude[i] = location.getString("longitude");
                title[i] = location.getString("title");
                mImage.add(image[i]);
                mTitle.add(title[i]);
                Mlocation.add(latitude[i]+" , "+longitude[i]);
               // Toast.makeText(this, image[i]+ "\n" +latitude[i]+ "\n" +longitude[i]+ "\n" +title[i], Toast.LENGTH_SHORT).show();
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        //goToLocationZoom(39.008224,-76.8984527,15);
        //Add a marker in Sydney and move the camera
        LatLng bangalore = new LatLng(Double.parseDouble(latitude[0]),Double.parseDouble(longitude[0]));
        for (int i = 0; i < 8; i++) {

            //mMap.addMarker(new MarkerOptions().position(sydney).title("yulu 8"));
            // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 20F));

            MarkerOptions options = new MarkerOptions()
             .title(title[i])
             .position(new LatLng(Double.parseDouble(latitude[i]), Double.parseDouble(longitude[i])));
            mMap.addMarker(options);

        }
        Toast.makeText(this, "inside ", Toast.LENGTH_SHORT).show();

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bangalore,15F));
        initRecyclerView();


    }

    private void initRecyclerView() {

        Toast.makeText(this, "inside Recycler view", Toast.LENGTH_SHORT).show();
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        RecyclerView recyclerView= findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerViewAdapter adapter =new RecyclerViewAdapter(mTitle,mImage,Mlocation,this);
        recyclerView.setAdapter(adapter);
        Toast.makeText(this, "inside Recycler view", Toast.LENGTH_SHORT).show();
    }
}
