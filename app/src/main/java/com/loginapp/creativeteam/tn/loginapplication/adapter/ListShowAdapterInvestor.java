package com.loginapp.creativeteam.tn.loginapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.loginapp.creativeteam.tn.loginapplication.Profile_user_click;
import com.loginapp.creativeteam.tn.loginapplication.QueryActivity;
import com.loginapp.creativeteam.tn.loginapplication.R;
import com.loginapp.creativeteam.tn.loginapplication.ServerHome;
import com.loginapp.creativeteam.tn.loginapplication.UserHome;
import com.loginapp.creativeteam.tn.loginapplication.model.ListItem;
import com.loginapp.creativeteam.tn.loginapplication.model.ListItemInvestor;
import com.loginapp.creativeteam.tn.loginapplication.model.ListItemServer;
import com.loginapp.creativeteam.tn.loginapplication.profile_server_click;
import com.parse.ParseUser;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.parse.Parse.getApplicationContext;

public class ListShowAdapterInvestor extends RecyclerView.Adapter<ListShowAdapterInvestor.ViewHolder> {
    private List<ListItemInvestor> listItems;
    private Context context;
    private  FirebaseAuth mauth;


    public ListShowAdapterInvestor(List<ListItemInvestor> listItems, Context context) {
        this.listItems = listItems;
        this.context=  context;

    }





    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_investor,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final ListItemInvestor listItem=listItems.get(position);
        holder.investor_name.setText(listItem.getInvestor_name());
        holder.money.setText(listItem.getMoney());
        holder.interest.setText(listItem.getInterest());


        holder.profile_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String objectid=listItem.getObjectid();
                String uid=listItem.getUid();
                String email=listItem.getEmail();

                if (objectid == ParseUser.getCurrentUser().getObjectId()) {
                    Intent intent = new Intent(getApplicationContext(), ServerHome.class);

                    context.startActivity(intent);

                }

                else {

                    Intent intent = new Intent(getApplicationContext(), profile_server_click.class);
                    intent.putExtra("Objectid", objectid);
                    intent.putExtra("Uid", uid);
                    intent.putExtra("Email", email);
                    context.startActivity(intent);

                }





            }
        });


    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView investor_name,money,interest;

        public LinearLayout profile_click;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
          investor_name=itemView.findViewById(R.id.investor_name);

           money=itemView.findViewById(R.id.money);
           interest=itemView.findViewById(R.id.interest);
            profile_click=itemView.findViewById(R.id.user_click);


        }
    }
}