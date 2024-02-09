package com.example.finalproject;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ElectivesAdapter extends RecyclerView.Adapter<ElectivesAdapter.ViewHolder> {
    private List<ElectiveItem> electives;

    public ElectivesAdapter(List<ElectiveItem> electives) {
        this.electives = electives;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_elective, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ElectiveItem elective = electives.get(position);

        holder.idTextView.setText(elective.getId());
        holder.nameTextView.setText(elective.getName());
        holder.hoursTextView.setText(elective.getHours());
    }

    @Override
    public int getItemCount() {
        return electives.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView idTextView;
        TextView nameTextView;
        TextView hoursTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idTextView = itemView.findViewById(R.id.idTextView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            hoursTextView = itemView.findViewById(R.id.hoursTextView);
        }
    }
}
