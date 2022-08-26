package com.example.attendancemnagement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.searchViewHolder> {
    ArrayList<searchModel> searchModelArrayList;
    public SearchAdapter(ArrayList<searchModel> searchModelArrayList){
        this.searchModelArrayList= searchModelArrayList;
    }

    @NonNull
    @Override
    public searchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.searchattendance,parent,false);
        return new searchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull searchViewHolder holder, int position) {

        holder.list_date.setText(searchModelArrayList.get(position).getDate());
        holder.list_attendence.setText(searchModelArrayList.get(position).getAttendence());
    }

    @Override
    public int getItemCount() {
        return searchModelArrayList.size();
    }


    public class searchViewHolder extends RecyclerView.ViewHolder {

        private TextView list_date;
        private TextView list_attendence;
        public searchViewHolder(@NonNull View itemView) {
            super(itemView);

            list_date = itemView.findViewById(R.id.searchDate);
            list_attendence = itemView.findViewById(R.id.searchAttendance);
        }
    }
}
