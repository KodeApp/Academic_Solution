package com.app.edu_academic_solution.views.student.class12;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.app.edu_academic_solution.R;
import com.app.edu_academic_solution.views.student.common.Attendance.Attendance;
import com.app.edu_academic_solution.views.student.common.DPP;
import com.app.edu_academic_solution.views.student.common.Notes;
import com.app.edu_academic_solution.views.student.common.Syllabus;

public class class12th extends AppCompatActivity {

    CardView syllabus;
    CardView notes;
    CardView dpp;
    boolean isTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class12);
        isTeacher = getIntent().getBooleanExtra("isTeacher", false);

        syllabus = findViewById(R.id.syllabus12);
        notes = findViewById(R.id.notes12);
        dpp = findViewById(R.id.dpp12);

        syllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(class12th.this, Syllabus.class);
                intent.putExtra("isTeacher", isTeacher);
                intent.putExtra("class", "12");
                startActivity(intent);
            }
        });

        notes.setOnClickListener(v -> {
            Intent intent = new Intent(class12th.this, Notes.class);
            intent.putExtra("isTeacher", isTeacher);
            intent.putExtra("class", "12");
            startActivity(intent);
        });
        dpp.setOnClickListener(v -> {
            Intent intent = new Intent(class12th.this, DPP.class);
            intent.putExtra("isTeacher", isTeacher);
            intent.putExtra("class", "12");
            startActivity(intent);
        });


    }

    public void attendanceOk(View view) {
        Intent intent = new Intent(this, Attendance.class);
        startActivity(intent);
    }
}




