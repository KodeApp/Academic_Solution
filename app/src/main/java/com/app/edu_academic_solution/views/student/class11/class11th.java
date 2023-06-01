package com.app.edu_academic_solution.views.student.class11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.app.edu_academic_solution.R;
import com.app.edu_academic_solution.views.student.common.Attendance.Attendance;
import com.app.edu_academic_solution.views.student.common.DPP;
import com.app.edu_academic_solution.views.student.common.Notes;
import com.app.edu_academic_solution.views.student.common.Syllabus;

public class class11th extends AppCompatActivity {

    CardView syllabus, notes, dpp;
    boolean isTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class11th);
        String role = getIntent().getStringExtra("role");

        isTeacher = getIntent().getBooleanExtra("isTeacher", false);
        Log.i("Value", "isTeacher = " + isTeacher);
        Log.i("Value", "role = " + role);

        syllabus = findViewById(R.id.syllabus11);
        notes = findViewById(R.id.notes11);
        dpp = findViewById(R.id.dpp11);

        syllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(class11th.this, Syllabus.class);
                intent.putExtra("class", "11");
                intent.putExtra("isTeacher", isTeacher);
                Log.i("Value", "syllabus isTeacher = " + isTeacher);
                startActivity(intent);
            }
        });

        notes.setOnClickListener((v -> {
            Intent intent = new Intent(class11th.this, Notes.class);
            intent.putExtra("class", "11");
            intent.putExtra("isTeacher", isTeacher);
            Log.i("Value", "notes isTeacher = " + isTeacher);
            startActivity(intent);
        }));
        dpp.setOnClickListener((v -> {
            Intent intent = new Intent(class11th.this, DPP.class);
            intent.putExtra("class", "11");
            intent.putExtra("isTeacher", isTeacher);
            Log.i("Value", "DPP isTeacher = " + isTeacher);
            startActivity(intent);
        }));




    }

    public void attendanceOk(View view) {
        Intent intent = new Intent(this, Attendance.class);
        startActivity(intent);
    }

}




