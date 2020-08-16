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

public class Profile_user_click extends AppCompatActivity {
    TextView project_name,project_idea,product_description,used_technology,uniqueness,evaluation_current_market,using_procedure,product_audience,success_rate,spend_resource,required_resource,utilization_method,stock_percentage,user_profile_name;
    Button ques,invest,back_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user_click);
        ques=findViewById(R.id.button_ques);
        invest=findViewById(R.id.button_investor);
        back_home=findViewById(R.id.back_home);
        user_profile_name=findViewById(R.id.user_profile_name);
        project_name=findViewById(R.id.project_name);
        project_idea=findViewById(R.id.project_idea);
        product_description=findViewById(R.id.product_description);
        used_technology=findViewById(R.id.used_technology);
        uniqueness=findViewById(R.id.uniqueness);
        evaluation_current_market=findViewById(R.id.evaluation_current_market);
        using_procedure=findViewById(R.id.using_procedure);
        product_audience=findViewById(R.id.product_audience);
        success_rate=findViewById(R.id.success_rate);
        spend_resource=findViewById(R.id.spend_resource);
        required_resource=findViewById(R.id.required_resource);
        utilization_method=findViewById(R.id.utilization_method);
        stock_percentage=findViewById(R.id.stock_percentage);
        final String objectid =  getIntent().getStringExtra("Objectid");
        final String uid =  getIntent().getStringExtra("Uid");
        final String email =  getIntent().getStringExtra("Email");

        ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
        query.orderByDescending("updatedAt");
        query.whereEqualTo("objectId",objectid);

        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject parseUser, ParseException e) {
                if (e == null) {
                    String project_name_string=parseUser.getString("project_name");
                    String project_idea_string=parseUser.getString("project_idea");
                    String product_description_string=parseUser.getString("product_description");
                    String used_technology_string=parseUser.getString("used_technology");
                    String uniqueness_string=parseUser.getString("uniqueness");
                    String using_procedure_string=parseUser.getString("using_procedure");
                    String evaluation_current_market_string=parseUser.getString("evaluation_current_market");
                    String product_audience_string=parseUser.getString("product_audience");
                    String success_rate_string=parseUser.getString("success_rate");
                    String spend_resource_string=parseUser.getString("spend_resource");
                    String utilization_method_string=parseUser.getString("utilization_method");
                    String stock_percentage_string=parseUser.getString("stock_percentage");
                    final String name=parseUser.getString("name");

                    user_profile_name.setText(name);
                    project_name.setText(project_name_string);
                    project_idea.setText(project_idea_string);
                    product_description.setText(product_description_string);
                    used_technology.setText(used_technology_string);
                    uniqueness.setText(uniqueness_string);
                    evaluation_current_market.setText(evaluation_current_market_string);
                    using_procedure.setText(using_procedure_string);
                    product_audience.setText(product_audience_string);
                    success_rate.setText(success_rate_string);
                    spend_resource.setText(spend_resource_string);
                    utilization_method.setText(utilization_method_string);
                    stock_percentage.setText(stock_percentage_string);

                    ques.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {


                            Intent intent = new Intent(Profile_user_click.this, SendActivity.class);
                            intent.putExtra("user_name", name);
                            intent.putExtra("user_id",uid );
                            startActivity(intent);
                            finish();


                        }
                    });
                    invest.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Profile_user_click.this, Investor.class);
                            intent.putExtra("objectid", objectid);
                            intent.putExtra("uid",uid);
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
                                Intent intent = new Intent(Profile_user_click.this,ServerHome.class);
                                startActivity(intent);
                                finish();
                            }
                            else if(type.equals("client")){
                                Intent intent = new Intent(Profile_user_click.this,UserHome.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });






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
