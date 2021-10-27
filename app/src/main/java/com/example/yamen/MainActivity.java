package com.example.yamen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener ,DialogInterface.OnClickListener{


    private static final String TAG ="FIREBASE";
    private Button login;
    private EditText editTextName;
    private EditText editTextPassword;
    private Button clear;
    private TextView signUp;
    private FirebaseAuth mAuth;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        editTextName=findViewById((R.id.editTextName));
        editTextPassword= findViewById((R.id.editTextPassword));
        login=findViewById(R.id.login);
        clear=findViewById(R.id.clear);
        clear.setOnLongClickListener(this);
        signUp=findViewById(R.id.signup);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openA();
            }
        });

        SharedPreferences sp=getSharedPreferences("settings",MODE_PRIVATE);
        String email=sp.getString("email","");
        String password=sp.getString("password","");

        if(!email.equals("")&&!password.equals("")){
            editTextName.setText(email);
            editTextPassword.setText(password);
        }
    }

    public void openA(){
        Intent intent=new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }



    public void login(View view){
        Intent intent = new Intent(  this, ArrayListActivity.class);
        intent.putExtra("name",editTextName.getText().toString());
        String pass = editTextPassword.getText().toString();
        String upperCaseChars = "(.*[A-Z].*)";
        String lowerCaseChars = "(.*[a-z].*)";
        String numbers = "(.*[0-9].*)";
        //  if(pass.length()>=8 && pass.contains(upperCaseChars) && pass.contains(numbers) && pass.contains(lowerCaseChars)) {
        if (editTextName.getText().toString().contains("@") && editTextName.getText().toString().contains(".")) {
            SharedPreferences sp = getSharedPreferences("settings",MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("email",editTextName.getText().toString());
            editor.putString("password",editTextPassword.getText().toString());
            editor.commit();
            intent.putExtra("name",editTextName.getText().toString());
            startActivity(intent);
        }
       // login(editTextName.getText().toString(),editTextPassword.getText().toString());
        //startActivity(intent);
//       }
    }




    public void signup(View view){
        Intent intent = new Intent(  this, SignUpActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onLongClick(View view) {
        editTextName.setText("");
        editTextPassword.setText("");
        return true;
    }


        public void onBackPressed(){
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setMessage("Are you sure?");

            builder.setCancelable(false);

            builder.setPositiveButton("Yes",this);
            builder.setNegativeButton("No",this);

            AlertDialog dialog=builder.create();
            dialog.show();
        }

    @Override
    public void onClick(DialogInterface dialog, int i) {


        if(i==dialog.BUTTON_POSITIVE){
            super.onBackPressed();
            dialog.cancel();
        }
        if(i==dialog.BUTTON_NEGATIVE)
            dialog.cancel();
    }

    public void login(String email,String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i=new Intent(MainActivity.this, MainActivity2.class);
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }


}

