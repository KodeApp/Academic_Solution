package com.app.edu_academic_solution;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.edu_academic_solution.model.pdfClass;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;


public class Syllabus extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    RecyclerView pdfRecycleView;
    private DatabaseReference pRef;
    //private DatabaseReference databaseReference;
    List<pdfClass> uploads;
    Query query;
    ProgressBar progressBar;
    FirebaseStorage storage= FirebaseStorage.getInstance();
    StorageReference storageReference= storage.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);

        floatingActionButton=findViewById(R.id.uploadBtn);



        displayPdfs();



    }

    private void displayPdfs() {

        pRef = FirebaseDatabase.getInstance().getReference().child("Uploads");
        pdfRecycleView = findViewById(R.id.pdfrecycleView);
        pdfRecycleView.setHasFixedSize(true);
        pdfRecycleView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        query = pRef.orderByChild("name");
        //we will request the file with the name, if name  exists then it will show in thw recycleView

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    progressBar.setVisibility(View.GONE);
                //    showPdf();
                }else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(Syllabus.this, ":", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Syllabus.this,UploadPDF.class);
                startActivity(intent);
            }
        });
    }


    }
