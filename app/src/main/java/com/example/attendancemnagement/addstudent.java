package com.example.attendancemnagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class addstudent extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner SpinnerCourse,SpinnerSelectYear,SpinnerDepartment;
    private EditText editTextFirstName,editTextLastName,editFatherName,editTextPhone,editTextAddress,editTextRollNo;
    private Button RegisterButton,Cancel_Button;
    String UserRole1,UserRole2,UserRole3;
    int m2=0;
    FirebaseFirestore db=FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstudent);

        init();  //function that get id from addstudent xml file
        saveorcancel(); //function that send the code on firebase




    }

    private void saveorcancel() {

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = editTextPhone.getText().toString().trim();
                String firstName = editTextFirstName.getText().toString();
                String fatherName = editFatherName.getText().toString();
                String address = editTextAddress.getText().toString();
                String rollNo = editTextRollNo.getText().toString();

                if (TextUtils.isEmpty(firstName)){
                    editTextFirstName.setError("FirstName is Required.");
                    return;
                }
                if (TextUtils.isEmpty(fatherName)){
                    editFatherName.setError("Father Name is Required.");
                    return;
                }
                if (TextUtils.isEmpty(address)){
                    editTextAddress.setError("Address is Required.");
                    return;
                }
                if (TextUtils.isEmpty(rollNo)){
                    editTextRollNo.setError("Roll no. is Required.");
                    return;
                }

                if (phoneNumber.length() <10){
                    editTextPhone.setError("Phone no. must be 10 digit");
                }

                //database data get start
                Map<String,String> values=new HashMap<>();
                values.put("firstName",editTextFirstName.getText().toString().trim());
                values.put("lastName",editTextLastName.getText().toString().trim());
                values.put("fatherName",editFatherName.getText().toString().trim());
                values.put("phoneNo",editTextPhone.getText().toString().trim());
                values.put("address",editTextAddress.getText().toString().trim());
                values.put("course",SpinnerCourse.getSelectedItem().toString().trim());
                values.put("year",SpinnerSelectYear.getSelectedItem().toString().trim());
                values.put("department",SpinnerDepartment.getSelectedItem().toString().trim());
                values.put("rollNo",editTextRollNo.getText().toString().trim());
                values.put("attendcheckbox","false");
                //database data get complete
                //database data push on server start
                db.collection("AddStudent").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                            if (documentSnapshot.getString("rollNo").equals(editTextRollNo.getText().toString().trim()))
                            {
                                Toast.makeText(addstudent.this, "Student already exists ", Toast.LENGTH_SHORT).show();
                                m2 = 1;
                            }

                        }
                        if (m2 == 0){

                            db.collection("AddStudent").add(values).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(addstudent.this, "Student Successfully Registered", Toast.LENGTH_LONG).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(addstudent.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                        }



                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(addstudent.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });


                //database data push on complete
                Intent intent = new Intent(addstudent.this , AdminPanel.class);
                startActivity(intent);
            }
        });
        //for cancel buttom
        Cancel_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(addstudent.this , AdminPanel.class);
                startActivity(intent);

            }
        });
    }

    private void init() {

        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editFatherName = findViewById(R.id.editFatherName);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextAddress = findViewById(R.id.editTextAddress) ;
        editTextRollNo = findViewById(R.id.editTextRollNo);
        SpinnerCourse = findViewById(R.id.SpinnerCourse);
        SpinnerSelectYear = findViewById(R.id.SpinnerSelectYear);
        SpinnerDepartment = findViewById(R.id.SpinnerDepartment);
        RegisterButton = findViewById(R.id.RegisterButton);
        Cancel_Button = findViewById(R.id.Cancel_Button);


        populateSpinnerSelectYear();
        SpinnerSelectYear.setOnItemSelectedListener(this);
        UserRole3 = (String) SpinnerSelectYear.getSelectedItem();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.course, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerCourse.setAdapter(adapter);
        SpinnerCourse.setOnItemSelectedListener(this);
        UserRole1 =(String) SpinnerCourse.getSelectedItem();

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.department, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerDepartment.setAdapter(adapter2);
        SpinnerDepartment.setOnItemSelectedListener(this);
        UserRole2 =(String) SpinnerDepartment.getSelectedItem();
    }

    private void populateSpinnerSelectYear() {
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.year));
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerSelectYear.setAdapter(yearAdapter);
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