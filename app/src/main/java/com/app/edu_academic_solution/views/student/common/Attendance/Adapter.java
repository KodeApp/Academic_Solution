package com.app.edu_academic_solution.views.student.common.Attendance;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.edu_academic_solution.R;

public class Adapter extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ItemClickListerner listener;
    private final Context context;
    public TextView pdfTitle;

    public Adapter(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();
        pdfTitle = itemView.findViewById(R.id.pdfName1);
    }

    @Override
    public void onClick(View view) {


        listener.onClick(view, getAdapterPosition(),false);

    }
}