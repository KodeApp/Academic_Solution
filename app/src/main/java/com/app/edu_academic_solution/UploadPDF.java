package com.app.edu_academic_solution;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.storage.StorageReference;

public class UploadPDF extends AppCompatActivity {

    Button uploadButton, viewButton;
    EditText pdfName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_pdf);
    }
}