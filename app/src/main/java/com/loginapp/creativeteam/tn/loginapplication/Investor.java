package com.loginapp.creativeteam.tn.loginapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class Investor extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mUsersReference;
    private String objectid,uid;
    private double money,interest;
    private Button invest_button;
    private EditText invest_money,profit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investor);

        invest_button=findViewById(R.id.invest_button);
        invest_money=findViewById(R.id.invest_money);
        profit=findViewById(R.id.profit);
        Intent intent = getIntent();
         objectid = intent.getStringExtra("objectid");
        uid = intent.getStringExtra("uid");
        invest_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                money = Double.parseDouble(invest_money.getText().toString().trim());
                interest = Double.parseDouble(profit.getText().toString().trim());
                if (money > 0 && interest > 0) {

                    mAuth = FirebaseAuth.getInstance();
                    mUsersReference = FirebaseDatabase.getInstance().getReference("client");
                    mUsersReference.child(uid).child("investor").child(mAuth.getCurrentUser().getUid()).child("money").setValue(money);
                    mUsersReference.child(uid).child("investor").child(mAuth.getCurrentUser().getUid()).child("interest").setValue(interest);
                    mUsersReference.child(uid).child("investor").child(mAuth.getCurrentUser().getUid()).child("uid").setValue(mAuth.getCurrentUser().getUid());
                    mUsersReference.child(uid).child("investor").child(mAuth.getCurrentUser().getUid()).child("investor_name").setValue(ParseUser.getCurrentUser().getUsername());

                    mUsersReference.child(uid).child("investor").child(mAuth.getCurrentUser().getUid()).child("objectid").setValue(ParseUser.getCurrentUser().getObjectId());

                    Toast.makeText(getApplicationContext(),"Investment Successful",Toast.LENGTH_SHORT).show();

                    ParseUser parseUser = ParseUser.getCurrentUser();
                    String type = parseUser.getString("type");
                    if( type.equals("client")){
                        Intent intent = new Intent(Investor.this, UserHome.class);
                        startActivity(intent);
                        finish();
                    }
                    else if(type.equals("server")){
                        Intent intent = new Intent(Investor.this, ServerHome.class);
                        startActivity(intent);
                        finish();
                    }



                }
            }
        });}}