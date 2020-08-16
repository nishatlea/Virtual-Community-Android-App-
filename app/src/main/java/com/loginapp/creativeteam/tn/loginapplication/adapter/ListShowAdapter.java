package com.loginapp.creativeteam.tn.loginapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ValueEventListener;
import com.loginapp.creativeteam.tn.loginapplication.HomeActivity;
import com.loginapp.creativeteam.tn.loginapplication.Profile_user_click;
import com.loginapp.creativeteam.tn.loginapplication.R;
import com.loginapp.creativeteam.tn.loginapplication.UserHome;
import com.loginapp.creativeteam.tn.loginapplication.UsersDisplay;
import com.loginapp.creativeteam.tn.loginapplication.model.ListItem;
import com.parse.ParseUser;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.core.content.ContextCompat.startActivity;
import static com.parse.Parse.getApplicationContext;

public class ListShowAdapter extends RecyclerView.Adapter<ListShowAdapter.ViewHolder>{
    private List<ListItem> listItems;
    private Context context;
private  FirebaseAuth mauth;


    public ListShowAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context=  context;
    }





    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

final ListItem listItem=listItems.get(position);
holder.name.setText(listItem.getName());

holder.project_name.setText(listItem.getProject_name());

        holder.project_idea.setText(listItem.getProject_idea());
        mauth = FirebaseAuth.getInstance();

        holder.profile_click.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      String objectid=listItem.getObjectid();
      String uid=listItem.getUid();
      String email=listItem.getEmail();

      if(uid.equals(mauth.getUid()) ){
          Intent intent = new Intent(getApplicationContext(), UserHome.class);

          context.startActivity(intent);

      }
      else{
        Intent intent = new Intent(getApplicationContext(),Profile_user_click.class);
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
        public TextView project_name,project_idea,name;

        public LinearLayout profile_click;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            project_name=itemView.findViewById(R.id.project_name);

            project_idea=itemView.findViewById(R.id.project_idea);
            name=itemView.findViewById(R.id.name);
            profile_click=itemView.findViewById(R.id.user_click);

        }
    }

}