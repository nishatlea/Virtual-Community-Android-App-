package com.loginapp.creativeteam.tn.loginapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.loginapp.creativeteam.tn.loginapplication.adapter.ListShowAdapter;
import com.loginapp.creativeteam.tn.loginapplication.model.ListItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UsersDisplay extends AppCompatActivity  {
private RecyclerView recyclerView;
private RecyclerView.Adapter adapter;
private List<ListItem> listItems;
private  String json;
     ProgressDialog progress;
private String name,project_name,project_idea,uid,email;
private float need;
   private ListItem listItem;
private String objectid;
    private DatabaseReference userRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_display);
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems=new ArrayList<>();

        userRef= FirebaseDatabase.getInstance().getReference();
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Object object = dataSnapshot.getValue(Object.class);
                json = new Gson().toJson(object);
                //new featch_data().execute(json);


               JSONObject jObject = null;
                try {
                    jObject = new JSONObject(json.trim());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONObject jsonObject= null;
                try {
                    jsonObject = jObject.getJSONObject("client");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Iterator<?> keys = jsonObject.keys();



                while( keys.hasNext() ) {


                    String key = (String)keys.next();
                    //   if ( jObject.get(key) instanceof JSONObject ) {

                    JSONObject jsonObject1= null;
                    try {
                        jsonObject1 = jsonObject.getJSONObject(key);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        name=jsonObject1.getString("name");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                   project_name=jsonObject1.optString("project_name","no status");
                    uid=jsonObject1.optString("uid","no uid");
                    email=jsonObject1.optString("email","no uid");
                    project_idea=jsonObject1.optString("project_idea","no status");
                    try {
                        objectid=jsonObject1.getString("objectid");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    listItem=new ListItem(name,project_name,project_idea,uid,objectid,email);
                    listItems.add(listItem);


                    //singleParse+="\n\n\n"+"Name: "+jsonObject1.getString("name")+"\n"+
                    // "Status: "+jsonObject1.optString("status","No status ")+"\n\n\n";
                    //}
                }
                adapter=new ListShowAdapter(listItems,getApplicationContext());
                recyclerView.setAdapter(adapter);





            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        /*for(int i=0;i<=10;i++){
            ListItem listItem=new ListItem("name","status");
            listItems.add(listItem);
        }*/


               // progress.dismiss();





    }
/*
private void loadRecycleViewData(){
    ProgressDialog progressDialog=new ProgressDialog(this);
    progressDialog.setMessage("loading data...");
    progressDialog.show();
    StringRequest stringRequest=new StringRequest(Request.Method.GET, json, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            JSONObject jObject = null;
            try {
                jObject = new JSONObject(json.trim());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JSONObject jsonObject= null;
            try {
                jsonObject = jObject.getJSONObject("users");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Iterator<?> keys = jsonObject.keys();

            while( keys.hasNext() ) {


                String key = (String)keys.next();
                //   if ( jObject.get(key) instanceof JSONObject ) {

                JSONObject jsonObject1= null;
                try {
                    jsonObject1 = jsonObject.getJSONObject(key);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    name=jsonObject1.getString("name");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                status=jsonObject1.optString("status","no status");
                try {
                    need= (float) jsonObject1.getDouble("need");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                objectid=jsonObject1.optString("objectid","null");
                listItem=new ListItem(name,status,need,objectid);
                listItems.add(listItem);



        } adapter=new ListShowAdapter(listItems,getApplicationContext());
            recyclerView.setAdapter(adapter);
    }});*/

    }





