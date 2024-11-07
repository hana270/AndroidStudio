package com.example.tp4_listes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class exercice1__ extends AppCompatActivity {
LinearLayout background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex1);
    background = findViewById(R.id.back);
        Button button=findViewById(R.id.b1);
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent i = new Intent(exercice1__.this , Exercice1.class);
        startActivityForResult(i,10);
    }
});

    }
    long ls;
    public void onActivityResult(int requestCode, int resultCode, Intent data){
super.onActivityResult(requestCode,resultCode,data);
if(requestCode == 10){
    if(resultCode == RESULT_OK){
         ls =data.getLongExtra("color",0);
        background.setBackgroundColor((int) ls);

    }
}
    }
}