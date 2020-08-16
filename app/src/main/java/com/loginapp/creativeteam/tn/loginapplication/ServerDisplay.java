package com.loginapp.creativeteam.tn.loginapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.loginapp.creativeteam.tn.loginapplication.adapter.ListShowAdapterServer;
import com.loginapp.creativeteam.tn.loginapplication.model.ListItemServer;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ServerDisplay extends AppCompatActivity  {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItemServer> listItems;
    private  String json;
    ProgressDialog progress;
    private String name,organization_name,field_of_interest,email,uid;
    private float need;
    private ListItemServer listItem;
    private String objectid;
    private DatabaseReference userRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_display);
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems=new ArrayList<ListItemServer>();

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
                    jsonObject = jObject.getJSONObject("server");
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


                   organization_name=jsonObject1.optString("organization_name","no status");
                    field_of_interest=jsonObject1.optString("field_of_interest","no uid");
                    uid=jsonObject1.optString("uid","no uid");
                    email=jsonObject1.optString("email","no uid");

                    try {
                        objectid=jsonObject1.getString("objectid");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    listItem= new ListItemServer(name,organization_name,field_of_interest,uid,objectid,email);
                    listItems.add(listItem);


                    //singleParse+="\n\n\n"+"Name: "+jsonObject1.getString("name")+"\n"+
                    // "Status: "+jsonObject1.optString("status","No status ")+"\n\n\n";
                    //}
                }
                adapter=new ListShowAdapterServer(listItems,getApplicationContext());
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





