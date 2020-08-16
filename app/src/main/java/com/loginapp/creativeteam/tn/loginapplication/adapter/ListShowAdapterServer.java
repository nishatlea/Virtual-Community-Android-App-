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
import com.loginapp.creativeteam.tn.loginapplication.R;
import com.loginapp.creativeteam.tn.loginapplication.ServerHome;
import com.loginapp.creativeteam.tn.loginapplication.UserHome;
import com.loginapp.creativeteam.tn.loginapplication.model.ListItemInvestor;
import com.loginapp.creativeteam.tn.loginapplication.model.ListItemServer;
import com.loginapp.creativeteam.tn.loginapplication.profile_server_click;
import com.parse.Parse;
import com.parse.ParseUser;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.parse.Parse.getApplicationContext;

public class ListShowAdapterServer extends RecyclerView.Adapter<ListShowAdapterServer.ViewHolder> {
    private List<ListItemServer> listItems;
    private Context context;

    private  FirebaseAuth mauth;

    public ListShowAdapterServer(List<ListItemServer> listItems, Context context) {
        this.listItems = listItems;
        this.context=  context;
    }





    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_server,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final ListItemServer listItem=listItems.get(position);
        holder.name.setText(listItem.getName());
        holder.organization_name.setText(listItem.getOrganization_name());
        holder.field_of_interest.setText(listItem.getField_of_interest());
        mauth = FirebaseAuth.getInstance();

        holder.profile_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String objectid=listItem.getObjectid();
                String uid=listItem.getUid();
                String email=listItem.getEmail();
                if (uid.equals(mauth.getUid()) ) {
                    Intent intent = new Intent(getApplicationContext(), ServerHome.class);
                    context.startActivity(intent);
                }
                // Toast.makeText( context,objectid,Toast.LENGTH_LONG).show();
                //Toast.makeText(ListShowAdapter.this,"objectid",Toast.LENGTH_LONG).show();
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
        public TextView organization_name,field_of_interest,name;

        public LinearLayout profile_click;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            organization_name=itemView.findViewById(R.id.organization_name);

            field_of_interest=itemView.findViewById(R.id.field_of_interest);
            name=itemView.findViewById(R.id.name);
            profile_click=itemView.findViewById(R.id.user_click);


        }
    }
}