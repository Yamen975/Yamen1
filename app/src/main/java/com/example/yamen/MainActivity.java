package com.example.yamen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener ,DialogInterface.OnClickListener{


    private Button login;
    private EditText editTextName;
    private EditText editTextPassword;
    private Button clear;
    private TextView signUp;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}

