package com.app.edu_academic_solution;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.edu_academic_solution.model.pdfClass;
import com.app.edu_academic_solution.views.student.common.Syllabus;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class UploadPDF extends AppCompatActivity {

    Button uploadButton, viewButton;
    EditText pdfName;

   StorageReference storageReference;
   DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_pdf);

        uploadButton = findViewById(R.id.uploadButton);
        viewButton = findViewById(R.id.viewButton);
        pdfName = findViewById(R.id.pdfName);

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("Uploads");

        uploadButton.setEnabled(false);

        pdfName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectFiles();
            }
        });


    }

    private void selectFiles() {

        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select PDF Files..."),101);
    }

    @SuppressLint("Range")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==101 && resultCode == RESULT_OK && data != null && data.getData() != null){

            Uri uri =(data.getData());

            String uriString = uri.toString();
            File myFile = new File(uriString);
            String path = myFile.getAbsolutePath();
            String displayName = null;

            if(uriString.startsWith("content://")){
                Cursor cursor = null;
                try {
                    cursor = this.getContentResolver().query(uri,null,null,null,null);
                    if (cursor != null && cursor.moveToFirst()){
                        displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));

                    }
                } finally {
                   cursor.close();
                }
            } else if (uriString.startsWith("file://")){
                displayName = myFile.getName();
            }
            uploadButton.setEnabled(true);
            pdfName.setText(displayName);

            uploadButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uploadfiles(data.getData());
                }
            });
        }
    }

    private void Uploadfiles(Uri data) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();

        final StorageReference reference=storageReference.child("Uploads/"+System.currentTimeMillis()+".pdf");
        reference.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                        while (!uriTask.isComplete());
                        Uri uri = uriTask.getResult();

                        pdfClass pdfClass=new pdfClass(pdfName.getText().toString(),uri.toString());
                        databaseReference.child(databaseReference.push().getKey()).setValue(pdfClass);

                        Toast.makeText(UploadPDF.this, "File Uploaded Successfully!!", Toast.LENGTH_SHORT).show();

                        progressDialog.dismiss();

                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                        float percent =(100 * snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                        progressDialog.setMessage("Uploaded :"+(int)percent + "%");



                    }
                });
    }


//    private void UploadFiles(Uri data) {
//        final ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setTitle("Uploading...");
//        progressDialog.show();
//
//        StorageReference reference=storageReference.child("Uploads/"+System.currentTimeMillis()+".pdf");
//        reference.putFile(data)
//                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                        Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
//                        while (!uriTask.isComplete());
//                        Uri uri = uriTask.getResult();
//
//                        pdfClass pdfClass=new pdfClass(pdfName.getText().toString(),uri.toString());
//                        databaseReference.child(databaseReference.push().getKey()).setValue(pdfClass);
//
//                        Toast.makeText(UploadPDF.this, "File Uploaded!!", Toast.LENGTH_SHORT).show();
//
//                        progressDialog.dismiss();
//
//                    }
//                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
//
//                        double progress=(100.0*snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
//                        progressDialog.setMessage("Upload:"+(int)progress+"%");
//
//
//
//                    }
//                });
//
//    }

    public void viewPDF(View view) {
        Intent intent = new Intent(getApplicationContext(), Syllabus.class);
        startActivity(intent);

    }
}