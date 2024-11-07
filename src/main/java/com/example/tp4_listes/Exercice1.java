package com.example.tp4_listes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Exercice1 extends AppCompatActivity {
    ListView maliste;
Intent in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercice1);

        String[] couleurs = {"Yellow", "Green", "Blue", "Red"};
        maliste = findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, couleurs);
        maliste.setAdapter(adapter);

        maliste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (couleurs[i].equals("Yellow")) {
                    maliste.setBackgroundColor(getResources().getColor(R.color.Yellow));
                    l = getResources().getColor(R.color.Yellow);

                }else if (couleurs[i].equals("Green"))  {
                        maliste.setBackgroundColor(getResources().getColor(R.color.Green));
                    l = getResources().getColor(R.color.Green);

                }else if (couleurs[i].equals("Blue")) {
                        maliste.setBackgroundColor(getResources().getColor(R.color.Blue));
                    l = getResources().getColor(R.color.Blue);

                }else if (couleurs[i].equals("Red")){
                        maliste.setBackgroundColor(getResources().getColor(R.color.Red));
                    l = getResources().getColor(R.color.Red);
                }

                in =new Intent();
                in.putExtra("color",l);
                setResult(RESULT_OK,in);
                finish();

            }

        });
    }
}
