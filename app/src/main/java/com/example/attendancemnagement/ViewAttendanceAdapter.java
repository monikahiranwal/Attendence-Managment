package com.example.attendancemnagement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewAttendanceAdapter extends RecyclerView.Adapter<ViewAttendanceAdapter.myviewholder> {
    ArrayList<viewAttendanceModel> viewAttendanceModelArrayList;

    public ViewAttendanceAdapter(ArrayList<viewAttendanceModel> viewAttendanceModelArrayList) {
        this.viewAttendanceModelArrayList = viewAttendanceModelArrayList;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewattendance,parent,false);
       return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {

        holder.t1.setText(viewAttendanceModelArrayList.get(position).getrollNo());
        holder.t2.setText(viewAttendanceModelArrayList.get(position).getfirstName());
        holder.t3.setText(viewAttendanceModelArrayList.get(position).getfatherName());
        holder.t4.setText(viewAttendanceModelArrayList.get(position).getAttendence());

    }

    @Override
    public int getItemCount() {
        return viewAttendanceModelArrayList.size();
    }

    class  myviewholder extends RecyclerView.ViewHolder {
        TextView t1,t2,t3,t4;
        public myviewholder(@NonNull View itemView) {
            super(itemView);

            t1=itemView.findViewById(R.id.viewRollNo);
            t2=itemView.findViewById(R.id.viewName);
            t3=itemView.findViewById(R.id.viewFatherName);
            t4=itemView.findViewById(R.id.viewAttendence);

        }
    }
}
