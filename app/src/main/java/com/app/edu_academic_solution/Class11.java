package com.app.edu_academic_solution;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Class11 extends AppCompatActivity {

    CardView syllabus11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class11);

        syllabus11 = findViewById(R.id.syllabus11);

        syllabus11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Class11.this,Syllabus.class);
                startActivity(intent);
            }
        });


    }

}