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

import java.util.HashMap;
import java.util.Map;

public class addhod extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText editTextFirstName, editTextLastName, editTextPhone, editTextAddress, USERNAME, Password;
    private Button RegisterButton, Cancel_Button;
    private Spinner SpinnerDepartment;
    String UserRole1;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addhod);

        init();
        saveorcancel();


    }

    private void saveorcancel() {

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = editTextPhone.getText().toString().trim();
                String firstName = editTextFirstName.getText().toString();
                String username = USERNAME.getText().toString();
                String address = editTextAddress.getText().toString();
                String password = Password.getText().toString();

                if (TextUtils.isEmpty(firstName)) {
                    editTextFirstName.setError("FirstName is Required.");
                    return;
                }
                if (TextUtils.isEmpty(username)) {
                    USERNAME.setError("USERNAME is Required.");
                    return;
                }
                if (TextUtils.isEmpty(address)) {
                    editTextAddress.setError("Address is Required.");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Password.setError("Password is Required.");
                    return;
                }

                if (phoneNumber.length() < 10) {
                    editTextPhone.setError("Phone no. must be 10 digit");
                }

                //database data get start
                Map<String, String> values = new HashMap<>();
                values.put("firstName", editTextFirstName.getText().toString());
                values.put("lastName", editTextLastName.getText().toString());
                values.put("username", USERNAME.getText().toString());
                values.put("password", Password.getText().toString());
                values.put("phoneNo", editTextPhone.getText().toString());
                values.put("address", editTextAddress.getText().toString());
                values.put("department", SpinnerDepartment.getSelectedItem().toString());
                //database data push on server start
                db.collection("AddHod").add(values).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(addhod.this, "Student Successfully Registered", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(addhod.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
                //database data push on complete
                Intent intent = new Intent(addhod.this, AdminPanel.class);
                startActivity(intent);
            }
        });
        //for cancel buttom
        Cancel_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(addhod.this, AdminPanel.class);
                startActivity(intent);

            }
        });
    }

    private void init() {

        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextAddress = findViewById(R.id.editTextAddress);
        USERNAME = findViewById(R.id.USERNAME);
        Password = findViewById(R.id.Password);
        SpinnerDepartment = findViewById(R.id.SpinnerDepartment);
        RegisterButton = findViewById(R.id.RegisterButton);
        Cancel_Button = findViewById(R.id.Cancel_Button);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.course, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerDepartment.setAdapter(adapter);
        SpinnerDepartment.setOnItemSelectedListener(this);
        UserRole1 = (String) SpinnerDepartment.getSelectedItem();
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