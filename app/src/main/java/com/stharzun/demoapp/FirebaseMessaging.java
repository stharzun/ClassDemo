package com.stharzun.demoapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;


import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Date;

/**
 * Created by Arjun Shrestha on 5/15/19.
 * arjunshrestha.com.np
 * stharzun@gmail.com
 */
public class FirebaseMessaging extends FirebaseMessagingService {
    private static final String NOTIFICATION_CHANNEL_ID = "classDemo.notification";
    private static final String NOTIFICATION_CHANNEL_NAME = "classDemo.notification_channel";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivities(this, 0, new Intent[]{intent}, PendingIntent.FLAG_UPDATE_CURRENT);
        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        //payload check
        String title = remoteMessage.getData().get("title");
        String body = remoteMessage.getData().get("body");
        String url = remoteMessage.getData().get("url");
        String image = remoteMessage.getData().get("image");

        NotificationCompat.Builder noBuilder = new NotificationCompat.Builder(this);
        noBuilder.setContentTitle(title);
        noBuilder.setContentText(body);
        noBuilder.setAutoCancel(true);
        noBuilder.setSmallIcon(R.mipmap.ic_launcher);
        noBuilder.setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL);
        noBuilder.setSound(defaultSound);
        noBuilder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    NOTIFICATION_CHANNEL_NAME,
                    importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            noBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        assert notificationManager != null;
        notificationManager.notify((int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE), noBuilder.build());
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        System.out.println("DeviceToken"+s);
        //sharedPreference save
    }
}
