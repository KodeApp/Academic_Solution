package com.app.edu_academic_solution.views.student.common;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.app.edu_academic_solution.R;
import com.app.edu_academic_solution.UploadPDF;
import com.app.edu_academic_solution.UploadPDF1;
import com.app.edu_academic_solution.model.pdfClass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;


public class Syllabus extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    ListView pdfListView;
    private DatabaseReference pRef;
    //private DatabaseReference databaseReference;
    List<pdfClass> uploads;
    Query query;
    ProgressBar progressBar;
    FirebaseStorage storage= FirebaseStorage.getInstance();
    boolean isTeacher;
    StorageReference storageReference= storage.getReference();

    List<String> pdfNames = new ArrayList<>();
    List<String> pdfUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);
        isTeacher = getIntent().getBooleanExtra("isTeacher", false);

        floatingActionButton=findViewById(R.id.uploadBtn);
        if (!isTeacher) {
            floatingActionButton.setVisibility(View.GONE);
        } else {
            floatingActionButton.setVisibility(View.VISIBLE);
        }
        displayPdfs();
    }
    private void displayPdfs() {
        String std = getIntent().getStringExtra("class");
        String db_url = std + "/" + "Syllabus";
        pRef = FirebaseDatabase.getInstance().getReference().child(db_url);

        pdfListView = findViewById(R.id.pdfListView);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        pRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    String pdfName = childSnapshot.child("name").getValue(String.class);
                    String pdfUrl = childSnapshot.child("url").getValue(String.class);
                    pdfUrls.add(pdfUrl);
                    pdfNames.add(pdfName);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(Syllabus.this, android.R.layout.simple_list_item_1, pdfNames);
                pdfListView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

       pdfListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               Intent intent = new Intent(Intent.ACTION_VIEW);
               intent.setDataAndType(Uri.parse(pdfUrls.get(position)), "application/pdf");
               intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               try {
                   startActivity(intent);
               } catch (ActivityNotFoundException e) {
                   Toast.makeText(Syllabus.this, "No PDF Viewer found", Toast.LENGTH_SHORT).show();
               }
           }
       });


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Syllabus.this, UploadPDF1.class);
                intent.putExtra("class", std);
                intent.putExtra("isTeacher", isTeacher);
                intent.putExtra("page", "Syllabus");
                startActivity(intent);
            }
        });
    }


    }
