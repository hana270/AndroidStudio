package com.example.tp3ig;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class exercice2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice2);
 TextView zoneC = findViewById(R.id.zoneColore);
        Button btnR = findViewById(R.id.btnR);
        Button btnV = findViewById(R.id.btnV);
        Button btnB = findViewById(R.id.btnB);
        Button btnJ = findViewById(R.id.btnJ);

        btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int c=getResources().getColor(R.color.red);
                zoneC.setBackgroundColor(c);
            }
        });

        btnV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int c=getResources().getColor(R.color.green);
                zoneC.setBackgroundColor(c);
            }
        });
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int c=getResources().getColor(R.color.bleu);
                zoneC.setBackgroundColor(c);
            }
        });
        btnJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int c=getResources().getColor(R.color.yellow);
                zoneC.setBackgroundColor(c);
            }
        });



    }

}