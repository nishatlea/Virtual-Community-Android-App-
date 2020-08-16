package com.loginapp.creativeteam.tn.loginapplication.notify;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.loginapp.creativeteam.tn.loginapplication.BloqueryActivity;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class SendActivity extends AppCompatActivity {



    private String mUserId;
    private String mUserName;
    private String mCurrentId;


    private FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFirestore = FirebaseFirestore.getInstance();
        mCurrentId = FirebaseAuth.getInstance().getUid();

        mUserId = getIntent().getStringExtra("user_id");
        mUserName = getIntent().getStringExtra("user_name");




                    Map<String, Object> notificationMessage = new HashMap<>();
                    notificationMessage.put("message", mUserName);
                    notificationMessage.put("from", mCurrentId);


                    mFirestore.collection("Users/" + mUserId + "/Notifications").add(notificationMessage).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {

                            Toast.makeText(SendActivity.this, "Notification Sent.", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(SendActivity.this, BloqueryActivity.class);

                            startActivity(intent);
                            finish();



                        }
                    });

                }

            }




