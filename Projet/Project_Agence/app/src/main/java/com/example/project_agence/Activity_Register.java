package com.example.project_agence;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Activity_Register extends AppCompatActivity {
    TextView login;
    EditText username, email, password, ConfPassword,phone_number;
    Button btnRegister;
    FirebaseAuth mAuth;
    ProgressBar pgB;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent i = new Intent(Activity_Register.this, Activity_Home.class);
            startActivity(i);
            finish();
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();

        login = findViewById(R.id.loginn);
        username = findViewById(R.id.username_field);
        email = findViewById(R.id.email_field);
        password = findViewById(R.id.password_field);
        ConfPassword = findViewById(R.id.confirm_password_field);
        btnRegister = findViewById(R.id.signup_button);
        phone_number = findViewById(R.id.phone_number);

        pgB = findViewById(R.id.progressbar);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pgB.setVisibility(View.VISIBLE);
                String Fusername, Femail, Fpassword, FconfPassword,Fphone_number;

                Fusername = username.getText().toString();
                Femail = email.getText().toString();
                Fpassword = password.getText().toString();
                FconfPassword = ConfPassword.getText().toString();
                Fphone_number = phone_number.getText().toString();
                // Validation des champs
                if (TextUtils.isEmpty(Fusername)) {
                    Toast.makeText(Activity_Register.this, "Enter username", Toast.LENGTH_LONG).show();
                    pgB.setVisibility(View.GONE);
                    return;
                }

                if (TextUtils.isEmpty(Femail)) {
                    Toast.makeText(Activity_Register.this, "Enter email", Toast.LENGTH_LONG).show();
                    pgB.setVisibility(View.GONE);
                    return;
                }

                if (TextUtils.isEmpty(Fpassword)) {
                    Toast.makeText(Activity_Register.this, "Enter password", Toast.LENGTH_LONG).show();
                    pgB.setVisibility(View.GONE);
                    return;
                }
                if (TextUtils.isEmpty(Fphone_number)) {
                    Toast.makeText(Activity_Register.this, "Enter phone number", Toast.LENGTH_LONG).show();
                    pgB.setVisibility(View.GONE);
                    return;
                }

                if (Fpassword.length() < 6) {
                    Toast.makeText(Activity_Register.this, "Password must be at least 6 characters", Toast.LENGTH_LONG).show();
                    pgB.setVisibility(View.GONE);
                    return;
                }

                if (!Fpassword.equals(FconfPassword)) {
                    Toast.makeText(Activity_Register.this, "Passwords do not match", Toast.LENGTH_LONG).show();
                    pgB.setVisibility(View.GONE);
                    return;
                }

                // Création de l'utilisateur
                mAuth.createUserWithEmailAndPassword(Femail, Fpassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                pgB.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(Activity_Register.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(Activity_Register.this, Activity_Login.class);
                                    startActivity(i);
                                    finish();
                                } else {
                                    Toast.makeText(Activity_Register.this, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activity_Register.this, Activity_Login.class);
                startActivity(i);
                finish();
            }
        });
    }
}
