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
import com.google.android.gms.auth.api.signin.*;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class Activity_Login extends AppCompatActivity {

    private static final int RC_SIGN_IN = 100;

    TextView register;
    EditText email, password;
    FirebaseAuth mAuth;
    ProgressBar pgB;
    Button loginbtn;

    GoogleSignInClient googleSignInClient;

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

        // Configure Google Sign-In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)) // Replace with your Web Client ID
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Femail = email.getText().toString();
                String Fpassword = password.getText().toString();

                if (TextUtils.isEmpty(Femail)) {
                    email.setError("Enter email");
                    email.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Fpassword)) {
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

        // Google Sign-In button
        findViewById(R.id.google_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInWithGoogle();
            }
        });
    }

    private void signInWithGoogle() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) {
                    firebaseAuthWithGoogle(account);
                }
            } catch (ApiException e) {
                Toast.makeText(this, "Google Sign-In failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(Activity_Login.this, "Authentication Successful!", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Activity_Login.this, Activity_Home.class);
                            startActivity(i);
                            finish();
                        } else {
                            Toast.makeText(Activity_Login.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
