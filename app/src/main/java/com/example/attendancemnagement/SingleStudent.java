package com.example.attendancemnagement;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class SingleStudent extends AppCompatActivity {
    private TextView firstName,fatherName,rollNo,phoneNo,address,course,department,year;
    private Button attendanceDetail;
    String getRollno ;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_student);
        getRollno = getIntent().getExtras().getString("keyname1");
        firstName = findViewById(R.id.studentName);
        fatherName = findViewById(R.id.studentDataFather);
        rollNo = findViewById(R.id.studentDataRollNo);
        phoneNo = findViewById(R.id.studentDataPhoneNO);
        address = findViewById(R.id.studentDataAddress);
        course = findViewById(R.id.studentDataCourse);
        department = findViewById(R.id.studentDataDepartment);
        year = findViewById(R.id.studentDataYear);
        attendanceDetail = findViewById(R.id.attendanceDetail);
        firebaseFirestore = FirebaseFirestore.getInstance();

        firebaseFirestore.collection("AddStudent").whereEqualTo("rollNo",getRollno).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot documentSnapshots, @Nullable FirebaseFirestoreException e) {
                for (DocumentSnapshot snapshot : documentSnapshots){
                    String firstName2 = snapshot.getString("firstName");
                    String lastName2 = snapshot.getString("lastName");
                    String fullName = firstName2+" "+lastName2;
                    firstName.setText(fullName.toUpperCase());

                    String fatherName2 = snapshot.getString("fatherName");
                    fatherName.setText(fatherName2.toUpperCase());

                    String rollNo2 = snapshot.getString("rollNo");
                    rollNo.setText(rollNo2.toUpperCase());

                    String phoneNo2 = snapshot.getString("phoneNo");
                    phoneNo.setText(phoneNo2.toUpperCase());

                    String address2 = snapshot.getString("address");
                    address.setText(address2.toUpperCase());

                    String course2 = snapshot.getString("course");
                    course.setText(course2.toUpperCase());

                    String department2 = snapshot.getString("department");
                    department.setText(department2.toUpperCase());

                    String year2 = snapshot.getString("year");
                    year.setText(year2.toUpperCase());

                }
            }
        });

        attendanceDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(SingleStudent.this,AttendanceDetail.class);
                intent.putExtra("keyname1",getRollno);
                startActivity(intent);
            }
        });
    }
}