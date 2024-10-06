package com.example.tp2ig;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.couleur);

        Button b1 = findViewById(R.id.btn1);
        Button b2 = findViewById(R.id.btn2);
        Button b3 = findViewById(R.id.btn3);
        Button b4 = findViewById(R.id.btn4);
        TextView aff = findViewById(R.id.aff);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int c = Color.RED;
                aff.setBackgroundColor(c);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int c = Color.BLUE;
                aff.setBackgroundColor(c);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int c = Color.YELLOW;
                aff.setBackgroundColor(c);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int c = Color.GREEN;
                aff.setBackgroundColor(c);
            }
        });
    }
}