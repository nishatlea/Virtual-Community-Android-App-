package com.loginapp.creativeteam.tn.loginapplication;

import android.app.ProgressDialog;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.loginapp.creativeteam.tn.loginapplication.firebase_login_signup.SignUpActivity;
import com.parse.ParsePush;
import com.parse.ParseUser;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    TextView tvName, tvEmail;
    Button nav_button;
    Button ques,server,user_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final ParseUser currentUser = ParseUser.getCurrentUser();
        mAuth = FirebaseAuth.getInstance();
        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        ques = findViewById(R.id.button_ques);
        user_home = findViewById(R.id.user_home);
        server = findViewById(R.id.server);
        if (currentUser != null) {
            tvName.setText(currentUser.getString("name"));
            tvEmail.setText(currentUser.getEmail());
        }
        nav_button = findViewById(R.id.nav_button);
        nav_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, QueryActivity
                        .class);
                startActivity(intent);
                finish();
            }
        });
        ques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Intent intent = new Intent(HomeActivity.this, SignUpActivity.class);
                // startActivity(intent);

                JSONObject data = new JSONObject();
// Put data in the JSON object
                try {
                    data.put("alert", "Back4App Rocks!");
                    data.put("title", "Hello from Device");
                } catch (JSONException e) {
                    // should not happen
                    throw new IllegalArgumentException("unexpected parsing error", e);
                }
// Configure the push
                ParsePush push = new ParsePush();
                push.setChannel("News");
                push.setData(data);
                push.sendInBackground();
                // Call to destroy an activity
                Intent myIntent = new Intent(HomeActivity.this, UsersDisplay.class);
                startActivity(myIntent);
                finish();
            }
        });
        server.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(HomeActivity.this, ServerDisplay.class);
                startActivity(myIntent);
                finish();
            }
        });
        user_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(HomeActivity.this, UserHome.class);
                startActivity(myIntent);
                finish();
            }
        });
    }

    public void logout(View view) {

        ParseUser.logOut();







            mAuth.signOut();


        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();

    }
}
