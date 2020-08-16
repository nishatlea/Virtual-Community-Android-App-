package com.loginapp.creativeteam.tn.loginapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseUser;

public class User_profile_activity extends AppCompatActivity {
    TextView name,project_name,project_idea,product_description,used_technology,uniqueness,evaluation_current_market,using_procedure,product_audience,success_rate,spend_resource,required_resource,utilization_method,stock_percentage,user_profile_name;
    Button back_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_activity);
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
back_home=findViewById(R.id.back_home);
        ParseUser parseUser=ParseUser.getCurrentUser();
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
        String name=parseUser.getString("name");
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

        back_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser parseUser = ParseUser.getCurrentUser();
                String type = parseUser.getString("type");
                if( type.equals("client")){
                    Intent intent = new Intent(User_profile_activity.this,UserHome.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}
