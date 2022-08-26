package com.example.attendancemnagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class studentAttend extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private RecyclerView mRecyclerView;
    private String UserRole4, UserRole5, UserRole6, getDate, getDay, getMonth, getYear;
    public String getLecture;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference StudentAttendance = db.collection("StudentAttendence");
    private Spinner SpinnerLecture;
    private TextView textViewDate;
    private Button Submit;
    private CheckBox checkBox3;
    int m1 = 0;
    ArrayList<Attendencemodel> attendenceModelArrayList;
    attendViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentattend);


        Submit = findViewById(R.id.submit);
        SpinnerLecture = findViewById(R.id.SpinnerLecture);
        textViewDate = findViewById(R.id.textviewdate);
        checkBox3 = findViewById(R.id.attendcheckbox);
        UserRole4 = getIntent().getExtras().getString("keyname1");  //course
        UserRole5 = getIntent().getExtras().getString("keyname2");  //department
        UserRole6 = getIntent().getExtras().getString("keyname3");  //year
        getDate = getIntent().getExtras().getString("keyname4");//date
        getDay = getIntent().getExtras().getString("keyname5");
        getMonth = getIntent().getExtras().getString("keyname6");
        getYear = getIntent().getExtras().getString("keyname7");

        // Toast.makeText(studentattend.this,""+getDay2 +getMonth2 +getYear2 +getLacture,Toast.LENGTH_SHORT).show();
        textViewDate.setText(getDate);
        populateSpinnerLecture();
        attendenceModelArrayList = new ArrayList<>();
        setUpRecyclerView();
        SetUpFirebase();
        loadDataFromFirebase();

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLecture = SpinnerLecture.getSelectedItem().toString();
                for (int i = 0; i < attendenceModelArrayList.size(); i++) {
                    Attendencemodel attendencemodel = attendenceModelArrayList.get(i);
                    String Rollno = attendencemodel.getRollNo();
                    String name = attendencemodel.getFirstName();
                    String Fathername = attendencemodel.getFatherName();
                    String persent = attendencemodel.getAttendcheckbox();
                    String Date = getDate.toString();
                    Map<String, String> values = new HashMap<>();
                    values.put("rollNo", Rollno.toString().trim());
                    values.put("firstName", name.toString().trim());
                    values.put("fatherName", Fathername.toString().trim());
                    values.put("attendence", persent.toString().trim());
                    values.put("Date",Date);
                    values.put("Lecture",getLecture);
                    //database data push on server start
                    StudentAttendance.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                                if (documentSnapshot.getString("Date").equals(getDate.toString().trim()))
                                {
                                    if((documentSnapshot.getString("Lecture").equals(getLecture.toString().trim())))
                                    {
                                        Toast.makeText(studentAttend.this, "Attendance already exists ", Toast.LENGTH_SHORT).show();
                                        m1 = 1;
                                    }
                                }
                            }
                                 if (m1 == 0)
                                {
                                    StudentAttendance.add(values).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            Toast.makeText(studentAttend.this, "Attendance Successfully Submit", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(studentAttend.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }



                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(studentAttend.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });




                }
                //database data push on complete
                Intent intent = new Intent(studentAttend.this, teacherpanel.class);
                startActivity(intent);

            }
        });


    }


    private void loadDataFromFirebase() {
        if (attendenceModelArrayList.size() > 0)
            attendenceModelArrayList.clear();
        db.collection("AddStudent").orderBy("firstName", Query.Direction.ASCENDING).orderBy("rollNo", Query.Direction.ASCENDING)
                .whereEqualTo("course", UserRole4).whereEqualTo("department", UserRole5).whereEqualTo("year", UserRole6)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot querySnapshot : task.getResult()) {
                            Attendencemodel attendencemodel = new Attendencemodel(querySnapshot.getString("rollNo"),
                                    querySnapshot.getString("firstName"),
                                    querySnapshot.getString("fatherName"),
                                    querySnapshot.getString("attendcheckbox"));
                            attendenceModelArrayList.add(attendencemodel);          //all data present
                        }

                        adapter = new attendViewAdapter(studentAttend.this, attendenceModelArrayList);
                        mRecyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(studentAttend.this, "problem=====1====", Toast.LENGTH_SHORT).show();
                Log.v("===1====", e.getMessage());
            }
        });
    }

    private void SetUpFirebase() {

        db = FirebaseFirestore.getInstance();
    }

    private void setUpRecyclerView() {
        mRecyclerView = findViewById(R.id.recycler2);
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

        // ((TextView) adapterView.getChildAt(0)).setTextColor(Color.GRAY);
        //    ((TextView) adapterView.getChildAt(0)).setTextSize(20);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}