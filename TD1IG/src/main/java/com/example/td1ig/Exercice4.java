package com.example.td1ig;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Exercice4 extends AppCompatActivity {

    EditText mot1;
    EditText mot2;
    CheckBox clicked;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercice4); 

        mot1 = findViewById(R.id.mot1);
        mot2 = findViewById(R.id.mot2);
        clicked = findViewById(R.id.espace);
        btn = findViewById(R.id.btnconcat);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                concat(v);  }
        });
    }

    public void concat(View view) {
        String m1 = mot1.getText().toString();
        String m2 = mot2.getText().toString();
        String res;

        if (m1.isEmpty() || m2.isEmpty()) {
            Toast.makeText(Exercice4.this, "Champs vide!!!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (clicked.isChecked()) {
            res = m1 + " " + m2;
        } else {
            res = m1 + m2;
        }
        Toast.makeText(Exercice4.this, "Résultat : " + res, Toast.LENGTH_SHORT).show();
    }
}
