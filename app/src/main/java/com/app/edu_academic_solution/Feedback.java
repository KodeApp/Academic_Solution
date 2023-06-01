package com.app.edu_academic_solution;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Feedback extends AppCompatActivity {

    Spinner spinner1;
    String[] role= {"Student", "Teacher", "Clerk", "Admin","Parent"};
    Button submit;
    EditText message;
    TextView subject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        submit = findViewById(R.id.submitFeedback);

        spinner1 = findViewById(R.id.spinner1);


        ArrayAdapter<String> adapter=new ArrayAdapter<String>(Feedback.this,android.R.layout.simple_spinner_item,role);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = parent.getItemAtPosition(position).toString();
                Toast.makeText(Feedback.this, "value", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submit.setOnClickListener(v -> {

            message = findViewById(R.id.message);
            subject = findViewById(R.id.txtFeedback);

            String messageStr = message.getText().toString();
            String subjectStr = subject.getText().toString();

            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL, new String[] {"dhananjaypanage11@gmail.com"});
            email.putExtra(Intent.EXTRA_SUBJECT, subjectStr);
            email.putExtra(Intent.EXTRA_TEXT, messageStr);
            email.setType("message/rfc822");

            startActivity(Intent.createChooser(email, "Choose an email client: "));

        });



    }



}