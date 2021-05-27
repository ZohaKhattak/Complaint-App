package com.example.complaint_app;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class NavigationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    CardView complaint_form, my_complaints, notifications, help;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar);
        complaint_form=findViewById(R.id.complaint_form);
        my_complaints=findViewById(R.id.my_complaint);
        notifications=findViewById(R.id.notifications);
        help=findViewById(R.id.help);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        navigationView.bringToFront(); // only if the selection is not making successfully on main menu
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        complaint_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NavigationActivity.this, ComplaintFormActivity.class);
                startActivity(i);
            }
        });


    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_home:
                break;
            case R.id.nav_complaintform:
                Intent intent = new Intent(NavigationActivity.this, ComplaintFormActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_mycomplaints:
                break;
            case R.id.nav_notifications:
                break;
            case R.id.nav_help:
                break;
            case R.id.nav_logout:
                Intent logout = new Intent(NavigationActivity.this,LoginActivity.class);
                startActivity(logout);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}