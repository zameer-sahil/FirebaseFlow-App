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

import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class resetPassActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextInputEditText emailResetPass;
    ConstraintLayout btnResetPass;
    FirebaseAuth auth;
    CircularProgressIndicator indicator;
    TextView loading, clicked;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("");
        setContentView(R.layout.activity_reset_pass);
        toolbar = findViewById(R.id.toolbarResetPassword);
        emailResetPass = findViewById(R.id.emailResetPass);
        btnResetPass = findViewById(R.id.buttonResetPass);
        indicator = findViewById(R.id.progressResetPass);
        loading = findViewById(R.id.loadingResetPass);
        clicked = findViewById(R.id.clicked);
        setSupportActionBar(toolbar);
        auth = FirebaseAuth.getInstance();
        indicator.setVisibility(View.INVISIBLE);
        loading.setVisibility(View.INVISIBLE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(resetPassActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indicator.setVisibility(View.VISIBLE);
                loading.setVisibility(View.VISIBLE);
                clicked.setVisibility(View.INVISIBLE);
                String email = emailResetPass.getText().toString();
                authentication(email);
            }
        });

    }

    private void authentication(String email) {
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(resetPassActivity.this, "successfully password has been sent", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(resetPassActivity.this, "Failed", Toast.LENGTH_SHORT).show();

                        indicator.setVisibility(View.INVISIBLE);
                        loading.setVisibility(View.INVISIBLE);
                        clicked.setVisibility(View.VISIBLE);
                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(resetPassActivity.this, LoginActivity.class);
        startActivity(i);
    }
}