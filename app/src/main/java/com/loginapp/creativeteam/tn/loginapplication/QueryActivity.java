package com.loginapp.creativeteam.tn.loginapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class QueryActivity extends Activity {
    private DatabaseReference ref;
    private FirebaseAuth mAuth;
    Button business;
    Button button4;
    Button bt2,bt3,bt5;
    String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        business = findViewById(R.id.button1);
        business.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            ParseUser parseUser = ParseUser.getCurrentUser();
                                            String flag = parseUser.getString("flag");
                                            String type = parseUser.getString("type");

                                             if(flag.equals("l") && type.equals("client")){
                                                Intent intent = new Intent(QueryActivity.this,UserHome.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                            else if(flag.equals("l") && type.equals("server")){
                                                Intent intent = new Intent(QueryActivity.this, ServerHome.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                            else
                                                {

                                                Intent intent = new Intent(QueryActivity.this, prev_query.class);
                                                startActivity(intent);
                                                finish();

                                                 }
                                        }


                                    });



        bt3 = findViewById(R.id.button3);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final  ParseUser parseUser1 = new ParseUser().getCurrentUser();
                ref = FirebaseDatabase.getInstance().getReference("checktype").child("objectid").child(parseUser1.getObjectId());
                ref.addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                 if(!dataSnapshot.exists()) {
                                     Log.d("TAG", "data not exists");
                                     Intent intent = new Intent(QueryActivity.this,HealthSignUp.class);
                                     startActivity(intent);
                                     finish();

                                 }
                                 else {
                                        Log.d("TAG", "data  exists");
                                     String value = (String) dataSnapshot.getValue();

                                     if(  String.valueOf(value).equals("d"))
                                     {
                                         Log.d("TAG", "doctor  exists");
                                         Intent intent = new Intent(QueryActivity.this,PatientProfile.class);
                                         startActivity(intent);
                                         finish();
                                     }
                                     else if( String.valueOf(value).equals("p") )
                                     {
                                         Log.d("TAG", "patient  exists");
                                         Intent intent = new Intent(QueryActivity.this, PatientProfile.class);
                                         startActivity(intent);
                                         finish();
                                     }


                                 }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Log.d("TAG", "database error");

                            }
                        });


            }
        });


        button4=findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QueryActivity.this,Sentiment_home.class);
                startActivity(intent);
                finish();
            }
        });







    }


}
