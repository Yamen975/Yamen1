package com.example.yamen;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity implements View.OnLongClickListener ,DialogInterface.OnClickListener {
    private static final String TAG = "FireBase";
    private Intent musicintent;
    private Button loginc;
    private EditText editTextName;
    private EditText editTextPassword;
    private FirebaseAuth mAuth;//auth = authentication אימות

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();//a method that connects the firebase console with the project
        editTextName = findViewById(R.id.editTextName);
        editTextPassword = findViewById(R.id.editTextPassword);
        loginc = findViewById(R.id.login);

        //sets the OnClickListener on the wanted button
        loginc.setOnLongClickListener(this);

        SharedPreferences sp = getSharedPreferences("settings", MODE_PRIVATE);//creates a local file which saves the sp in it
        String email = sp.getString("email", "");//the email in the local file
        String password = sp.getString("password", "");//the password in the local file
        if (!email.equals("") && !password.equals("")) {//checks if the edit text is empty and if it is it
            // presets the email with the email that it saved from before
            editTextName.setText(email);
        }
        musicintent = new Intent(this,MusicService.class);
        //startService(musicintent);
        loginc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginfb(editTextName.getText().toString(), editTextPassword.getText().toString());
            }
        });

    }



    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);//object which interact with the user
        builder.setMessage("Are you sure you want to exit?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", this);
        builder.setNegativeButton("No", this);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    //the dialog interface is implemented in the top of the file that way he knows the on click is for the dialog
    public void onClick(DialogInterface dialog, int which) {
        if (which == dialog.BUTTON_POSITIVE) {//did the user press positive
            super.onBackPressed();
            dialog.cancel();
        }
        if (which == dialog.BUTTON_NEGATIVE) {//did the user press negative
            dialog.cancel();
        }
    }

    public void login() {
        //saving email of user in a local file (settings) for future use
        //create file if not found
        SharedPreferences sp = getSharedPreferences("settings", MODE_PRIVATE);
        //open editor for editing
        SharedPreferences.Editor editor = sp.edit();
        //write the wanted settings
        editor.putString("email", editTextName.getText().toString());
        editor.putString("password", editTextPassword.getText().toString());
        //saves and closes file
        editor.commit();


        //       loginfb(editTextTextPersonName.getText().toString(),editTextTextPassword.getText().toString());
    }

    public void signup(View view) {
        Intent intent = new Intent(this, Type.class);
        startActivity(intent);
    }

    @Override
    public boolean onLongClick(View view) {
        editTextName.setText("");
        editTextPassword.setText("");
        return true;
    }

    public void loginfb(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)//a method that is premade in the fire base that checks the
                // email and password of the user and signs him in accordingly
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    //OnCompleteListener waits for the firebase
                    // Signin task to complete and returns
                    // a task which we check if it was made successfully
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            Intent i = new Intent(Login.this,WelcomeActivity.class);
                            FirebaseUser user = mAuth.getCurrentUser();
                            login();
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());//a description of the error for the task that wasn't successful
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

}