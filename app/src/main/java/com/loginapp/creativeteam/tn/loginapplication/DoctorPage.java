package com.loginapp.creativeteam.tn.loginapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class DoctorPage extends AppCompatActivity {


    Button btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_page);

        btn4=findViewById(R.id.button4);
        /*btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorPage.this,DoctorView.class);
                startActivity(intent);
                finish();
            }
        });

         */
    }
}
