package com.example.attendancemnagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.FirebaseFirestore;

public class viewstudent extends AppCompatActivity {

    private RecyclerView mFirestoreList;
    private String UserRole1, UserRole2, UserRole3;
    private FirestoreRecyclerAdapter adapter;
    private FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewstudent);

        UserRole1 = getIntent().getExtras().getString("keyname1");  //course
        UserRole2 = getIntent().getExtras().getString("keyname2");  //department
        UserRole3 = getIntent().getExtras().getString("keyname3");  //year

        mFirestoreList = findViewById(R.id.recycler);
        firebaseFirestore = FirebaseFirestore.getInstance();

        Query query = firebaseFirestore.collection("AddStudent")
                .whereEqualTo("course",UserRole1).whereEqualTo("department",UserRole2).whereEqualTo("year",UserRole3);
        FirestoreRecyclerOptions<viewstudentdb> options = new FirestoreRecyclerOptions.Builder<viewstudentdb>()
                .setQuery(query,viewstudentdb.class)
                .build();
        adapter =new FirestoreRecyclerAdapter<viewstudentdb,studentViewHolder>(options) {
            @NonNull
            @Override
            public studentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.viewstudent, parent, false);
                return new studentViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull studentViewHolder holder, int position, @NonNull viewstudentdb model) {
                holder.rollNo.setText(model.getRollNo());
                holder.firstName.setText(model.getFirstName());
                holder.lastName.setText(model.getLastName());
                holder.fatherName.setText(model.getFatherName());
                holder.phoneNo.setText(model.getPhoneNo());
                holder.address.setText(model.getAddress());
                holder.course.setText(model.getCourse());
                holder.department.setText(model.getDepartment());
                holder.year.setText(model.getYear());

            }
        };


        mFirestoreList.setHasFixedSize(true);
        mFirestoreList.setLayoutManager(new LinearLayoutManager(this));
        mFirestoreList.setAdapter(adapter);
    }


    private class studentViewHolder extends RecyclerView.ViewHolder {

        private TextView rollNo, firstName, lastName, fatherName, phoneNo, address, course, department, year;

        public studentViewHolder(@NonNull View itemView) {
            super(itemView);

            rollNo = itemView.findViewById(R.id.rollNo);
            firstName = itemView.findViewById(R.id.firstName);
            lastName = itemView.findViewById(R.id.lastName);
            fatherName = itemView.findViewById(R.id.fatherName);
            phoneNo = itemView.findViewById(R.id.phoneNo);
            address = itemView.findViewById(R.id.address);
            course = itemView.findViewById(R.id.course);
            department = itemView.findViewById(R.id.department);
            year = itemView.findViewById(R.id.year);

        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

}