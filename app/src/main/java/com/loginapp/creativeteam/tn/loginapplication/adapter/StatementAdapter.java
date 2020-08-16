package com.loginapp.creativeteam.tn.loginapplication.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.loginapp.creativeteam.tn.loginapplication.R;
import com.loginapp.creativeteam.tn.loginapplication.model.Question;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class StatementAdapter extends RecyclerView.Adapter<StatementAdapter.StatementAdapterViewHolder> {

    public static interface StatementAdapterDelegate {
        public void onItemClicked(int position, List<Question> queries);
    }

    /* private fields */
    private List<Question> mQuestions;

    // References to delegate objects
    private WeakReference<StatementAdapterDelegate> mDelegate;

    public StatementAdapter() {
        mQuestions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        mQuestions.add(question);
        notifyDataSetChanged();
    }

    @Override
    public StatementAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.statement_list_item, parent, false);
        return new StatementAdapterViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(StatementAdapterViewHolder holder, int position) {
        holder.update(position, mQuestions.get(position));
    }

    @Override
    public int getItemCount() {
        return mQuestions.size();
    }

    /*
     * Getters and setter methods
     */
    public StatementAdapterDelegate getQuestionAdapterDelegate() {
        if (mDelegate == null) {
            return null;
        }
        return mDelegate.get();
    }

    public void setStatementAdapterDelegate(StatementAdapterDelegate delegate) {
        mDelegate = new WeakReference<StatementAdapterDelegate>(delegate);
    }

    class StatementAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Reference to Question items
        TextView questionString;
        TextView timeStamp;
        TextView numAnswers;
        ImageView userImage;
        TextView ask;

        int position;

        public StatementAdapterViewHolder(View itemView) {

            super(itemView);
            questionString = (TextView) itemView.findViewById(R.id.text_question_string);
            timeStamp = (TextView) itemView.findViewById(R.id.text_question_time_stamp);
            numAnswers = (TextView) itemView.findViewById(R.id.text_question_number_of_answers);
            userImage = (ImageView) itemView.findViewById(R.id.image_question_user_image);
            ask=(TextView)itemView.findViewById(R.id.ask);
          //  reply=(TextView)itemView.findViewById(R.id.reply);

            itemView.setOnClickListener(this);
        }

        void update(int position, Question question) {

            this.position = position;

            questionString.setText(question.getQuestionString());
            timeStamp.setText("Submitted: " + formatDate(new Date(question.getTimeStamp())));
            numAnswers.setText("Number of answers: " + question.getNumberOfAnswers());
            ask.setText(question.getAsk());
           // reply.setText(question.getReply());

            //userImage.setImageResource(question.getUserImageResId());
        }

        // Return a formatted date string (i.e. 1 Jan, 2000 ) from a Date object.
        private String formatDate(Date date) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM, yyyy");
            return dateFormat.format(date);
        }

        // Only method of View.OnClickListener
        @Override
        public void onClick(View view) {
            if (getQuestionAdapterDelegate() != null) {
                getQuestionAdapterDelegate().onItemClicked(position, mQuestions);
            }
        }
    }
}
