package com.loginapp.creativeteam.tn.loginapplication;

import android.app.ProgressDialog;
import android.content.Intent;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import androidx.appcompat.app.AppCompatActivity;

public class Business1Activity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mUsersReference;
    EditText organization_name,cmarket,net_profit_mergin,ctype,gross_profit_mergin,field_of_interest,installment_criteria,installment_method,loan_duration,loan_amount,loan_interest;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business1);
        installment_method = findViewById(R.id.installment_method);
        loan_amount = findViewById(R.id.loan_amount);
        loan_interest = findViewById(R.id.loan_interest);
        organization_name = findViewById(R.id.organization_name);
       loan_duration = findViewById(R.id.loan_duration);
        cmarket = findViewById(R.id.cmarket);
        installment_criteria=findViewById(R.id.installment_criteria);
        net_profit_mergin = findViewById(R.id.net_profit_mergin);
        gross_profit_mergin=findViewById(R.id.gross_profit_mergin);
        field_of_interest=findViewById(R.id.field_of_interest);
        ctype = findViewById(R.id.ctype);
        next = findViewById(R.id.cnext_button);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(organization_name.getText())) {
                    organization_name.setError(" organization Name is required!");
                } else if (TextUtils.isEmpty(cmarket.getText())) {
                    cmarket.setError("market strategy  is required!");
                } else if (TextUtils.isEmpty(net_profit_mergin.getText())) {
                    net_profit_mergin.setError("net profit margin is required!");

                }
                else if (TextUtils.isEmpty(installment_criteria.getText())) {
                    installment_criteria.setError("installment criteria is required!");

                }
                else if (TextUtils.isEmpty(field_of_interest.getText())) {
                   field_of_interest.setError("Address is required!");

                }
                else if (TextUtils.isEmpty(gross_profit_mergin.getText())) {
                    gross_profit_mergin.setError("field of interest is required!");

                }

                else if (TextUtils.isEmpty(ctype.getText())) {
                    ctype.setError("type is required!");
                } else {
                    mAuth = FirebaseAuth.getInstance();
                    mUsersReference = FirebaseDatabase.getInstance().getReference("server");

                    ParseUser user = ParseUser.getCurrentUser();
                    user.put("organization_name", organization_name.getText().toString().trim());
                    user.put("loan_duration", loan_duration.getText().toString().trim());
                    user.put("loan_amount", loan_amount.getText().toString().trim());
                    user.put("loan_interest",loan_interest.getText().toString().trim());
                    user.put("installment_method", installment_method.getText().toString().trim());
                    user.put("field_of_interest", field_of_interest.getText().toString());
                    user.put("installment_criteria", installment_criteria.getText().toString());
                    user.put("gross_profit_mergin", gross_profit_mergin.getText().toString());
                    user.put("net_profit_mergin", net_profit_mergin.getText().toString());
                    user.put("cmarket",cmarket.getText().toString().trim());
                    user.put("ctype", ctype.getText().toString());
                    mUsersReference.child(mAuth.getCurrentUser().getUid()).child("organization_name").setValue(user.getString("organization_name"));
                    mUsersReference.child(mAuth.getCurrentUser().getUid()).child("field_of_interest").setValue(user.getString("field_of_interest"));

                    mUsersReference.child(mAuth.getCurrentUser().getUid()).child("uid").setValue(mAuth.getCurrentUser().getUid());
                    mUsersReference.child(mAuth.getCurrentUser().getUid()).child("email").setValue(user.getEmail());


                    user.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {

                            if (e == null) {
                                Toast.makeText(Business1Activity.this, "Welcome!", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Business1Activity.this,ProfileActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                ParseUser.logOut();
                                Toast.makeText(Business1Activity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }

            }


        });


    }}
