package com.example.finalproject;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {
    private List<course> courses;
    private SelectCourse lis;

    public RecyclerViewAdapter(List<course> courses, SelectCourse lis) {
        this.courses = courses;
        this.lis = lis;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
        return new RecyclerViewAdapter.ViewHolder(view, lis);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        course course = courses.get(position);

        holder.idTextView.setText(course.getId());
        holder.nameTextView.setText(course.getName());
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView idTextView;
        TextView nameTextView;
        public CardView cardView;

        public ViewHolder(@NonNull View itemView, SelectCourse lis) {
            super(itemView);
            idTextView = itemView.findViewById(R.id.title2);
            nameTextView = itemView.findViewById(R.id.title3);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (lis != null){
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION){
                            lis.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
