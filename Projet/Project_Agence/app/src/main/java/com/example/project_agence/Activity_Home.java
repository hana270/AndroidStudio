package com.example.project_agence;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Activity_Home extends AppCompatActivity {
    TextView textview;
     FirebaseAuth auth;

    FirebaseUser user;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

       auth =FirebaseAuth.getInstance();
       button = findViewById(R.id.logout);
       textview = findViewById(R.id.textView);
       user = auth.getCurrentUser();
       if(user == null){
           Intent i = new Intent(Activity_Home.this,Activity_Login.class);
           startActivity(i);
           finish();
       }else{
           textview.setText(user.getEmail());
       }
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        FirebaseAuth.getInstance().signOut();

        Intent i = new Intent(Activity_Home.this,Activity_Login.class);
        startActivity(i);
        finish();
    }
});




    }
}