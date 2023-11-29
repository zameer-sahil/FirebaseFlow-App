package com.example.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;


public class LoginActivity extends AppCompatActivity {

    TextView gotoSignUp, gotoResetPass;
    TextInputEditText email, pass;
    ConstraintLayout login;
    Toolbar toolbar;
    FirebaseAuth auth;
    CircularProgressIndicator indicator;
    TextView loading, clicked;
    String userEmail, userPass;
    FirebaseUser user;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        gotoSignUp = findViewById(R.id.gotoSignUp);
        gotoResetPass = findViewById(R.id.gotoResetPass);
        login = findViewById(R.id.buttonLogin);
        email = findViewById(R.id.emailLogin);
        pass = findViewById(R.id.passLogin);
        auth = FirebaseAuth.getInstance();
        indicator = findViewById(R.id.progressLogin);
        loading = findViewById(R.id.loadingLogin);
        clicked = findViewById(R.id.clickedLogin);
        toolbar = findViewById(R.id.toolbarLogin);
        setSupportActionBar(toolbar);
        indicator.setVisibility(View.INVISIBLE);
        loading.setVisibility(View.INVISIBLE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        gotoSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });

        gotoResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, resetPassActivity.class);
                startActivity(i);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indicator.setVisibility(View.VISIBLE);
                loading.setVisibility(View.VISIBLE);
                clicked.setVisibility(View.INVISIBLE);
                 userEmail = email.getText().toString();
                 userPass = pass.getText().toString();
                authentication(userEmail, userPass);
            }
        });
    }

    private void authentication(String userEmail, String userPass) {
        auth.signInWithEmailAndPassword(userEmail, userPass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(LoginActivity.this, "successfully login", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            i.putExtra("emailLogin", userEmail);
                            startActivity(i);
                            finish();
                        }
                        else
                            Toast.makeText(LoginActivity.this, "Failed to login", Toast.LENGTH_SHORT).show();

                        indicator.setVisibility(View.INVISIBLE);
                        loading.setVisibility(View.INVISIBLE);
                        clicked.setVisibility(View.VISIBLE);
                    }
                });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        System.exit(0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        user = auth.getCurrentUser();
        if (user!=null)
        {
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }
}