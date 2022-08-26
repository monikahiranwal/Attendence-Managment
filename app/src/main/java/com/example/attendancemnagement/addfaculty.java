package com.example.attendancemnagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class addfaculty extends AppCompatActivity {

    private EditText editTextFirstName,editTextLastName,editTextPhone,editTextAddress,USERNAME,Password;
    private Button RegisterButton,Cancel_Button;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    int m3=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfaculty);

        init();  //function that get id from addfaculty xml file
        saveorcancel(); //function that send the code on firebase
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

                if (TextUtils.isEmpty(firstName)){
                    editTextFirstName.setError("FirstName is Required.");
                    return;
                }
                if (TextUtils.isEmpty(username)){
                    USERNAME.setError("USERNAME is Required.");
                    return;
                }
                if (TextUtils.isEmpty(address)){
                    editTextAddress.setError("Address is Required.");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Password.setError("Password is Required.");
                    return;
                }

                if (phoneNumber.length() <10){
                    editTextPhone.setError("Phone no. must be 10 digit");
                }

                //database data get start
                Map<String,String> values=new HashMap<>();
                values.put("firstName",editTextFirstName.getText().toString().trim());
                values.put("lastName",editTextLastName.getText().toString().trim());
                values.put("username",USERNAME.getText().toString().trim());
                values.put("password",Password.getText().toString().trim());
                values.put("phoneNo",editTextPhone.getText().toString().trim());
                values.put("address",editTextAddress.getText().toString().trim());
                //database data push on server start
                db.collection("AddFaculty").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            if (documentSnapshot.getString("username").equals(USERNAME.getText().toString().trim())){
                                Toast.makeText(addfaculty.this, "Teacher already exists ", Toast.LENGTH_SHORT).show();
                                m3 = 1;

                            }
                        }

                        if (m3 == 0){
                            db.collection("AddFaculty").add(values).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(addfaculty.this, "Student Successfully Registered", Toast.LENGTH_LONG).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(addfaculty.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                        }


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(addfaculty.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });




                //database data push on complete
                Intent intent = new Intent(addfaculty.this , AdminPanel.class);
                startActivity(intent);
            }
        });
        //for cancel buttom
        Cancel_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(addfaculty.this , AdminPanel.class);
                startActivity(intent);

            }
        });
    }

    private void init() {
        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextAddress = findViewById(R.id.editTextAddress) ;
        USERNAME = findViewById(R.id.USERNAME) ;
        Password = findViewById(R.id.Password) ;
        RegisterButton = findViewById(R.id.RegisterButton) ;
        Cancel_Button = findViewById(R.id.Cancel_Button) ;
    }
}