package com.example.yamen;

import android.location.Location;

public class LocationHelper {
    private double Longitude;
    private double Latitude;

    private Location loc;
    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public LocationHelper(double longitude, double latitude) {
        Longitude = longitude;
        Latitude = latitude;
    }
}
