package com.example.tp2ig;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
EditText texte1 , texte2;
RadioButton r1,r2,r3,r4;
Button btn;
TextView res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calcul);
        texte1 = findViewById(R.id.texte1);
        texte2 = findViewById(R.id.texte2);
        r1 = findViewById(R.id.r1);
        r2 = findViewById(R.id.r2);
        r3 = findViewById(R.id.r3);
        r4 = findViewById(R.id.r4);
        btn = findViewById(R.id.btn);
        res = findViewById(R.id.res);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (texte1.getText().toString().isEmpty() || texte2.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity2.this, "Veuillez entrer des valeurs dans les deux champs", Toast.LENGTH_SHORT).show();
                    return;
                }

                int num1 = Integer.parseInt(texte1.getText().toString());
                int num2 = Integer.parseInt(texte2.getText().toString());

               if (!r1.isChecked() && !r2.isChecked() && !r3.isChecked() && !r4.isChecked()) {
                    Toast.makeText(MainActivity2.this, "Champs Vide", Toast.LENGTH_SHORT).show();
                    return;
                }

               int rest = 0;
                if (r1.isChecked()) {
                    rest = num1 + num2;
                } else if (r2.isChecked()) {
                    rest = num1 - num2;
                } else if (r3.isChecked()) {
                    rest = num1 * num2;
                } else if (r4.isChecked()) {
                    if (num2 == 0) {
                        Toast.makeText(MainActivity2.this, "Division par 0 impossible", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    rest = num1 / num2;
                }

                     res.setText("Résultat = " + rest);

                if (!r1.isChecked() && !r2.isChecked() && !r3.isChecked() && !r4.isChecked()) {
                    Toast.makeText(MainActivity2.this, "Veuillez sélectionner une opération", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }



        );



    }
}