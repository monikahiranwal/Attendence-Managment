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

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.FirebaseFirestore;

public class viewfaculty extends AppCompatActivity {
    private RecyclerView mFirestoreList;
    private FirestoreRecyclerAdapter adapter;
    private FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewfaculty);

        mFirestoreList = findViewById(R.id.recycler);
        firebaseFirestore = FirebaseFirestore.getInstance();

        Query query = firebaseFirestore.collection("AddFaculty");
        FirestoreRecyclerOptions<viewfacultydb> options = new FirestoreRecyclerOptions.Builder<viewfacultydb>()
                .setQuery(query,viewfacultydb.class)
                .build();
        adapter =new FirestoreRecyclerAdapter<viewfacultydb, viewfaculty.facultyViewHolder>(options) {
            @NonNull
            @Override
            public viewfaculty.facultyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.viewfaculty, parent, false);
                return new viewfaculty.facultyViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull viewfaculty.facultyViewHolder holder, int position, @NonNull viewfacultydb model) {
                holder.username.setText(model.getUsername());
                holder.password.setText(model.getPassword());
                holder.firstName.setText(model.getFirstName());
                holder.lastName.setText(model.getLastName());
                holder.phoneNo.setText(model.getPhoneNo());
                holder.address.setText(model.getAddress());


            }
        };


        mFirestoreList.setHasFixedSize(true);
        mFirestoreList.setLayoutManager(new LinearLayoutManager(this));
        mFirestoreList.setAdapter(adapter);
    }


    private class facultyViewHolder extends RecyclerView.ViewHolder {

        private TextView username, firstName, lastName, password, phoneNo, address;

        public facultyViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username);
            firstName = itemView.findViewById(R.id.firstName);
            lastName = itemView.findViewById(R.id.lastName);
            password = itemView.findViewById(R.id.password);
            phoneNo = itemView.findViewById(R.id.phoneNo);
            address = itemView.findViewById(R.id.address);


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