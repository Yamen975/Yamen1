package com.example.yamen;

import android.location.Location;

public class Barber {

    private String name;
    private String location;
    private String picture;

    public Barber(){

    }

    public Barber(String name,String location){
        this.name=name;
        this.location=location;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }


}
