package com.example.attendancemnagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class teacherpanel extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner SpinnerCourse3,SpinnerSelectYear3,SpinnerDepartment3;
    private String UserRole4,UserRole5,UserRole6,passdate;
    private String passDay,passMonth,passYear,Year2,Month2,Day2;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private Button fetchStudents3,viewAttendance3,viewTotalAttendance3;
    private static final String TAG = "teacherpanel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacherpanel);

        SpinnerCourse3 = findViewById(R.id.SpinnerCourse3);
        SpinnerSelectYear3 = findViewById(R.id.SpinnerSelectYear3);
        SpinnerDepartment3 = findViewById(R.id.SpinnerDepartment3);
        fetchStudents3 = findViewById(R.id.fetchStudents3);
        viewAttendance3 = findViewById(R.id.viewAttendance3);
        viewTotalAttendance3 = findViewById(R.id.viewTotalAttendence3);
        mDisplayDate = (TextView) findViewById(R.id.date);
        dateDisplay();
        populateSpinnerYear();
        populateSpinnerDepartment();
        populateSpinnerCourse();


        fetchStudents3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserRole4 =SpinnerCourse3.getSelectedItem().toString();
                UserRole5 =SpinnerDepartment3.getSelectedItem().toString();
                UserRole6 =SpinnerSelectYear3.getSelectedItem().toString();
                passdate = mDisplayDate.getText().toString();
                String mDisplayDate2 = mDisplayDate.getText().toString();
                passDay = Day2;
                passMonth = Month2;
                passYear = Year2;


                if (TextUtils.isEmpty(mDisplayDate2))
                {
                    mDisplayDate.setError("Date is Required");
                    Toast.makeText(teacherpanel.this,"Date is required",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent =new Intent(teacherpanel.this, studentAttend.class);
                intent.putExtra("keyname1",UserRole4);
                intent.putExtra("keyname2",UserRole5);
                intent.putExtra("keyname3",UserRole6);
                intent.putExtra("keyname4",passdate);
                intent.putExtra("keyname5",passDay);
                intent.putExtra("keyname6",passMonth);
                intent.putExtra("keyname7",passYear);
                startActivity(intent);
            }
        });
        viewAttendance3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserRole4 =SpinnerCourse3.getSelectedItem().toString();
                UserRole5 =SpinnerDepartment3.getSelectedItem().toString();
                UserRole6 =SpinnerSelectYear3.getSelectedItem().toString();
                passdate = mDisplayDate.getText().toString();
                String mDisplayDate2 = mDisplayDate.getText().toString();
                passDay = Day2;
                passMonth = Month2;
                passYear = Year2;
                if (TextUtils.isEmpty(mDisplayDate2))
                {
                    mDisplayDate.setError("Date is Required");
                    Toast.makeText(teacherpanel.this,"Date is required",Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent =new Intent(teacherpanel.this, viewAttendance.class);
                intent.putExtra("keyname1",UserRole4);
                intent.putExtra("keyname2",UserRole5);
                intent.putExtra("keyname3",UserRole6);
                intent.putExtra("keyname4",passdate);
                intent.putExtra("keyname5",passDay);
                intent.putExtra("keyname6",passMonth);
                intent.putExtra("keyname7",passYear);
                startActivity(intent);}
        });

        viewTotalAttendance3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserRole4 =SpinnerCourse3.getSelectedItem().toString();
                UserRole5 =SpinnerDepartment3.getSelectedItem().toString();
                UserRole6 =SpinnerSelectYear3.getSelectedItem().toString();
                passdate = mDisplayDate.getText().toString();
                passDay = Day2;
                passMonth = Month2;
                passYear = Year2;
                Intent intent =new Intent(teacherpanel.this, ViewTotalAttendance.class);
                intent.putExtra("keyname1",UserRole4);
                intent.putExtra("keyname2",UserRole5);
                intent.putExtra("keyname3",UserRole6);
                intent.putExtra("keyname4",passdate);
                intent.putExtra("keyname5",passDay);
                intent.putExtra("keyname6",passMonth);
                intent.putExtra("keyname7",passYear);
                startActivity(intent);

            }
        });
    }
    private void dateDisplay() {

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        teacherpanel.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = day + "/" + month + "/" + year;

                Day2 =String.valueOf(day);
                Month2 =String.valueOf(month);
                Year2 =String.valueOf(year);
                mDisplayDate.setText(date);
              //  Toast.makeText(teacherpanel.this, date, Toast.LENGTH_LONG).show();
            }
        };
    }

    private void populateSpinnerCourse() {
        ArrayAdapter<String> courseAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.course));
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerCourse3.setAdapter(courseAdapter);
        SpinnerCourse3.setOnItemSelectedListener(this);
    }

    private void populateSpinnerDepartment() {
        ArrayAdapter<String> departmentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.department));
        departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerDepartment3.setAdapter(departmentAdapter);
        SpinnerDepartment3.setOnItemSelectedListener(this);
    }

    private void populateSpinnerYear() {
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.year));
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerSelectYear3.setAdapter(yearAdapter);
        SpinnerSelectYear3.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        //((TextView) adapterView.getChildAt(0)).setTextColor(Color.GRAY);
     //   ((TextView) adapterView.getChildAt(0)).setTextSize(20);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent =new Intent(teacherpanel.this, LoginPage.class);
        startActivity(intent);

    }
}