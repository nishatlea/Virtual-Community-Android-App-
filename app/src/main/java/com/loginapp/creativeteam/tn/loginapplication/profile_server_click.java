package com.loginapp.creativeteam.tn.loginapplication;

import androidx.appcompat.app.AppCompatActivity;
// Parse Dependencies

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loginapp.creativeteam.tn.loginapplication.notify.SendActivity;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class profile_server_click extends AppCompatActivity {
    TextView   organization_name,organization_type,marketing_strategy,net_profit_mergin,user_profile,gross_profit_mergin,field_of_interest,installment_criteria,installment_method,loan_duration,loan_amount,loan_interest,user_profile_name;
    Button ques,back_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_server_click);
        ques=findViewById(R.id.button_ques);
        back_home=findViewById(R.id.back_home);
        user_profile_name=findViewById(R.id.user_profile_name);
        organization_name=findViewById(R.id.organization_name);
        installment_method=findViewById(R.id.installment_method);
        loan_amount=findViewById(R.id.loan_amount);
        loan_duration=findViewById(R.id.loan_duration);
        installment_criteria=findViewById(R.id.installment_criteria);
        field_of_interest=findViewById(R.id.field_of_interest);
        gross_profit_mergin=findViewById(R.id.gross_profit_mergin);
        ques=findViewById(R.id.button_ques);
        loan_interest=findViewById(R.id.loan_interest);
        user_profile=findViewById(R.id.user_profile_name);
        net_profit_mergin=findViewById(R.id.net_profit_mergin);
        marketing_strategy=findViewById(R.id.marketing_strategy);
        organization_type=findViewById(R.id.organization_type);
        final String objectid =  getIntent().getStringExtra("Objectid");
        final String uid =  getIntent().getStringExtra("Uid");

        final String email =  getIntent().getStringExtra("Email");

        ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
        query.orderByDescending("updatedAt");
        query.whereEqualTo("objectId",objectid);

        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject parseUser, ParseException e) {
                if (e == null) {
                    String cname=parseUser.getString("organization_name");
                    String loan_interest_string=parseUser.getString("loan_interest");
                    String loan_amount_string=parseUser.getString("loan_amount");
                    String loan_duration_string=parseUser.getString("loan_duration");
                    String installment_method_string=parseUser.getString("installment_method");
                    String ctype=parseUser.getString("ctype");
                    String installment_string=parseUser.getString("installment_criteria");
                    String gross_profit_string=parseUser.getString("gross_profit_mergin");
                    String profit_mergin_string=parseUser.getString("net_profit_mergin");
                    String field_of_interest_string=parseUser.getString("field_of_interest");
                    String cmarket=parseUser.getString("cmarket");
                    final String name=parseUser.getString("name");

                    user_profile_name.setText(name);
                    loan_interest.setText(loan_interest_string);
                    organization_name.setText(cname);
                    installment_method.setText(installment_method_string);
                    installment_criteria.setText(installment_string);
                    net_profit_mergin.setText(profit_mergin_string);
                    organization_type.setText(ctype);
                    gross_profit_mergin.setText(gross_profit_string);
                    field_of_interest.setText(field_of_interest_string);
                    marketing_strategy.setText(cmarket);
                    loan_amount.setText(loan_amount_string);
                    loan_duration.setText(loan_duration_string);
                    ques.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(profile_server_click.this, SendActivity.class);
                            intent.putExtra("user_name", name);
                            intent.putExtra("user_id",uid );

                            startActivity(intent);
                            finish();




                        }
                    });


                    back_home.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ParseUser parseUser = ParseUser.getCurrentUser();
                            String type = parseUser.getString("type");
                            if( type.equals("server")){
                                Intent intent = new Intent(profile_server_click.this,ServerHome.class);
                                startActivity(intent);
                                finish();
                            }
                            else if(type.equals("client")){
                                Intent intent = new Intent(profile_server_click.this,UserHome.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });

                   /* invest.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(profile_server_click.this, Investor.class);
                            intent.putExtra("objectid", objectid);
                            intent.putExtra("uid",uid);
                            startActivity(intent);
                            finish();
                        }
                    });*/






                } else {
                    // Something is wrong
                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();

                }
            }
        });

   /*     ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
        query.whereEqualTo("objectId",objectid);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> object, ParseException e) {
                if (e == null) {
                    //List contain object with specific user id.
                    cname=object.getString("cname");
                    cemail=object.get()
                    caddress=object.getString("caddress");
                    cdate=object.getString("cdate");
                    name=object.getString("name");

                    user_profile.setText(name);
                    cname_id.setText(cname);
                    cemail_id.setText(cemail);
                    caddress_id.setText(caddress);
                    cdate_id.setText(cdate);
                    ques.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Profile_user_click.this, BloqueryActivity.class);
                            startActivity(intent);
                            finish();

                } else {
                    // error
                }
            }
        });*/

    }
}
