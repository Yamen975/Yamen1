package com.example.yamen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.FileNotFoundException;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG ="FIREBASE";
    private EditText password;
    private EditText emails;
    private static final int CAMERA_REQUEST =0;
    private static final int GALLERY_REQUEST =1;
    private ImageButton buttonCamera,buttonGallery;
    private ImageView imageViewProfile;
    private Bitmap picture;
    private FirebaseAuth mAuth;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        password = findViewById(R.id.editTextPassword);
        emails=findViewById(R.id.editEditTextEmail);
        buttonCamera=findViewById(R.id.buttonCamera);
        buttonCamera.setOnClickListener(this);
        buttonGallery=findViewById(R.id.buttonGallery);
        buttonGallery.setOnClickListener(this);
        imageViewProfile=findViewById(R.id.imageViewProfile);
    }

    public void signUp(View view) {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }
    public void onClick(View view) {
        if(view.getId()==R.id.buttonCamera){
            Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i,CAMERA_REQUEST);
        }
        else{
            Intent i=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i,GALLERY_REQUEST);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode==CAMERA_REQUEST){
            if(resultCode==RESULT_OK){
                picture=(Bitmap) data.getExtras().get("data");
                imageViewProfile.setImageBitmap(picture);
            }
        }
        else {
            if(resultCode==RESULT_OK){
                Uri targetUri=data.getData();
                try{
                    picture= BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                    imageViewProfile.setImageBitmap(picture);
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }
            }

        }
    }
    public void submit(View view){
        signUp(emails.getText().toString(),password.getText().toString());
    }

    public void signUp(String email,String password){

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i = new Intent (SignUpActivity.this, MainActivity.class);
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

    }

}

