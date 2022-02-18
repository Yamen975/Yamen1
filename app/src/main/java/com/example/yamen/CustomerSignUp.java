package com.example.yamen;

import static androidx.constraintlayout.motion.widget.Debug.getLocation;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


public class CustomerSignUp extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG ="FIREBASE";
    private EditText password,emails,name;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private FirebaseAuth mAuth;
    private Button btLocation,signUp,login;
    private String loc;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_sign_up);
        name = findViewById(R.id.editTextName);
        password = findViewById(R.id.editEditTextPassword);
        emails=findViewById(R.id.editEditTextEmail);
        mAuth = FirebaseAuth.getInstance();
        signUp= findViewById(R.id.signUpButton);
        signUp.setOnClickListener(this);
        login = findViewById(R.id.toLogin);
        login.setOnClickListener(view -> {
            Intent i= new Intent(this,Login.class);
            startActivity(i);
        });


    }


    public void signUp(String email, String password,String name,String loc){

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Barber barber= new Barber(name,loc);
                            FirebaseDatabase.getInstance().getReference("Barber")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(barber).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(CustomerSignUp.this,"Barber has been registered successfully",Toast.LENGTH_LONG).show();
                                    }
                                }

                            });
                            Intent i = new Intent(CustomerSignUp.this, Login.class);
                            startActivity(i);
                        }else{
                            Log.d("ERROR", task.getException().toString());
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        signUp(emails.getText().toString(),password.getText().toString(),name.getText().toString(),loc);
    }
}
