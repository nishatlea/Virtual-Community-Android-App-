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

public class entraprenure1 extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private DatabaseReference mUsersReference;
    EditText name,project_name,project_idea,product_description,used_technology,uniqueness,evaluation_current_market,using_procedure,product_audience,success_rate,spend_resource,required_resource,utilization_method,stock_percentage;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entraprenure1);
       project_name=findViewById(R.id.project_name);
        project_idea=findViewById(R.id.project_idea);
        product_description=findViewById(R.id.product_description);
        used_technology=findViewById(R.id.used_technology);
        uniqueness=findViewById(R.id.uniqueness);
        evaluation_current_market=findViewById(R.id.evaluation_current_market);
        using_procedure=findViewById(R.id.using_procedure);
        product_audience=findViewById(R.id.product_audience);
        success_rate=findViewById(R.id.success_rate);
        spend_resource=findViewById(R.id.spend_resource);
        required_resource=findViewById(R.id.required_resource);
        utilization_method=findViewById(R.id.utilization_method);
        stock_percentage=findViewById(R.id.stock_percentage);


        next = findViewById(R.id.cnext_button);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(project_name.getText())) {
                   project_name.setError(" organization Name is required!");
                } else if (TextUtils.isEmpty(project_idea.getText())) {
                   project_idea.setError("market strategy  is required!");
                } else if (TextUtils.isEmpty(product_description.getText())) {
                    product_description.setError("net profit margin is required!");

                }
                else if (TextUtils.isEmpty(uniqueness.getText())) {
                    uniqueness.setError("installment criteria is required!");

                }
                else if (TextUtils.isEmpty(evaluation_current_market.getText())) {
                    evaluation_current_market.setError("Address is required!");

                }
                else if (TextUtils.isEmpty(using_procedure.getText())) {
                    using_procedure.setError("field of interest is required!");

                }

                else if (TextUtils.isEmpty(success_rate.getText())) {
                    success_rate.setError("type is required!");
                } else {
                    mAuth = FirebaseAuth.getInstance();
                    mUsersReference = FirebaseDatabase.getInstance().getReference("client");

                    ParseUser user = ParseUser.getCurrentUser();
                    user.put("project_name",project_name.getText().toString().trim());
                    user.put("project_idea",  project_idea.getText().toString().trim());
                    user.put("product_description",  product_description.getText().toString().trim());
                    user.put("used_technology", used_technology.getText().toString().trim());
                    user.put("uniqueness",uniqueness.getText().toString().trim());
                    user.put("product_audience",  product_audience.getText().toString().trim());
                    user.put("success_rate",success_rate.getText().toString());
                    user.put("spend_resource", spend_resource.getText().toString());
                    user.put("using_procedure",  using_procedure.getText().toString());
                    user.put("required_resource",required_resource.getText().toString());
                    user.put("utilization_method", utilization_method.getText().toString());
                    user.put("stock_percentage",stock_percentage.getText().toString().trim());
                    user.put("evaluation_current_market",  evaluation_current_market.getText().toString());

                    mUsersReference.child(mAuth.getCurrentUser().getUid()).child("project_name").setValue(user.getString("project_name"));
                    mUsersReference.child(mAuth.getCurrentUser().getUid()).child("project_idea").setValue(user.getString("project_idea"));
                    mUsersReference.child(mAuth.getCurrentUser().getUid()).child("uid").setValue(mAuth.getCurrentUser().getUid());
                    mUsersReference.child(mAuth.getCurrentUser().getUid()).child("email").setValue(user.getEmail());


                    user.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {

                            if (e == null) {
                                Toast.makeText(entraprenure1.this, "Welcome!", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(entraprenure1.this,User_profile_activity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                ParseUser.logOut();
                                Toast.makeText(entraprenure1.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }

            }


        });


    }}
