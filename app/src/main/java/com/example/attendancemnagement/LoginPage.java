package com.example.attendancemnagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class LoginPage extends AppCompatActivity {
    private Spinner spinner;
    private EditText editTextTextPersonName, editTextTextPassword;
    private Button button;
    private String userrole;
    private CheckBox mCheckBoxRemember;
    private String[] userRoleString = new String[]{"ADMIN", "TEACHER", "H.O.D", "STUDENT"};
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    int m = 0;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor Editor;
    Boolean savelogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        SavePassword();

        init();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinner.getSelectedView() != null) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.GRAY);
                ((TextView) adapterView.getChildAt(0)).setTextSize(20);}

                String choice = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(), choice, Toast.LENGTH_LONG).show();
                userrole = (String) spinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        ArrayAdapter<String> adapter_role = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, userRoleString);
        adapter_role
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter_role);


        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                final String user_name = editTextTextPersonName.getText().toString();
                final String pass_word = editTextTextPassword.getText().toString();

                if (TextUtils.isEmpty(user_name)){
                    editTextTextPersonName.setError("Username is Required.");
                    return;
                }

                if (TextUtils.isEmpty(pass_word)){
                    editTextTextPassword.setError("Password is Required.");
                    return;
                }

                if (userrole.equals("ADMIN")) {

                    db.collection("Users").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                if (documentSnapshot.getString("username").equals(user_name) && documentSnapshot.getString("password").equals(pass_word)) {
                                  loginCheckbox();

                                    Intent intent = new Intent(LoginPage.this, AdminPanel.class);
                                    startActivity(intent);
                                    Toast.makeText(LoginPage.this, "Logged in", Toast.LENGTH_LONG).show();
                                    m = 1;
                                }
                            }
                            if (m == 0) {
                                Toast.makeText(LoginPage.this, "Admin not registered", Toast.LENGTH_LONG).show();
                            }
                        }

                        private void loginCheckbox() {
                            if (mCheckBoxRemember.isChecked()){
                                Editor.putBoolean("savelogin",true);
                                Editor.putString("username",user_name);
                                Editor.putString("password",pass_word);
                                Editor.commit();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(LoginPage.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                } else if (userrole.equals("TEACHER")) {


                    db.collection("AddFaculty").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                if (documentSnapshot.getString("username").equals(user_name) && documentSnapshot.getString("password").equals(pass_word)) {
                                    if (mCheckBoxRemember.isChecked()){
                                        Editor.putBoolean("savelogin",true);
                                        Editor.putString("username",user_name);
                                        Editor.putString("password",pass_word);
                                        Editor.commit();
                                    }
                                    Intent intent = new Intent(LoginPage.this, teacherpanel.class);
                                    startActivity(intent);
                                    Toast.makeText(LoginPage.this, "Logged in", Toast.LENGTH_LONG).show();
                                    m = 1;
                                }
                            }
                            if (m == 0) {
                                Toast.makeText(LoginPage.this, "Teacher not registered", Toast.LENGTH_LONG).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(LoginPage.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

                } else if (userrole.equals("H.O.D")) {


                    db.collection("AddHod").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                if (documentSnapshot.getString("username").equals(user_name) && documentSnapshot.getString("password").equals(pass_word)) {
                                    if (mCheckBoxRemember.isChecked()){
                                        Editor.putBoolean("savelogin",true);
                                        Editor.putString("username",user_name);
                                        Editor.putString("password",pass_word);
                                        Editor.commit();
                                    }
                                    Intent intent = new Intent(LoginPage.this, ViewTotalAttendance.class);
                                    startActivity(intent);
                                    Toast.makeText(LoginPage.this, "Logged in", Toast.LENGTH_LONG).show();
                                    m = 1;
                                }
                            }
                            if (m == 0) {
                                Toast.makeText(LoginPage.this, "H.O.D not registered", Toast.LENGTH_LONG).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(LoginPage.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

                } else {

                    db.collection("AddStudent").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                if (documentSnapshot.getString("rollNo").equals(user_name) && documentSnapshot.getString("phoneNo").equals(pass_word)) {
                                    if (mCheckBoxRemember.isChecked()){
                                        Editor.putBoolean("savelogin",true);
                                        Editor.putString("username",user_name);
                                        Editor.putString("password",pass_word);
                                        Editor.commit();
                                    }

                                    Intent intent = new Intent(LoginPage.this, SingleStudent.class);
                                    intent.putExtra("keyname1",user_name);
                                    startActivity(intent);
                                    Toast.makeText(LoginPage.this, "Logged in", Toast.LENGTH_LONG).show();
                                    m = 1;
                                }
                            }
                            if (m == 0) {
                                Toast.makeText(LoginPage.this, "Student not registered", Toast.LENGTH_LONG).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(LoginPage.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

                }
//

            }
        });
        savelogin = sharedPreferences.getBoolean("savelogin",true);
        if(savelogin==true){
            editTextTextPersonName.setText(sharedPreferences.getString("username",null));
            editTextTextPassword.setText(sharedPreferences.getString("password",null));
        }






    }

    private void SavePassword() {

        mCheckBoxRemember = findViewById(R.id.checkBox);
        sharedPreferences= getSharedPreferences("loginref",MODE_PRIVATE);
        Editor = sharedPreferences.edit();
    }


    private void init() {

        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        button = findViewById(R.id.button);
        mCheckBoxRemember = findViewById(R.id.checkBox);
        spinner = findViewById(R.id.spinner);
    }


}