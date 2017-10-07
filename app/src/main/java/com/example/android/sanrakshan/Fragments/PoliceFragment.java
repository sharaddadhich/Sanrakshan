package com.example.android.sanrakshan.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.sanrakshan.Adapters.PoliceAdapter;
import com.example.android.sanrakshan.Models.Police;
import com.example.android.sanrakshan.R;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by HP on 07-Oct-17.
 */

public class PoliceFragment extends Fragment {

    Context ctx;
    ArrayList<Police> police;
    public static final String TAG = "Police";
    RecyclerView rvList;
    PoliceAdapter policeAdapter;
    ProgressDialog progressDialog;

    public PoliceFragment(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        police = new ArrayList<>();
        policeAdapter = new PoliceAdapter(ctx,police);
        progressDialog = new ProgressDialog(ctx);
        progressDialog.setMessage("Fetching your Location..");
        progressDialog.show();

        Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                progressDialog.cancel();
            }
        };
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {

            }
        });

        Handler handler = new Handler();
        handler.postDelayed(progressRunnable, 1000);



//        android.os.Handler handler = new android.os.Handler();
//
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                progressDialog.show();
//            }
//        }, 1000);
        fetchStations();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_police,container,false);
        Log.d(TAG, "onCreateView: ");

        rvList = (RecyclerView) rootView.findViewById(R.id.rvList);
        rvList.setLayoutManager(new LinearLayoutManager(ctx));
        rvList.addItemDecoration(new DividerItemDecoration(ctx,LinearLayoutManager.VERTICAL));
        rvList.setAdapter(policeAdapter);
        return  rootView;
    }


    void fetchStations(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=28.6652,77.2324&radius=5000&type=police&key=AIzaSyBgEqbEuZ8LJdG7BmDXn3frx89AK1IVd0c",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            for (int i=0;i<jsonArray.length();++i){
                                JSONObject loc = jsonArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location");
                                String name = jsonArray.getJSONObject(i).getString("name");
                                String address = jsonArray.getJSONObject(i).getString("vicinity");
                                String placeId = jsonArray.getJSONObject(i).getString("place_id");
                                LatLng latlng = new LatLng(loc.getDouble("lat"),loc.getDouble("lng"));
                                String dist = String.valueOf(round(distance(28.6652,77.2324,loc.getDouble("lat"),loc.getDouble("lng"),'K'),2)) + " km away";
                                Police item = new Police(name,address,placeId,latlng,dist);
                                police.add(item);
                            }
                            policeAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ctx, "Volley Error", Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(ctx);
        requestQueue.add(jsonObjectRequest);
    }

    public static final double distance(double lat1, double lon1, double lat2, double lon2, char unit)
    {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        if (unit == 'K') {
            dist = dist * 1.609344;
        }
        else if (unit == 'N') {
            dist = dist * 0.8684;
        }

        return (dist);
    }

    private static final double deg2rad(double deg)
    {
        return (deg * Math.PI / 180.0);
    }

    private static final double rad2deg(double rad)
    {
        return (rad * 180 / Math.PI);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }


}