package com.brillicaservices.database2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ayush on 04-07-2018.
 */

public class DisplayActivity extends AppCompatActivity {
    TextView displayStudentsResultTV;
    ArrayList<Studentmodel> studentArrayList = new ArrayList<>();
    Databasehelper databasehelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thirdactivity);
        databasehelper = new Databasehelper(this);
        displayStudentsResultTV = findViewById(R.id.display_student_details_text_view);
        studentArrayList.addAll(databasehelper.allStudentsDetails());
                for (int i=0; i<studentArrayList.size(); i++) {
                    displayStudentsResultTV.setText(displayStudentsResultTV.getText() +
                            "Student ID is: " + studentArrayList.get(i).getId() + "\n");
                    displayStudentsResultTV.setText(displayStudentsResultTV.getText() +
                            "Student Name is: " + studentArrayList.get(i).getName() + "\n");
                    displayStudentsResultTV.setText(displayStudentsResultTV.getText() +
                            "Student College is: " + studentArrayList.get(i).getCollegeName() + "\n");
                    displayStudentsResultTV.setText(displayStudentsResultTV.getText() +
                            "Student Phone Number is: " + studentArrayList.get(i).getPhoneNumber() + "\n");
                    displayStudentsResultTV.setText(displayStudentsResultTV.getText() +
                            "Student Fees is: " + studentArrayList.get(i).getFees() + "\n");
                    displayStudentsResultTV.setText(displayStudentsResultTV.getText() + "****************\n\n");

//                    Log.d(TAG, studentArrayList.get(i).studentName + "\n");
//                    Log.d(TAG, studentArrayList.get(i).studentCollege + "\n");
//                    Log.d(TAG, studentArrayList.get(i).studentPhoneNumber+ "\n");
//                    Log.d(TAG, studentArrayList.get(i).studentFees + "\n");
                }
            }

    }

