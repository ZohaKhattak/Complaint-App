package com.example.complaint_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class SignupActivity extends AppCompatActivity {

    Button CallLogin, btn_signup;
    TextInputEditText fullname, username, email, phone, password;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        
        fullname = (TextInputEditText) findViewById(R.id.fullname);
        username = (TextInputEditText)findViewById(R.id.username);
        email = (TextInputEditText)findViewById(R.id.email);
        phone = (TextInputEditText)findViewById(R.id.phone);
        password =(TextInputEditText) findViewById(R.id.password);
        btn_signup = (Button) findViewById(R.id.btn_signup);
        CallLogin =(Button) findViewById(R.id.call_login);

        databaseHelper = new DatabaseHelper(SignupActivity.this);


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = fullname.getText().toString();
                String s2 = username.getText().toString();
                String s3 = email.getText().toString();
                String s4 = phone.getText().toString();
                String s5 = password.getText().toString();

                if ((TextUtils.isEmpty(s1)) && (TextUtils.isEmpty(s2)) && (TextUtils.isEmpty(s3)) && (TextUtils.isEmpty(s4)) && (TextUtils.isEmpty(s5))){
                    toastMessage("Please enter the required fields.");
                }
                else
                {
                    Boolean checkemail = databaseHelper.checkemail(s3);
                    Boolean checkUsername = databaseHelper.checkUsername(s2);
                    if (checkemail == true && checkUsername == true) {
                        Boolean insetData = databaseHelper.addText(s1,s2,s3,s4,s5);
                        if (insetData){
                            toastMessage("You have successfully Signed up.");
                        }
                        else {
                            toastMessage("This username or email already exist.");
                        }
                    } } }
        });
    }


    private void toastMessage(String message){
        Toast.makeText(this, message , Toast.LENGTH_SHORT).show();

    }
}