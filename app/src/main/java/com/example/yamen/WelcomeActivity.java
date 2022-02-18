package com.example.yamen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    private TextView textViewWelcome;
    private Button buttonBack;

    @Override

    protected void onCreate(Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        setContentView(R.layout.activity_welcome);

        textViewWelcome = findViewById(R.id.textViewWelcome);
        buttonBack = findViewById(R.id.buttonBack);
        String name= getIntent().getStringExtra( "name");
        textViewWelcome.setText("Welcome "+name);
    }

    public void back (View view){
        Intent intent= new Intent(this, Login.class);
        startActivity(intent);
    }
}