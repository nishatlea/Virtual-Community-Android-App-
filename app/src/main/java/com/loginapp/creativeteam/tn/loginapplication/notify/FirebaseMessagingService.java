package com.loginapp.creativeteam.tn.loginapplication.notify;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;


import com.google.firebase.messaging.RemoteMessage;
import com.loginapp.creativeteam.tn.loginapplication.R;

import androidx.core.app.NotificationCompat;

/**
 * Created by AkshayeJH on 10/01/18.
 */

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String messageTitle = remoteMessage.getNotification().getTitle();
        String messageBody = remoteMessage.getNotification().getBody();

        String click_action = remoteMessage.getNotification().getClickAction();

        String dataMessage = remoteMessage.getData().get("message");
        String dataFrom = remoteMessage.getData().get("from_user_id");

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, getString(R.string.default_notification_channel_id))
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(messageTitle)
                        .setContentText(messageBody)
                        .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) this.getSystemService(
                NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel("Tvac_CH_01",  "Notification Channel", NotificationManager.IMPORTANCE_HIGH);

            notificationManager.createNotificationChannel(mChannel);
        }
        Intent resultIntent = new Intent(click_action);

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        mBuilder.setContentIntent(resultPendingIntent);

        int mNotificationId = (int) System.currentTimeMillis();

        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        mNotifyMgr.notify(mNotificationId, mBuilder.build());


    }


}
