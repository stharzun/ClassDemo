package com.stharzun.demoapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import java.util.Date;

public class HomeFragment extends Fragment {

    private String NOTIFICATION_CHANNEL_ID = "com.stharzun.demoApp.id";
    private String NOTIFICATION_CHANNEL_NAME = "com.stharzun.demoApp.Notification";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "This", Toast.LENGTH_SHORT).show();
                generateNotification();
            }
        });
    }

    private void generateNotification() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivities(getContext(), 0, new Intent[]{intent}, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder noBuilder = new NotificationCompat.Builder(getContext());
        noBuilder.setContentTitle("this is title");
        noBuilder.setAutoCancel(true);
        noBuilder.setSmallIcon(R.mipmap.ic_launcher);

        noBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText("this is body  this  this tihei tiht this is body  this  this tihei tiht this is body  this  this tihei tihtthis is body  this  this tihei tihtthis is body  this  this tihei tihtthis is body  this  this tihei tiht\" +\n" +
                " this is body  this  this tihei tihtthis is body  this  this tihei tihtthis is body  this  this tihei tiht\" +\n" +
                " this is body  this  this tihei tihtthis is body  this  this tihei tihtthis is body  this  this tihei tihtthis is body  this  this tihei tiht\" +\n" +
                "                \"this is body  this  this tihei tiht"));

        noBuilder.setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL);
        noBuilder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);

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
}
