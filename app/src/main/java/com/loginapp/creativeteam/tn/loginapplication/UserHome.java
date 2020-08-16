package com.loginapp.creativeteam.tn.loginapplication;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.parse.ParseUser;

import androidx.appcompat.app.AppCompatActivity;

public class UserHome extends AppCompatActivity {
    private FirebaseAuth mAuth;

    LinearLayout user_display,server_display,investor_display,logout,ques_ans_disply,profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        final ParseUser currentUser = ParseUser.getCurrentUser();
        mAuth = FirebaseAuth.getInstance();

        user_display = findViewById(R.id.user_display);
        server_display=findViewById(R.id.server_display);
        investor_display=findViewById(R.id.investor_display);
        logout=findViewById(R.id.logout);
        ques_ans_disply=findViewById(R.id.ques_ans_display);
        profile=findViewById(R.id.profile);

        user_display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(UserHome.this, UsersDisplay.class);
                startActivity(myIntent);
                //finish();
            }
        });

        server_display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(UserHome.this,ServerDisplay.class);
                startActivity(myIntent);
                //finish();
            }
        });

        investor_display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(UserHome.this, InvestorDisplay.class);
                startActivity(myIntent);
                finish();
            }
        });
ques_ans_disply.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent myIntent = new Intent(UserHome.this, BloqueryActivity.class);
        startActivity(myIntent);
        finish();
    }
});
profile.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent myIntent = new Intent(UserHome.this,User_profile_activity.class);
        startActivity(myIntent);
        finish();
    }
});
logout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        ParseUser.logOut();







        mAuth.signOut();


        Intent intent = new Intent(UserHome.this, LoginActivity.class);
        startActivity(intent);
        finish();

    }
});


    }
}
