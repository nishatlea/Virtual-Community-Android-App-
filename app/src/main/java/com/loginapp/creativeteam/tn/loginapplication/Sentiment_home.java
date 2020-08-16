package com.loginapp.creativeteam.tn.loginapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.loginapp.creativeteam.tn.loginapplication.adapter.StatementAdapter;
import com.loginapp.creativeteam.tn.loginapplication.fragment.AddInputDialogFragment;
import com.loginapp.creativeteam.tn.loginapplication.model.Question;
import com.parse.ParseUser;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by namlu on 30-Jul-16.
 * <p>
 * BloqueryActivity.java is the default main screen of the app.
 */
public class Sentiment_home extends AppCompatActivity
        implements
        ChildEventListener,
       StatementAdapter.StatementAdapterDelegate,
        AddInputDialogFragment.AddInputDialogListener,
        NavigationView.OnNavigationItemSelectedListener{

    /* Constants */
    public static final String TAG = "Sentiment_home";
    public static final String EXTRA_QUESTION_ID_KEY = "question_id_key";
    public static final String EXTRA_QUESTION_STRING = "question_string";
    public static final String EXTRA_CURRENT_USER = "current_user_email";

    private StatementAdapter mQuestionAdapter;
    private RecyclerView mQueryRecyclerView;
    // Firebase stuff
    private DatabaseReference mQuestionsReference;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    // Use DrawerLayout
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    // Use Navigation options
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentiment_home);

        // Add Actionbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_bloquery);
        setSupportActionBar(toolbar);

        // Initialise Firebase objects
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();

        // Initialise the adapt er
        mQuestionAdapter = new StatementAdapter();
        // Set BloqueryActivity(this) as QuestionAdapter's delegate
        mQuestionAdapter.setStatementAdapterDelegate(this);

        // Initialise Views in the layout
        mQueryRecyclerView = (RecyclerView) findViewById(R.id.recycler_bloquery);

        // Set the layout, animator, and adapter for RecyclerView
        mQueryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mQueryRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mQueryRecyclerView.setAdapter(mQuestionAdapter);

        // Initialise database;
        mQuestionsReference = FirebaseDatabase.getInstance().getReference("statement");
        //  mQuestionsReference = FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("questions");

        // Setup event listener
        mQuestionsReference.addChildEventListener(this);

        // Initialise ActionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_bloquery);

        // Initialise mDrawerToggle and implement DrawLayout listener
        mDrawerToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /* Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View drawerView){
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }

            /* Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        // Initialise NavigationView and set Listener
        mNavigationView = (NavigationView) findViewById(R.id.navigation_items_bloquery);
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mQuestionsReference.removeEventListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sentiment, menu);

        return super.onCreateOptionsMenu(menu);
    }

    /*
     * When Actionbar is selected, respond to NavigationDrawer or Overflow menu
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add_question_bloquery) {
            showEditDialog();
        }

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*
     * When NavigationDrawerItem is selected, respond to action
     * */
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.nav_log_out) {
            Toast.makeText(this, "Log out selected", Toast.LENGTH_SHORT).show();
            mDrawerLayout.closeDrawer(mNavigationView);
            ParseUser.logOut();
            mAuth.signOut();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        return true;
    }

    /*
     * Called whenever we call invalidateOptionsMenu()
     * */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mNavigationView);
        menu.findItem(R.id.action_add_question_bloquery).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    /*
     * Firebase: Required methods of ChildEventListener
     */
    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        Question question = dataSnapshot.getValue(Question.class);
        //  Question question = dataSnapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).getValue(Question.class);

        mQuestionAdapter.addQuestion(question);
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

    /*
     * Method from implementing QuestionAdapter.QuestionAdapterDelegate
     */
    @Override
    public void onItemClicked(int position, List<Question> questions) {

        // The current Question item
        Question questionItem = questions.get(position);
        String questionId = questionItem.getQuestionId();

        Intent intent = new Intent(this, SingleStatement.class);
        intent.putExtra(EXTRA_QUESTION_ID_KEY, questionId);
        intent.putExtra(EXTRA_QUESTION_STRING, questionItem.getQuestionString());

        startActivity(intent);
    }

    @Override
    public void onFinishAddInput(String inputText) {
        if (inputText.isEmpty()) {
            Toast.makeText(this, "Please enter a question...", Toast.LENGTH_SHORT).show();
        } else {
            String key = mQuestionsReference.push().getKey();
            String userId = mCurrentUser.getUid();
            Intent intent = getIntent();
            String emailask="From: " +ParseUser.getCurrentUser().getEmail();
           // String emailreply ="Query For: "+ intent.getStringExtra("email");


            Question question = new Question(key, inputText, emailask,(long) System.currentTimeMillis(), 0, userId);
            mQuestionsReference.child(key).setValue(question);

            Toast.makeText(this, "Statement added!", Toast.LENGTH_SHORT).show();
        }
    }

    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        AddInputDialogFragment addInputDialogFragment = AddInputDialogFragment.newInstance("Write a Statement");
        addInputDialogFragment.show(fm, TAG);
    }
}
