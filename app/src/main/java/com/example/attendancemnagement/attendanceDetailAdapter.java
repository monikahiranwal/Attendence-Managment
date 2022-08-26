package com.example.attendancemnagement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class attendanceDetailAdapter extends RecyclerView.Adapter<attendanceDetailAdapter.DetailViewHolder>  {
    ArrayList<attendanceDetailModel> attendanceDetailModelArrayList;
    public attendanceDetailAdapter(ArrayList<attendanceDetailModel> attendanceDetailModelArrayList) {
        this.attendanceDetailModelArrayList = attendanceDetailModelArrayList;
    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detailattendance,parent,false);
        return new attendanceDetailAdapter.DetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder holder, int position) {
        holder.list_date.setText(attendanceDetailModelArrayList.get(position).getDate());
        holder.list_attendence.setText(attendanceDetailModelArrayList.get(position).getAttendence());
    }

    @Override
    public int getItemCount() {
        return attendanceDetailModelArrayList.size();
    }

    public class DetailViewHolder extends RecyclerView.ViewHolder {

        private TextView list_date;
        private TextView list_attendence;
        public DetailViewHolder(@NonNull View itemView) {
            super(itemView);

            list_date = itemView.findViewById(R.id.searchDate5);
            list_attendence = itemView.findViewById(R.id.searchAttendance5);
        }
    }


}
