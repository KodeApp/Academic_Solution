package com.app.edu_academic_solution;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Spinner spinner;
    String[] role= {"Student", "Teacher", "Clerk", "Admin"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        spinner = findViewById(R.id.spinner);




        ArrayAdapter<String> adapter=new ArrayAdapter<String>(LoginActivity.this,android.R.layout.simple_spinner_item,role);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = parent.getItemAtPosition(position).toString();
                Toast.makeText(LoginActivity.this, "value", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }

    public void loginBtn(View view) {
        Intent intent = new Intent(LoginActivity.this,HomeScreen.class);
        startActivity(intent);
    }
}