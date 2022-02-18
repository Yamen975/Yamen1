package com.example.yamen;

import java.sql.Time;

public class Appointment {
    private BarberShop barbershop;
    private String time;
    private String date;

    public BarberShop getBarbershop() {
        return barbershop;
    }

    public void setBarbershop(BarberShop barbershop) {
        this.barbershop = barbershop;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Appointment(BarberShop barbershop, String time, String date) {
        this.barbershop = barbershop;
        this.time = time;
        this.date = date;
    }
}
