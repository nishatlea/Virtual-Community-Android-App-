package com.loginapp.creativeteam.tn.loginapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PatientProfile extends AppCompatActivity {
    Button bt2,bt3,bt5,bt6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);

        bt2 = findViewById(R.id.h1);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientProfile.this,MapsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        bt3 = findViewById(R.id.h2);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientProfile.this,Map2Activity.class);
                startActivity(intent);
                finish();
            }
        });

        bt5 = findViewById(R.id.h3);
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientProfile.this,DoctorMainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        bt6 = findViewById(R.id.h4);
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientProfile.this,DoctorInfo.class);
                startActivity(intent);
                finish();
            }
        });



    }
}
