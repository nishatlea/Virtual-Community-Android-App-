package com.loginapp.creativeteam.tn.loginapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.parse.ParseUser;



public class HealthSignUp extends AppCompatActivity {

    Button btn1,btn2;
    private FirebaseAuth mAuth;
    private DatabaseReference mUsersReference;
    private DatabaseReference ref;
    private DatabaseReference reference;
    private FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_sign_up);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
       final  ParseUser parseUser1 = new ParseUser().getCurrentUser();
        mAuth = FirebaseAuth.getInstance();

        ref = FirebaseDatabase.getInstance().getReference("checktype");
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FirebaseUser firebaseUser = auth.getCurrentUser();
                String userid = mAuth.getCurrentUser().getUid();
                String Doctorusername = parseUser1.getString("name");

                reference = FirebaseDatabase.getInstance().getReference("DoctorUsers").child(userid);
                reference.child("id").setValue(userid);
                reference.child("doctorusername").setValue("Doctor : " + Doctorusername);
                reference.child("imageURL").setValue("default");
                reference.child("status").setValue("offline");
                reference.child("search").setValue(Doctorusername.toLowerCase());

                /*HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("id", userid);
                hashMap.put("username", Doctorusername);
                hashMap.put("imageURL", "default");
                hashMap.put("status", "offline");
                hashMap.put("search", Doctorusername.toLowerCase());*/



                parseUser1.put("type1", "doctor");

                mUsersReference = FirebaseDatabase.getInstance().getReference("doctor");
                mUsersReference.child(mAuth.getCurrentUser().getUid()).child("name").setValue(parseUser1.getString("name"));
                mUsersReference.child(mAuth.getCurrentUser().getUid()).child("objectid").setValue(parseUser1.getObjectId());

                //mUsersReference.child("doctorname").setValue(parseUser1.getString("name"));
                ref.child("objectid").child(parseUser1.getObjectId()).setValue("d");


                Intent intent = new Intent(HealthSignUp.this,DoctorSignUp.class);
                startActivity(intent);
                finish();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseUser1.put("type1", "patient");
                String userid = mAuth.getCurrentUser().getUid();
                String Doctorusername = parseUser1.getString("name");
                reference = FirebaseDatabase.getInstance().getReference("DoctorUsers").child(userid);
                reference.child("id").setValue(userid);
                reference.child("doctorusername").setValue("Patient : " + Doctorusername);
                reference.child("imageURL").setValue("default");
                reference.child("status").setValue("offline");
                reference.child("search").setValue(Doctorusername.toLowerCase());

                mUsersReference = FirebaseDatabase.getInstance().getReference("patient");
                mUsersReference.child(mAuth.getCurrentUser().getUid()).child("name").setValue(parseUser1.getString("name"));
                mUsersReference.child(mAuth.getCurrentUser().getUid()).child("objectid").setValue(parseUser1.getObjectId());

                ref.child("objectid").child(parseUser1.getObjectId()).setValue("p");


                Intent intent = new Intent(HealthSignUp.this,PatientSignUp.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
