package com.app.edu_academic_solution;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeScreen extends AppCompatActivity {

    CardView classFirst,classSecond;
    boolean isTeacher = false;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = getUserData();
        isTeacher = getIsTeacher();

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
            Intent intent = new Intent(HomeScreen.this,Class11th.class);
            startActivity(intent);
        });

        classSecond.setOnClickListener(view -> {
            Intent intent = new Intent(HomeScreen.this,class12.class);
            startActivity(intent);

        });
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