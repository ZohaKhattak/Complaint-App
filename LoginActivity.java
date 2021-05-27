package com.example.complaint_app;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;


public class LoginActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    ImageView image;
    TextView logo_text, slogan_text;
    TextInputEditText username,password;
    Button login_btn,callSignUp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(this);


        image = (ImageView) findViewById(R.id.logo_image);
        logo_text= (TextView) findViewById(R.id.logo_name);
        slogan_text=(TextView) findViewById(R.id.slogan_text);
        username = (TextInputEditText) findViewById(R.id.username);
        password = (TextInputEditText) findViewById(R.id.password);
        login_btn=(Button) findViewById(R.id.btn_login);
        callSignUp = (Button) findViewById(R.id.btn_signup);


        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                Pair[] pairs = new Pair[7];
                pairs[0] = new Pair<View,String>(image,"logo_image");
                pairs[1] = new Pair<View,String>(logo_text,"logo_text");
                pairs[2] = new Pair<View,String>(slogan_text,"logo_desc");
                pairs[3] = new Pair<View,String>(username,"username_tran");
                pairs[4] = new Pair<View,String>(password,"password_tran");
                pairs[5] = new Pair<View,String>(login_btn,"button_tran");
                pairs[6] = new Pair<View,String>(callSignUp,"login_signup_tran");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this,pairs);
                    startActivity(intent,options.toBundle());
                }

            }});

        login_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                String s1 = Objects.requireNonNull(username.getText()).toString();
                String s2 = Objects.requireNonNull(password.getText()).toString();

                if (s1.equals("") || s2.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please enter the empty fields.", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean loginAuth = databaseHelper.loginAuth(s1, s2);
                    if (loginAuth == true) {
                        Toast.makeText(getApplicationContext(), "Successfully Logged in.", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(LoginActivity.this, NavigationActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(), "Wrong email or password.", Toast.LENGTH_SHORT).show();
                    }
                }
            }});






    }}