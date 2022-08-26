package com.example.attendancemnagement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class attendViewAdapter extends RecyclerView.Adapter<attendViewAdapter.attendViewHolder> {

    studentAttend studentattend;
    ArrayList<Attendencemodel> attendenceModelArrayList;

    public attendViewAdapter(studentAttend studentattend, ArrayList<Attendencemodel> attendenceModelArrayList) {

        this.studentattend = studentattend;
        this.attendenceModelArrayList = attendenceModelArrayList;
    }

    @NonNull
    @Override
    public attendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(studentattend.getBaseContext());
        View view = layoutInflater.inflate(R.layout.studentattend, parent, false);

        return new attendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull attendViewHolder holder, int position) {

        Attendencemodel attendencemodel = attendenceModelArrayList.get(position);

        holder.RollNo.setText(attendenceModelArrayList.get(position).getRollNo());
        holder.FirstName.setText(attendenceModelArrayList.get(position).getFirstName());
        holder.FatherName.setText(attendenceModelArrayList.get(position).getFatherName());
        holder.attendCheckbox.setText(attendenceModelArrayList.get(position).getAttendcheckbox());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                Attendencemodel attendencemodel = attendenceModelArrayList.get(pos);
                CheckBox chk = view.findViewById(R.id.attendcheckbox);
                if (chk.isChecked()) {
                    attendencemodel.setAttendcheckbox("True");
                    Toast.makeText(studentattend,"Present", Toast.LENGTH_SHORT).show();
                } else if (!chk.isChecked()) {
                    attendencemodel.setAttendcheckbox("False");
                    Toast.makeText(studentattend,"Absent", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return attendenceModelArrayList.size();
    }

    public class attendViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView RollNo, FirstName, FatherName;
        public CheckBox attendCheckbox;
        ItemClickListener itemClickListener;
        public attendViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            RollNo = itemView.findViewById(R.id.attendrollNo);
            FirstName = itemView.findViewById(R.id.attendName);
            FatherName = itemView.findViewById(R.id.attendFatherName);
            attendCheckbox = (CheckBox) itemView.findViewById(R.id.attendcheckbox);
            attendCheckbox.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener ic) {
            this.itemClickListener = ic;
        }
        @Override
        public void onClick(View view) {
            this.itemClickListener.onItemClick(view,getLayoutPosition());
        }
    }
}
