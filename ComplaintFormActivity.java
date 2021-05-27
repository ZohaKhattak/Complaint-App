package com.example.complaint_app;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class ComplaintFormActivity extends AppCompatActivity {

    private AutoCompleteTextView nature_of_complaints, desired_outcome;
    TextInputEditText fullname,specific_details,date;
    Button submit;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_form);


        nature_of_complaints=findViewById(R.id.nature_of_complaints);
        desired_outcome=findViewById(R.id.desired_outcome);
        fullname=findViewById(R.id.fullname);
        specific_details=findViewById(R.id.details);
        date= findViewById(R.id.date);

        databaseHelper = new DatabaseHelper(ComplaintFormActivity.this);


        String[] items = new String[] {"Pay related", "Staff attitude", "Excessive workload",  "Noisy environment",
                "Too hot or too cold temperature", "Hygiene related", "Workplace changes", "Harassment or bullying",
                "Internet issue", "Gadgets fault or repair", "Appliances issue", "Services complaints",
                "Mechanical services", "Other, please specify"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(ComplaintFormActivity.this, R.layout.dropdown_menu,items);
        nature_of_complaints.setAdapter(adapter);

        String[] items1 = new String[] {
                "Apology", "Promotion", "Change", "Explanation", "Fixing", "Other"
        };
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(ComplaintFormActivity.this, R.layout.dropdown_menu,items1);
        desired_outcome.setAdapter(adapter1);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = fullname.getText().toString();
                String nature = nature_of_complaints.getText().toString();
                String details = specific_details.getText().toString();
                String dated = date.getText().toString();
                String outcome = desired_outcome.getText().toString();

                if ((TextUtils.isEmpty(name)) && (TextUtils.isEmpty(nature)) && (TextUtils.isEmpty(details)) && (TextUtils.isEmpty(dated)) && (TextUtils.isEmpty(outcome))){
                    toastMessage("Please enter the required fields.");
                }
                else
                {
                    boolean insetData = databaseHelper.addComplaint(name,nature,details,dated,outcome);
                    if (insetData){
                        toastMessage("Your complaint has been submitted successfully.");
                    }
                    else {
                        toastMessage("Error! Your complaint was not submitted. Try again.");
                    } } }
        });
    }


    private void toastMessage(String message){
        Toast.makeText(this, message , Toast.LENGTH_SHORT).show();
    }

}
