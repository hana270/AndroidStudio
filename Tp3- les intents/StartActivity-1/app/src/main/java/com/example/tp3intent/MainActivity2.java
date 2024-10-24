package com.example.tp3intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView  nom = (TextView)findViewById(R.id.nom);
        nom.setText("Hello "+ getIntent().getStringExtra("nom"));

        TextView  age = (TextView)findViewById(R.id.age);
        age.setText(" Age : "+ getIntent().getStringExtra("age"));

        TextView  siteW = (TextView)findViewById(R.id.sitew);
        siteW.setText("Site Web : "+ getIntent().getStringExtra("sitew"));

        TextView  numtel = (TextView)findViewById(R.id.numtel);
        numtel.setText("Numero de telephone :  "+ getIntent().getStringExtra("numtel"));

        Button btnOK ,BtnR;

        btnOK = findViewById(R.id.btnok);
        BtnR = findViewById(R.id.btnr);

        BtnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }});
     /*   btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(x);
            }
        });
*/
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x =new Intent(MainActivity2.this,MainActivity3.class);
                startActivity(x);
            }
        });



    }
}