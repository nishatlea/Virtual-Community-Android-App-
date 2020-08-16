package com.loginapp.creativeteam.tn.loginapplication;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.loginapp.creativeteam.tn.loginapplication.adapter.AnswerAdapter;
import com.loginapp.creativeteam.tn.loginapplication.fragment.AddInputDialogFragment;
import com.loginapp.creativeteam.tn.loginapplication.model.Answer;
import com.parse.ParseUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SingleStatement extends AppCompatActivity implements
        ChildEventListener,
        AddInputDialogFragment.AddInputDialogListener {

    /* Constants */
    public static final String TAG = "SingleStatementActivity";

    private TextView mQuestionString;

    private int position;

    private AnswerAdapter mAnswerAdapter;

    private DatabaseReference mAnswersReference;
    private FirebaseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_statement);

        // Add Actionbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_single_question);
        setSupportActionBar(toolbar);

        // Get the intent data from BloqueryActivity
        String questionId = getIntent().getStringExtra("question_id_key");

        // Initialise Views
        TextView userEmail = (TextView) findViewById(R.id.text_single_question_user_email);
        mQuestionString = (TextView) findViewById(R.id.text_single_question_string);
        TextView timeStamp = (TextView) findViewById(R.id.text_single_question_time_stamp);
        TextView numAnswers = (TextView) findViewById(R.id.text_single_question_num_answers);
        ImageView userImage = (ImageView) findViewById(R.id.image_single_question_user_image);

        // Initialise Firebase stuff
        mAnswersReference = FirebaseDatabase.getInstance().getReference("comments").child(questionId);
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();

        //  mAnswersReference = FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("answers").child(questionId);

        // Set listener on Database
        mAnswersReference.addChildEventListener(this);

        /* RecyclerView stuff */
        mAnswerAdapter = new AnswerAdapter();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_answers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAnswerAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Set values for this single Question object
        mQuestionString.setText(getIntent().getStringExtra("question_string"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.single_statement, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        showEditDialog();

        return super.onOptionsItemSelected(item);
    }

    /*
     * Firebase: Required methods of ChildEventListener
     */
    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        Answer answer = dataSnapshot.getValue(Answer.class);
        mAnswerAdapter.addAnswer(answer);
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

    @Override
    public void onFinishAddInput(String inputText) {
        if (inputText.isEmpty()) {
            Toast.makeText(this, "Please enter a comment...", Toast.LENGTH_SHORT).show();
        } else {
            String userId = mCurrentUser.getUid();
            String key = mAnswersReference.push().getKey();
            String ans_email= "Comment From: "+ParseUser.getCurrentUser().getEmail();

            Answer answer = new Answer(userId, key,ans_email,inputText, (long) System.currentTimeMillis(), 0);
            mAnswersReference.child(key).setValue(answer);

            Toast.makeText(this, "Comment added!", Toast.LENGTH_SHORT).show();
        }
    }

    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        AddInputDialogFragment addInputDialogFragment = AddInputDialogFragment.newInstance("Add a Comment");
        addInputDialogFragment.show(fm, TAG);
    }
}
