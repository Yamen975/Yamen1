package com.example.yamen;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Type extends AppCompatActivity {
    private ImageButton customer;
    private ImageButton barber;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_type);

        customer = findViewById(R.id.customer);
        customer.setOnClickListener(view -> {
            Intent intent = new Intent(this, CustomerSignUp.class);
            startActivity(intent);
        });
        barber = findViewById(R.id.barber);
        barber.setOnClickListener(view -> {
            Intent intent = new Intent(  this, BarberSignUp.class);
            startActivity(intent);
        });
    }


}
