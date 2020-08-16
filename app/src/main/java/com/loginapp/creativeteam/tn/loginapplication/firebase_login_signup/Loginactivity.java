package com.loginapp.creativeteam.tn.loginapplication.firebase_login_signup;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.loginapp.creativeteam.tn.loginapplication.BloqueryActivity;
import com.loginapp.creativeteam.tn.loginapplication.HomeActivity;
import com.loginapp.creativeteam.tn.loginapplication.QueryActivity;
import com.loginapp.creativeteam.tn.loginapplication.R;
import com.loginapp.creativeteam.tn.loginapplication.UserHome;
import com.parse.ParseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by namlu on 30-Jul-16.
 * <p>
 * Loginactivity.java handles logging in a user who already has an account.
 * <p>
 * Before:
 * User provides their account Email and Password to login
 * After:
 * If successful, user is logged into their account and is present with Main screen
 * Other:
 * If user doesn't have an account, they can be taken to SignUp screen
 */
public class Loginactivity extends AppCompatActivity {
    private static final String TAG = Loginactivity.class.getSimpleName();
    private static final int REQUEST_SIGNUP = 0;

    private String mInputEmail;
    private String mInputPassword;

    // Create reference to Firebase mAuth
    private FirebaseAuth mAuth;

    // Create reference to mAuthStateListener
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Views
        mInputEmail = ParseUser.getCurrentUser().getEmail();
        mInputPassword = ParseUser.getCurrentUser().getObjectId();

        // Buttons

        // Initialize Firebase mAuth object
        mAuth = FirebaseAuth.getInstance();

        // Create AuthStateListener to respond to changes in user signin state
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                    // Send user to main activity
                    Intent intent = new Intent(Loginactivity.this, QueryActivity.class);
                    startActivity(intent);

                    // Call to destroy an activity
                    finish();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                updateUI(user);
            }
        };

        // Listener for Login button

        // If successful, take user to initial screen.
        // Otherwise show an error
        signIn(mInputEmail, mInputPassword);
    }





    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthStateListener != null) {
            mAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

    // Sign in user
    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }

        // @Todo
        //showProgressDialog();

        // Sign in user with email and password
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user.
                        // If sign in succeeds the auth state listener will be notified
                        // and logic to handle the signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(Loginactivity.this, R.string.auth_failed, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Loginactivity.this,
                                    "Welcome back " + mAuth.getCurrentUser().getEmail(),
                                    Toast.LENGTH_SHORT).show();
                        }

                        // @Todo
                        //hideProgressDialog();
                    }
                });
    }

    // @Todo
    private void updateUI(FirebaseUser user) {
        // @Todo
        //hideProgressDialog();
        if (user != null) {

        } else {

        }
    }

    // @Todo
    private boolean validateForm() {
        boolean valid = true;


        return valid;
    }
}
