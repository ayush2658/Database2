package com.brillicaservices.database2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Ayush on 04-07-2018.
 */

public class AddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText studentNameTF, studentPhoneTF, studentFeesTF;
    Button addStudentBtn;
    String collegeName = "";
    Spinner spinnerCollegeNames;
    String collegeNames[] = {"Select college name","DIT", "Graphic Era", "HNB"};
    Databasehelper databasehelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondactivity);
        studentNameTF = findViewById(R.id.student_name);
        studentPhoneTF = findViewById(R.id.enter_phone);
        studentFeesTF = findViewById(R.id.enter_fees);

        addStudentBtn = findViewById(R.id.add_student_button);


        spinnerCollegeNames = findViewById(R.id.college_name_spinner);

        databasehelper = new Databasehelper(this);

        spinnerCollegeNames.setOnItemSelectedListener(this);


        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, collegeNames);


        spinnerCollegeNames.setAdapter(arrayAdapter);

        spinnerCollegeNames.setPrompt(collegeNames[0]);
        addStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String name = studentNameTF.getText().toString();
                int phone = Integer.parseInt(studentPhoneTF.getText().toString());
                Double fees = Double.parseDouble(studentFeesTF.getText().toString());

                databasehelper.addNewStudent(new Studentmodel(name,
                        collegeName, fees, phone));


                Toast.makeText(getApplicationContext(), "Student data saved successfully", Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}