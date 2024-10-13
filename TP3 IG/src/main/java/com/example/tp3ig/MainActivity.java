package com.example.tp3ig;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText input;
    RadioButton toEuro;
    RadioButton toTND;
    Button convertButton;
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        input = findViewById(R.id.input);
        toEuro = findViewById(R.id.dinartoeuro);
        toTND = findViewById(R.id.eurotodinar);
        convertButton = findViewById(R.id.convert);
        resultText = findViewById(R.id.result);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valeur = input.getText().toString();
                if (valeur.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Entrez un montant svp !!!", Toast.LENGTH_SHORT).show();
                    return; }

                double montant = Double.parseDouble(valeur);
                double tauxChange = 3.2;

                if (toEuro.isChecked()) {
                    // Conversion de dinar vers euro
                    double resultat = montant / tauxChange;
                    resultText.setText(montant + " TND equivaut à " + String.valueOf(resultat) + " EUR");
                } else if (toTND.isChecked()) {
                    // Conversion euro vers dinar
                    double resultat = montant * tauxChange;
                    resultText.setText(montant + " EUR equivaut à " + String.valueOf(resultat) + " TND");
                } else {
                    Toast.makeText(MainActivity.this, "Sélectionnez une option de conversion!", Toast.LENGTH_SHORT).show();
                return ;
                }

            }
        });
    }
}
