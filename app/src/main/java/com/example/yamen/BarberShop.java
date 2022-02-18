package com.example.yamen;


import android.graphics.Bitmap;
import android.location.Location;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class BarberShop {
    private String name;
    private double longitude,latitude;
    private ArrayList<String> workhours;
    private ArrayList<Appointment> appointments;
    private boolean isAvilable;
    private String pic;




    public BarberShop(String name, double longitude,double latitude){
        this.longitude=longitude;
        this.latitude=latitude;
        this.name=name;
        this.appointments= new ArrayList<>();
        this.isAvilable=false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void bitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] arr=baos.toByteArray();
        this.pic= Base64.encodeToString(arr, Base64.DEFAULT);
    }



    public void open(){
        this.isAvilable=true;
    }

    public void close(){
        this.appointments.clear();
        this.isAvilable=false;
    }

    public boolean addAppointment(Appointment appointment){
        if(!this.isAvilable)
            return false;
        this.appointments.add(appointment);
        return true;
    }

    public boolean cancelApp(Appointment appointment){
        int i=0;
        while(this.appointments.get(i)!=null) {
            if (this.appointments.get(i) == appointment) {
                this.appointments.remove(i);
                return true;
            }
        }
        return false;
    }
    public float[] distance(Customer customer){
        double l1,l2,lo1,lo2,dis;
        float [] result = new float[1];
        l1=this.latitude;
        l2=customer.getLatitude();
        lo1=this.longitude;
        lo2=customer.getLongitude();

        Location.distanceBetween(l1,lo1,l2,lo2,result);
        return result;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
