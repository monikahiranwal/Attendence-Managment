package com.example.attendancemnagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class viewAttendance extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private RecyclerView mRecyclerView;
    private String UserRole4, UserRole5, UserRole6, getDate, getDay, getMonth, getYear;
    public String getLecture2;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Spinner SpinnerLecture;
    private TextView textViewDate;
    private Button Back;
    ArrayList<viewAttendanceModel> viewAttendanceModelArrayList;
    ViewAttendanceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance);

        Back = findViewById(R.id.back);
        SpinnerLecture = findViewById(R.id.viewSpinnerLecture);
        textViewDate = findViewById(R.id.viewTextViewDate);
        UserRole4 = getIntent().getExtras().getString("keyname1");  //course
        UserRole5 = getIntent().getExtras().getString("keyname2");  //department
        UserRole6 = getIntent().getExtras().getString("keyname3");  //year
        getDate = getIntent().getExtras().getString("keyname4");//date

        textViewDate.setText(getDate);
        populateSpinnerLecture();
        viewAttendanceModelArrayList = new ArrayList<>();
        adapter = new ViewAttendanceAdapter(viewAttendanceModelArrayList);
        setUpRecyclerView();
        mRecyclerView.setAdapter(adapter);
        SetUpFirebase();
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(viewAttendance.this, teacherpanel.class);
                startActivity(intent);
            }
        });
        SpinnerLecture.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getLecture2 = SpinnerLecture.getSelectedItem().toString();
                if (viewAttendanceModelArrayList.size() > 0)
                    viewAttendanceModelArrayList.clear();
                {
                    db.collection("StudentAttendence").whereEqualTo("Date",getDate).whereEqualTo("Lecture",getLecture2).get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                                    for (DocumentSnapshot d:list)
                                    {
                                        viewAttendanceModel obj = d.toObject(viewAttendanceModel.class);
                                        viewAttendanceModelArrayList.add(obj);

                                    }
                                    adapter.notifyDataSetChanged();
                                    if(adapter.getItemCount()==0)
                                    {
                                        Toast.makeText(viewAttendance.this,"Attendance not available for this criteria",Toast.LENGTH_LONG).show();
                                    }

                                }

                            });

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private void SetUpFirebase() {

        db = FirebaseFirestore.getInstance();
    }

    private void setUpRecyclerView() {

        mRecyclerView = findViewById(R.id.ViewRecycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void populateSpinnerLecture() {
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.lecture));
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerLecture.setAdapter(yearAdapter);
        SpinnerLecture.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}