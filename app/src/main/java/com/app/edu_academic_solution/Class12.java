package com.app.edu_academic_solution;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Class12 extends AppCompatActivity {

    private final CardView attendanceCardView = findViewById(R.id.attendanceCardView);
    private FirebaseUser currentUser;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class12);
        attendanceCardView.setOnClickListener(v -> {
            Intent intent = new Intent(Class12.this, AttendanceMenu.class);
            intent.putExtra("isTeacher", true);
            startActivity(intent);


        });
    }


}