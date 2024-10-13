package com.example.tp2ig;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class fontstyle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layoutstyle);


        Button btnTextsize = findViewById(R.id.textsize);
        TextView btnitalic = findViewById(R.id.italic);
        TextView btnBold = findViewById(R.id.bold);
        LinearLayout l = findViewById(R.id.layout);
        TextView size = findViewById(R.id.inputTaille);
        TextView paragraph= findViewById(R.id.paragraph);
    Button btnvalider = findViewById(R.id.btnvalider);

        l.setVisibility(View.INVISIBLE);
         btnTextsize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 if (btnTextsize.isClickable()) {
                     l.setVisibility(View.VISIBLE);
                 }}});

        btnvalider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = size.getText().toString();
                if (t.isEmpty()) {
                    Toast.makeText(fontstyle.this, "Veuillez entrer une valeur dans le champ de taille", Toast.LENGTH_SHORT).show();
                    return;
                }
                float ft = Float.parseFloat(t);
            if (btnvalider.isClickable()) {
                    paragraph.setTextSize(ft);
                }
            }
        });

        btnBold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnBold.isClickable()){
                    paragraph.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                }            }
        });
        btnitalic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnitalic.isClickable()){
                    paragraph.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));

                }            }
        });



    }
}
