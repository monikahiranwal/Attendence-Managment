package com.example.attendancemnagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class viewstudentperameter extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner SpinnerCourse,SpinnerSelectYear,SpinnerDepartment;
    private String UserRole1,UserRole2,UserRole3,UserRole4,UserRole5,UserRole6;
    private Button viewStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewstudentperameter);

        SpinnerCourse = findViewById(R.id.SpinnerCourse2);
        SpinnerSelectYear = findViewById(R.id.SpinnerSelectYear2);
        SpinnerDepartment = findViewById(R.id.SpinnerDepartment2);
        viewStudents = findViewById(R.id.viewStudents);
        populateSpinnerYear();
        populateSpinnerDepartment();
        populateSpinnerCourse();



        viewStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserRole1 =SpinnerCourse.getSelectedItem().toString();
                UserRole2 =SpinnerDepartment.getSelectedItem().toString();
                UserRole3 =SpinnerSelectYear.getSelectedItem().toString();
                Intent intent =new Intent(viewstudentperameter.this,viewstudent.class);
                intent.putExtra("keyname1",UserRole1);
                intent.putExtra("keyname2",UserRole2);
                intent.putExtra("keyname3",UserRole3);
                startActivity(intent);
            }
        });
    }

    private void populateSpinnerCourse() {

        ArrayAdapter<String> courseAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.course));
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerCourse.setAdapter(courseAdapter);
        SpinnerCourse.setOnItemSelectedListener(this);
    }

    private void populateSpinnerDepartment() {

        ArrayAdapter<String> departmentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.department));
        departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerDepartment.setAdapter(departmentAdapter);
        SpinnerDepartment.setOnItemSelectedListener(this);


    }

    private void populateSpinnerYear() {
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.year));
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerSelectYear.setAdapter(yearAdapter);
        SpinnerSelectYear.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        ((TextView) adapterView.getChildAt(0)).setTextColor(Color.GRAY);
        ((TextView) adapterView.getChildAt(0)).setTextSize(20);

        String choice = adapterView.getItemAtPosition(i).toString();
        //Toast.makeText(getApplicationContext(),choice,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}