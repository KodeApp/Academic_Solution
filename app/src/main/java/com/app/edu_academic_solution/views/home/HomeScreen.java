package com.app.edu_academic_solution.views.home;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.app.edu_academic_solution.Bonafide;
import com.app.edu_academic_solution.Feedback;
import com.app.edu_academic_solution.R;
import com.app.edu_academic_solution.views.student.class11.class11th;
import com.app.edu_academic_solution.views.student.class12.class12th;
import com.google.firebase.auth.FirebaseUser;

public class HomeScreen extends AppCompatActivity {

    CardView classFirst,classSecond;
    boolean isTeacher = false;
    String role;
    FirebaseUser user;
    Context context;
    TextView feedbackTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = getUserData();
        isTeacher = getIsTeacher();
        role = getIntent().getStringExtra("role");

        setContentView(R.layout.activity_home_screen);

        if (isTeacher) {
            TextView feesTextView = findViewById(R.id.feesTextView);
            TextView feedbackTextView = findViewById(R.id.feedbackTextView);
            TextView bonafideRequestTextView = findViewById(R.id.bonafideRequestTextView);
            feesTextView.setVisibility(TextView.GONE);
            feedbackTextView.setVisibility(TextView.GONE);
            bonafideRequestTextView.setVisibility(TextView.GONE);
        }


        classFirst = findViewById(R.id.classFirst);
        classSecond = findViewById(R.id.classSecond);

        classFirst.setOnClickListener(view -> {
            Intent intent = new Intent(HomeScreen.this, class11th.class);
            intent.putExtra("user", user);
            intent.putExtra("isTeacher", isTeacher);
            startActivity(intent);
        });

        classSecond.setOnClickListener(view -> {
            Intent intent = new Intent(HomeScreen.this, class12th.class);
            intent.putExtra("user", user);
            intent.putExtra("isTeacher", isTeacher);
            startActivity(intent);

        });
    }
    public void bonafideBtn(View view) {

        Intent intent = new Intent(this, Bonafide.class);
        startActivity(intent);
    }

    public void feedbackBtn(View view) {
        Intent intent = new Intent(this, Feedback.class);
        startActivity(intent);

    }
    private FirebaseUser getUserData() {
        Intent intent = getIntent();
        return intent.getParcelableExtra("userData");
    }
    private boolean getIsTeacher() {
        Intent intent = getIntent();

        return intent.getBooleanExtra("isTeacher", false);

    }
}