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



//
//        floatingActionButton = findViewById(R.id.uploadBtn);
//
//
//        uploads=new ArrayList<>();
//
//        viewAllFiles();
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
//
//                pdfClass pdfupload=uploads.get(i);
//
//                Intent intent=new Intent(Intent.ACTION_VIEW);
//                intent.setType("application/pdf");
//                intent.setData(Uri.parse(pdfupload.getUrl()));
//                startActivity(intent);
//
//            }
//        });
//
//
//
//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =new Intent(getApplicationContext(),UploadPDF.class);
//                startActivity(intent);
//            }
//        });
//    }
//
//    private void viewAllFiles() {
//
//        databaseReference= FirebaseDatabase.getInstance().getReference("Uploads");
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot postsnapshot:snapshot.getChildren()){
//                    pdfClass pdfClass=postsnapshot.getValue(com.app.edu_academic_solution.model.pdfClass.class);
//
//                    uploads.add(pdfClass);
//
//                }
//                String[] Uploads=new String[uploads.size()];
//                for (int i=0;i<Uploads.length;i++){
//                    Uploads[i]=uploads.get(i).getName();
//                }
//                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),
//                        android.R.layout.simple_list_item_1,Uploads){
//
//
//
//                    @NonNull
//                    @Override
//                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//                         View view=super.getView(position,convertView,parent);
//                        TextView text=(TextView) view.findViewById(android.R.id.text1);
//                        text.setTextColor(Color.BLACK);
//                        text.setTextSize(22);
//
//                        return view;
//
//                    }
//                };
//                listView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

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


    }
//
//    private void showPdf() {
//        FirebaseRecyclerOptions<pdfClass> options = new FirebaseRecyclerOptions.Builder<pdfClass>()
//                .setQuery(query, pdfClass.class)
//                .build();
//        FirebaseRecyclerAdapter<pdfClass, Adapter> adapter = new FirebaseRecyclerAdapter<pdfClass, Adapter>(options){
//
//            @Override
//            protected void onBindViewHolder(@NonNull Adapter holder, int position, @NonNull pdfClass model) {
//
//            }
//
//            @NonNull
//            @Override
//            public Adapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
//                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pdf_item,parent,false);
//                return new Adapter(view);
//            }
//
//        };

  //  }
}