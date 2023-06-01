package com.app.edu_academic_solution;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class Bonafide extends AppCompatActivity {

    TextInputEditText name, className, reason;
    Button bonafideRequestButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonafide);

        bonafideRequestButton = findViewById(R.id.bonafideRequestButton);
        name = findViewById(R.id.bonafideRequestStudentName);
        className = findViewById(R.id.bonafideRequestStudentClassName);
        reason = findViewById(R.id.bonafideRequestStudentReason);

        bonafideRequestButton.setOnClickListener((v -> {
            String nameStr = bonafideRequestButton.getText().toString();
            String classNameStr = className.getText().toString();
            String reasonStr = reason.getText().toString();

            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL, new String[] {"dhananjaypanage11@gmail.com"});
            email.putExtra(Intent.EXTRA_SUBJECT, "Bonafide Request - " + nameStr + " " + classNameStr);
            email.putExtra(Intent.EXTRA_TEXT, reasonStr);
            email.setType("message/rfc822");

            startActivity(Intent.createChooser(email, "Choose an email client: "));

        }));

    }
}