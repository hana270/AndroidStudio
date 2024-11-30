package com.example.project_agence;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Activity_Login extends AppCompatActivity {
    TextView register;
    EditText email, password;
    FirebaseAuth mAuth;
    ProgressBar pgB;
    Button loginbtn;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent i = new Intent(Activity_Login.this, Activity_Home.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.email_field);
        password = findViewById(R.id.password_field);
        loginbtn = findViewById(R.id.login_button);
        pgB = findViewById(R.id.progressbar);
        register = findViewById(R.id.registrr);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Femail = email.getText().toString();
                String Fpassword = password.getText().toString();

                if (TextUtils.isEmpty(Femail)) {
                    pgB.setVisibility(View.GONE);
                    email.setError("Enter email");
                    email.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Fpassword)) {
                    pgB.setVisibility(View.GONE);
                    password.setError("Enter password");
                    password.requestFocus();
                    return;
                }

                pgB.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(Femail, Fpassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                pgB.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(Activity_Login.this, "Authentication Successful.", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(Activity_Login.this, Activity_Home.class);
                                    startActivity(i);
                                    finish();
                                } else {
                                    Toast.makeText(Activity_Login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activity_Login.this, Activity_Register.class);
                startActivity(i);
                finish();
            }
        });
    }
}
