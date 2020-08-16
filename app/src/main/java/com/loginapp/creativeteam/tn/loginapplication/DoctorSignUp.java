package com.loginapp.creativeteam.tn.loginapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;




public class DoctorSignUp extends AppCompatActivity {

    private DatabaseReference mUsersReference;
    private FirebaseAuth mAuth;
    EditText edName, edMobile, edSpecial, edDegree;
    Button b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_sign_up);
        edName = findViewById(R.id.doctorName);
        edMobile = findViewById(R.id.doctorMobile);
        edSpecial = findViewById(R.id.doctorSpecialized);
        edDegree = findViewById(R.id.doctorDegree);
        b3 = findViewById(R.id.doctorButton);
        mAuth = FirebaseAuth.getInstance();

        //if( edName.getText()!= null && edMobile.getText()!= null && edSpecial.getText()!= null && edDegree.getText()!=null) {
    b3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (TextUtils.isEmpty(edName.getText())) {
                edName.setError("Name is required!");
            } else if (TextUtils.isEmpty(edMobile.getText())) {
                edMobile.setError("Mobile Number is required!");
            } else if (TextUtils.isEmpty(edSpecial.getText())) {
                edSpecial.setError("Password is required!");
            } else if (TextUtils.isEmpty(edDegree.getText())) {
                edDegree.setError("Confirm password is required!");
            }
            else {

                mUsersReference = FirebaseDatabase.getInstance().getReference("doctor");
                mUsersReference.child(mAuth.getCurrentUser().getUid()).child("Mobile").setValue(edMobile.getText().toString().trim());
                mUsersReference.child(mAuth.getCurrentUser().getUid()).child("Specialization").setValue(edSpecial.getText().toString().trim());
                mUsersReference.child(mAuth.getCurrentUser().getUid()).child("Degree").setValue(edDegree.getText().toString().trim());

                Intent intent = new Intent(DoctorSignUp.this, QueryActivity.class);
                Toast.makeText(DoctorSignUp.this, "Welcome!You have successfully signed up as a doctor", Toast.LENGTH_LONG).show();

                startActivity(intent);
                finish();

            }
        }

    });

}

   // }
}
