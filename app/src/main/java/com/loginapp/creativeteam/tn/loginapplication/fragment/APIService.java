package com.loginapp.creativeteam.tn.loginapplication.fragment;

//import com.loginapp.creativeteam.tn.loginapplication.MyResponse;
import com.loginapp.creativeteam.tn.loginapplication.Notifications.MyResponse;
import com.loginapp.creativeteam.tn.loginapplication.Notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=ADD HERE YOUR KEY FROM FIREBASE PROJECT"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
