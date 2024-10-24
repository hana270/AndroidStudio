package com.example.tp3intent;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    EditText nom;
    EditText age;
    EditText sitew;
    EditText numtel;
    Button btnV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.premierapp);


        nom = findViewById(R.id.nom);
        age = findViewById(R.id.age);
        sitew = findViewById(R.id.sitew);
        numtel = findViewById(R.id.numtel);
        btnV = findViewById(R.id.btnV);


        btnV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Vérifier si le champ nom est vide
                if (nom.getText().toString().isEmpty()) {
                    nom.setBackgroundColor(Color.RED);  // Fond rouge si vide
                } else {
                    nom.setBackgroundColor(Color.GREEN);  // Fond vert si rempli
                }

                if (age.getText().toString().isEmpty()) {
                    age.setBackgroundColor(Color.RED);
                } else {
                    age.setBackgroundColor(Color.GREEN);
                }
               if (sitew.getText().toString().isEmpty() ||  !sitew.getText().toString().startsWith("https://") ) {
                    sitew.setBackgroundColor(Color.RED);
                } else {
                    sitew.setBackgroundColor(Color.GREEN);
                }
                if (numtel.getText().toString().isEmpty()) {
                    numtel.setBackgroundColor(Color.RED);
                } else {
                    numtel.setBackgroundColor(Color.GREEN);
                }

                Intent x =new Intent(MainActivity.this,MainActivity2.class);

                x.putExtra("nom",nom.getText().toString());
                x.putExtra("age",age.getText().toString());
                x.putExtra("sitew",sitew.getText().toString());
                x.putExtra("numtel",numtel.getText().toString());
                startActivity(x);

            }
        });



    }




}