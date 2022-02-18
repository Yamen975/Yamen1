package com.example.yamen;
import android.location.Location;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Customer {


    private String name;
    private boolean hasApp;
    private Appointment appointment;
    private boolean isBook;
    private double longitude,latitude;

    public Customer(){


    }
    public Customer(String name){
        this.name=name;
        this.hasApp=false;
        this.appointment=null;
        this.isBook=false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHasApp() {
        return hasApp;
    }

    public void setHasApp(boolean hasApp) {
        this.hasApp = hasApp;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public boolean bookApp(BarberShop barberShop){
        if(!this.isBook) {
            Appointment appointment = new Appointment(barberShop, java.time.LocalTime.now().toString(), java.time.LocalDate.now().toString());
            if (barberShop.addAppointment(appointment)) {
                this.isBook = true;
                return true;
            }
        }
        return false;
    }

    public boolean cancel(){
        if (isBook){
            if(this.appointment.getBarbershop().cancelApp(this.appointment)){
                isBook=false;
                return true;
            }
            else
                return false;
        }
        return true;
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
