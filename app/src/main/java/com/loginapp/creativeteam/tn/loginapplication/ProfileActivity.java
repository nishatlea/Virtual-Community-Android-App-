package com.loginapp.creativeteam.tn.loginapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.loginapp.creativeteam.tn.loginapplication.firebase_login_signup.SignUpActivity;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {
TextView organization_name,organization_type,marketing_strategy,net_profit_mergin,user_profile,gross_profit_mergin,field_of_interest,installment_criteria,installment_method,loan_duration,loan_amount,loan_interest;;
Button back_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
      organization_name=findViewById(R.id.organization_name);
        installment_method=findViewById(R.id.installment_method);
        back_home=findViewById(R.id.back_home);
        loan_amount=findViewById(R.id.loan_amount);
        loan_duration=findViewById(R.id.loan_duration);
        installment_criteria=findViewById(R.id.installment_criteria);
        field_of_interest=findViewById(R.id.field_of_interest);
      gross_profit_mergin=findViewById(R.id.gross_profit_mergin);
        loan_interest=findViewById(R.id.loan_interest);
        user_profile=findViewById(R.id.user_profile_name);
        net_profit_mergin=findViewById(R.id.net_profit_mergin);
        marketing_strategy=findViewById(R.id.marketing_strategy);
        organization_type=findViewById(R.id.organization_type);
        ParseUser parseUser=ParseUser.getCurrentUser();
        String organization_name_string=parseUser.getString("organization_name");
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
        String name=parseUser.getString("name");
        user_profile.setText(name);
        organization_name.setText(organization_name_string);
        loan_interest.setText(loan_interest_string);

        installment_method.setText(installment_method_string);
        installment_criteria.setText(installment_string);
        net_profit_mergin.setText(profit_mergin_string);
        organization_type.setText(ctype);
        gross_profit_mergin.setText(gross_profit_string);
        field_of_interest.setText(field_of_interest_string);
        marketing_strategy.setText(cmarket);
        loan_amount.setText(loan_amount_string);
        loan_duration.setText(loan_duration_string);


 back_home.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         ParseUser parseUser = ParseUser.getCurrentUser();
         String type = parseUser.getString("type");
         if( type.equals("server")){
             Intent intent = new Intent(ProfileActivity.this,ServerHome.class);
             startActivity(intent);
             finish();
         }
     }
 });
    }
}
