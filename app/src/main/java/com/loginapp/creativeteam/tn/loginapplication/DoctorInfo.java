package com.loginapp.creativeteam.tn.loginapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DoctorInfo extends AppCompatActivity {

    private DatabaseReference reference;
    private FirebaseAuth mAuth;
    TextView myText;
    String sum,p,q,r,s,sum2="See Doctors List \n\n\n";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_info);

        reference = FirebaseDatabase.getInstance().getReference("doctor");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {


                    if (postSnapshot.child("name") !=null && postSnapshot.child("Degree") != null  && postSnapshot.child("Specialization") != null) {


                        p = (String)postSnapshot.child("name").getValue();
                        q = (String)postSnapshot.child("Degree").getValue();
                        //r = (Number)postSnapshot.child("Mobile").getValue();
                        s = (String)postSnapshot.child("Specialization").getValue();
                        //sum = "\n\n"+"Name: "+p+"\n"+"Mobile No:"+r+"\nArea of Specialization :"+s+"\n"+"Degree:"+q+"\n";
                        // }
                        if(p!=null && q!=null && s!=null) {
                            sum = "\n\n"+"Name: "+p+"\n"+"Area of Specialization :"+s+"\n"+"Degree:"+q+"\n";
                            if(sum!=null) {
                                sum2 = sum2 + sum;
                            }
                            //myText = findViewById(R.id.doctext);
                            //myText.setText(sum.toString());
                        }

                       /* if(sum!=null) {
                            myText = findViewById(R.id.doctext);
                            myText.setText(sum.toString());
                        }
                       */
                    }

                }

                if(sum2!=null) {
                    myText = findViewById(R.id.doctext);
                    myText.setText(sum2.toString());
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e("tag", "Failed to read app title value.", error.toException());
            }
        });

        //Log.e("tag", sum2);
            //sum = "\n\n"+"Name: "+p+"\n"+"Mobile No:"+r+"\nArea of Specialization :"+s+"\n"+"Degree"+q+"\n";




    }
}
