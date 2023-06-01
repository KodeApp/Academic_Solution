package com.app.edu_academic_solution.views.student.common.Attendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import com.app.edu_academic_solution.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Attendance extends AppCompatActivity {
    FloatingActionButton fabName;
    RecyclerView recyView;
    ClassAdapter classAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<ClassItem> classItems= new ArrayList<>();
    Toolbar toolbar;
    DbHelper dbHelper;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        dbHelper = new DbHelper(this);

        fabName = findViewById(R.id.fabName);
        fabName.setOnClickListener(v -> showDialog());

        loadData();

        recyView = findViewById(R.id.recyView);
        recyView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyView.setLayoutManager(layoutManager);
        classAdapter = new ClassAdapter(this,classItems);
        recyView.setAdapter(classAdapter);
        classAdapter.setOnItemClickListerner(position -> gotoItemActivity(position));

        setToolbar();

    }

    private void loadData() {

        Cursor cursor = dbHelper.getClassTable();

        classItems.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(DbHelper.C_ID));
            String className = cursor.getString(cursor.getColumnIndex(DbHelper.CLASS_NAME_KEY));
            String subjectName = cursor.getString(cursor.getColumnIndex(DbHelper.SUBJECT_NAME_KEY));

            classItems.add(new ClassItem(id,className,subjectName));
        }
    }

    private void setToolbar() {

        toolbar = findViewById(R.id.toolBar);
        TextView title = toolbar.findViewById(R.id.titleToolbar);
        TextView subtitle = toolbar.findViewById(R.id.subtitleToolbar);
        ImageButton back = toolbar.findViewById(R.id.back);
        ImageButton save = toolbar.findViewById(R.id.save);

        title.setText("Attendance App");
        subtitle.setVisibility(View.GONE);
        back.setVisibility(View.INVISIBLE);
        save.setVisibility(View.INVISIBLE);
    }

    private void gotoItemActivity(int position) {
        Intent intent = new Intent(this, StudentAttendance.class);

        intent.putExtra("ClassName",classItems.get(position).getClassName());
        intent.putExtra("SubjectName",classItems.get(position).getSubjectName());

        intent.putExtra("Position",position);
        intent.putExtra("cid",classItems.get(position).getCid());
        startActivity(intent);
    }

    private  void showDialog(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        View view = LayoutInflater.from(this).inflate(R.layout.dialog,null);
//        builder.setView(view);
//        AlertDialog dialog = builder.create();
//        dialog.show();
//
//
//        class_edt = view.findViewById(R.id.edt01);
//        sub_edt= view.findViewById(R.id.edt02);
//
//        Button cancel = view.findViewById(R.id.cancel_btn);
//        Button add = view.findViewById(R.id.add_btn);
//
//        cancel.setOnClickListener(v -> dialog.dismiss());
//        add.setOnClickListener(v -> {
//            addClass();
//            dialog.dismiss();
//        });

        MyDialog dialog = new MyDialog();
        dialog.show(getSupportFragmentManager(),MyDialog.CLASS_ADD_DIALOG);
        dialog.setListener((className,subName)->addClass(className,subName));

    }

    private void addClass(String className,String subjectName) {

        long cid = dbHelper.addClass(className,subjectName);
        ClassItem classItem =new ClassItem(cid,className,subjectName);
        classItems.add(classItem);
        classAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 0:
                showUpdateDialog(item.getGroupId());
                break;
            case 1:
                deleteClass(item.getGroupId());
        }
        return super.onContextItemSelected(item);
    }

    private void showUpdateDialog(int position) {
        MyDialog dialog = new MyDialog();
        dialog.show(getSupportFragmentManager(),MyDialog.CLASS_UPDATE_DIALOG);
        dialog.setListener((className,subjectName)->updateClass(position,className,subjectName));
    }

    private void updateClass(int position, String className, String subjectName) {
        dbHelper.updateClass(classItems.get(position).getCid(),className,subjectName);
        classItems.get(position).setClassName(className);
        classItems.get(position).setSubjectName(subjectName);
        classAdapter.notifyItemChanged(position);
    }

    private void deleteClass(int position) {
        dbHelper.deleteClass(classItems.get(position).getCid());
        classItems.remove(position);
        classAdapter.notifyItemRemoved(position);
    }
}