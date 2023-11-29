package com.example.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;


import com.example.myapplication.classes.sqliteHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpActivity extends AppCompatActivity {

    TextInputEditText name, email, pass;
    TextView toLogin;
    FirebaseAuth auth;
    Toolbar toolbar;
    CircularProgressIndicator indicator;
    ConstraintLayout signUp;
    TextView loading, clicked;
    sqliteHelper sqliteHelperObj;
    String userEmail, userPass, userName;

    DatabaseReference reference;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name = findViewById(R.id.nameSign);
        email = findViewById(R.id.emailSign);
        pass = findViewById(R.id.passwordSign);
        signUp = findViewById(R.id.btnSignUp2);
        toLogin = findViewById(R.id.alreadyAccount);
        indicator = findViewById(R.id.progressSignUp);
        loading = findViewById(R.id.loadingSignUp);
        clicked = findViewById(R.id.clickedSignUp);
        auth = FirebaseAuth.getInstance();
        toolbar = findViewById(R.id.toolbarSignup);
        setSupportActionBar(toolbar);
        reference = FirebaseDatabase.getInstance().getReference();




        indicator.setVisibility(View.INVISIBLE);
        loading.setVisibility(View.INVISIBLE);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indicator.setVisibility(View.VISIBLE);
                loading.setVisibility(View.VISIBLE);
                clicked.setVisibility(View.INVISIBLE);
                 userEmail = email.getText().toString();
                 userPass = pass.getText().toString();
                  userName = name.getText().toString();
                authentication(userEmail, userPass, userName);

            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void authentication(String userEmail, String userPass, String userName) {
        auth.createUserWithEmailAndPassword(userEmail, userPass)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            reference.child("users").child(auth.getUid()).child("name").setValue(userName);
                            reference.child("users").child(auth.getUid()).child("email").setValue(userEmail);
                            Toast.makeText(SignUpActivity.this, "successfully sign up", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(SignUpActivity.this, MainActivity.class);
                            i.putExtra("name", userName);
                            i.putExtra("email", userEmail);
                            startActivity(i);
                            finish();
                        }
                        else
                            Toast.makeText(SignUpActivity.this, "Failed to sign up", Toast.LENGTH_SHORT).show();


                        indicator.setVisibility(View.INVISIBLE);
                        loading.setVisibility(View.INVISIBLE);
                        clicked.setVisibility(View.VISIBLE);
                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(i);
    }
}