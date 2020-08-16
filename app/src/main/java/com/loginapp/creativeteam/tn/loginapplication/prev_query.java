package com.loginapp.creativeteam.tn.loginapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.parse.ParseUser;

public class prev_query extends AppCompatActivity {
Button button1,button2;
    private FirebaseAuth mAuth;
    private DatabaseReference mUsersReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prev_query);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        final ParseUser parseUser = new ParseUser().getCurrentUser();
        mAuth = FirebaseAuth.getInstance();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseUser.put("type", "server");
                mUsersReference = FirebaseDatabase.getInstance().getReference("server");
                mUsersReference.child(mAuth.getCurrentUser().getUid()).child("name").setValue(parseUser.getString("name"));
                mUsersReference.child(mAuth.getCurrentUser().getUid()).child("objectid").setValue(parseUser.getObjectId());

                Intent intent = new Intent(prev_query.this,Business1Activity.class);
                startActivity(intent);
                finish();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseUser.put("type", "client");
                mUsersReference = FirebaseDatabase.getInstance().getReference("client");
                mUsersReference.child(mAuth.getCurrentUser().getUid()).child("name").setValue(parseUser.getString("name"));
                mUsersReference.child(mAuth.getCurrentUser().getUid()).child("objectid").setValue(parseUser.getObjectId());
                Intent intent = new Intent(prev_query.this,entraprenure1.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
