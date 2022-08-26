package com.example.attendancemnagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

public class ViewTotalAttendance extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText search;
    private ImageButton searchButton;
    private RecyclerView mfirestoreList;
    private Button back2;
    public String searchText,totalLecture2,totalPresent2,totalAbsent2,totalPercentage2;
    private Spinner SpinnerLecture;
    private FirebaseFirestore firebaseFirestore;
    private TextView totalLecture,totalPresent,totalAbsent,totalPercentage;

    SearchAdapter adapter;
    ArrayList<searchModel> searchModelArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_total_attendance);
        search = findViewById(R.id.search);
        searchButton = findViewById(R.id.imageButton);
        SpinnerLecture = findViewById(R.id.searchLecture);
        mfirestoreList= findViewById(R.id.TotalRecycler);
        totalLecture=findViewById(R.id.TotalLectureValue);
        totalPresent=findViewById(R.id.TotalPresentValue);
        totalAbsent=findViewById(R.id.TotalAbsentValue);
        totalPercentage=findViewById(R.id.PercentageValue);
        back2 = findViewById(R.id.back2);
        populateSpinnerLecture();
        searchModelArrayList = new ArrayList<>();
        adapter = new SearchAdapter(searchModelArrayList);

        firebaseFirestore = FirebaseFirestore.getInstance();
        setupFirebase();
        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              finish();
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchText = search.getText().toString();
                String getLecture = SpinnerLecture.getSelectedItem().toString();
                if (searchModelArrayList.size() > 0)
                    searchModelArrayList.clear();
                firebaseFirestore.collection("StudentAttendence").whereEqualTo("rollNo",searchText).whereEqualTo("Lecture",getLecture)
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                                for (DocumentSnapshot d:list)
                                {
                                    searchModel obj = d.toObject(searchModel.class);
                                    searchModelArrayList.add(obj);

                                }
                                adapter.notifyDataSetChanged();
                                totalLecture2=String.valueOf(adapter.getItemCount());
                                totalLecture.setText(totalLecture2);
                                int b=0,c=0;
                                for(int i=0;i<searchModelArrayList.size();i++){
                                    String a =searchModelArrayList.get(i).getAttendence();
                                    if (a.equals("false")){
                                        b=b+1;
                                    }
                                    else{
                                        c=c+1;
                                    }
                                }

                                totalPresent2=String.valueOf(c);
                                totalPresent.setText(totalPresent2);

                                totalAbsent2=String.valueOf(b);
                                totalAbsent.setText(totalAbsent2);

                                totalPercentage2=String.valueOf((c*100)/adapter.getItemCount());
                                totalPercentage.setText(totalPercentage2);

                            }

                        });
            }
        });





    }

    private void populateSpinnerLecture() {

        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.lecture));
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerLecture.setAdapter(yearAdapter);
        SpinnerLecture.setOnItemSelectedListener(this);
    }

    private void setupFirebase() {
        mfirestoreList.setHasFixedSize(true);
        mfirestoreList.setLayoutManager(new LinearLayoutManager(this));
        mfirestoreList.setAdapter(adapter);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}