package com.app.edu_academic_solution;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeScreen extends AppCompatActivity {

    CardView classFirst,classSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        classFirst = findViewById(R.id.classFirst);
        classSecond = findViewById(R.id.classSecond);

        classFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this,Class11th.class);
                startActivity(intent);
            }
        });

        classSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this,class12.class);
                startActivity(intent);

            }
        });
    }
}