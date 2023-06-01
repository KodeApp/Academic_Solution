package com.app.edu_academic_solution.views.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.edu_academic_solution.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class LoginActivity extends AppCompatActivity {
    Spinner spinner;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    CollectionReference collectionReference;
    String currentRole;
    String[] roles = {"Student", "Teacher", "Clerk", "Admin"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firestore = FirebaseFirestore.getInstance();
        firebaseAuth  = FirebaseAuth.getInstance();

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(LoginActivity.this,android.R.layout.simple_spinner_item, roles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentRole = parent.getItemAtPosition(position).toString();
                Toast.makeText(LoginActivity.this, currentRole, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void login(View view) {
        EditText emailEditText = findViewById(R.id.username);
        EditText passwordEditText = findViewById(R.id.password);
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // As currentRole is Student, Teacher or Clerk and we need student, teacher or clerk
        // as these are the collection names
        currentRole = currentRole.toLowerCase();
        Log.e("LOGIN", "email: " + email);
        Log.e("LOGIN", "password: " + password);
        Log.d("LOGIN", "current role:" + currentRole);
        collectionReference = firestore.collection(currentRole);
        Query query = collectionReference.whereEqualTo("email", email);

        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "No such email with role " + currentRole + " found", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("LOGIN", "email: " + email + " found in role: " + currentRole);
                    Log.d("LOGIN", "Initiating Login Process");
                    initiateFirebaseLoginProcess(email, password);
                }
            }
            else {
                Log.e("LOGIN", "There was a issue fetching documents");
            }
        });
    }
    private void initiateFirebaseLoginProcess(String email, String password) {
        Log.d("LOGIN", "Logging in with email and password");
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                Log.d("LOGIN", "Login successful");
                Log.d("LOGIN", "Moving to HomeScreen");
                FirebaseUser userData = firebaseAuth.getCurrentUser();
                Intent intent = new Intent(this, HomeScreen.class);
                intent.putExtra("userData", userData);
                intent.putExtra("role", currentRole);
                intent.putExtra("isTeacher", currentRole.equals("teacher"));
                startActivity(intent);
            } else {
                Log.d("LOGIN", "Login failed, password is incorrect");
                Toast.makeText(LoginActivity.this, "Username or password is incorrect", Toast.LENGTH_SHORT).show();
            }
        });
    }
}