package com.app.edu_academic_solution;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder> {
    ArrayList<ClassItem> classItems;
    Context context;

    private onItemClickListerner onItemClickListerner;
    public interface onItemClickListerner{
        void onClick(int position);
    }

    public void setOnItemClickListerner(ClassAdapter.onItemClickListerner onItemClickListerner) {
        this.onItemClickListerner = onItemClickListerner;
    }

    public ClassAdapter(Context context, ArrayList<ClassItem> classItems){
        this.classItems = classItems;
        this.context = context;
    }
    public  static class ClassViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        TextView className;
        TextView subjectName;

        public ClassViewHolder(@NonNull View itemView,onItemClickListerner onItemClickListerner) {
            super(itemView);
            className = itemView.findViewById(R.id.classTv);
            subjectName = itemView.findViewById(R.id.subjectTv);
            itemView.setOnClickListener(v->onItemClickListerner.onClick(getAdapterPosition()));
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(getAdapterPosition(),0,0,"EDIT");
            menu.add(getAdapterPosition(),1,0,"DELETE");

        }
    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.class_item,parent,false);
        return new ClassViewHolder(itemView,onItemClickListerner);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {

        holder.className.setText(classItems.get(position).getClassName());
        holder.subjectName.setText(classItems.get(position).getSubjectName());

    }

    @Override
    public int getItemCount() {
        return classItems.size();
    }
}
