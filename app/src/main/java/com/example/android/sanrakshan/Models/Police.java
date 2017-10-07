package com.example.android.sanrakshan.Models;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by HP on 07-Oct-17.
 */

public class Police {
    String name;
    String address;
    String placeId;
    LatLng latLng;
    String distance;


    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public Police(String name, String address, String placeId, LatLng latLng, String distance) {
        this.name = name;
        this.address = address;
        this.placeId = placeId;
        this.latLng = latLng;
        this.distance = distance;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }


}
