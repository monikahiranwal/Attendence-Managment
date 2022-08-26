package com.example.attendancemnagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminPanel extends AppCompatActivity implements View.OnClickListener {
    private CardView addStudent,addFaculty,viewStudent,viewFaculty,attendencePerStudent,addHod;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        addStudent = (CardView) findViewById(R.id.addStudent);
        addFaculty = (CardView) findViewById(R.id.addFaculty);
        viewStudent = (CardView) findViewById(R.id.viewStudent);
        viewFaculty = (CardView) findViewById(R.id.viewFaculty);
        attendencePerStudent = (CardView) findViewById(R.id.attendencePerStudent);
        addHod = (CardView) findViewById(R.id.addHod);

        addStudent.setOnClickListener(this);
        addFaculty.setOnClickListener(this);
        viewStudent.setOnClickListener(this);
        viewFaculty.setOnClickListener(this);
        attendencePerStudent.setOnClickListener(this);
        addHod.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch ((view.getId())) {
            case R.id.addStudent : i = new Intent(this,addstudent.class);startActivity(i); break;
            case R.id.addFaculty : i = new Intent(this,addfaculty.class);startActivity(i); break;
            case R.id.viewStudent : i = new Intent(this,viewstudentperameter.class);startActivity(i); break;
            case R.id.viewFaculty : i = new Intent(this,viewfaculty.class);startActivity(i); break;
            case R.id.attendencePerStudent : i = new Intent(this,ViewTotalAttendance.class);startActivity(i); break;
            case R.id.addHod : i = new Intent(this,addhod.class);startActivity(i); break;

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}