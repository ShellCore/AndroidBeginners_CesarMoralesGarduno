package com.shellcore.android.flashstudy.jobs;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;

import com.shellcore.android.flashstudy.MainActivity;
import com.shellcore.android.flashstudy.R;

/**
 * Created by Cesar on 21/06/2017.
 */

public class RecordatoryJobService extends JobService {

    @Override
    public boolean onStartJob(JobParameters params) {
        showNotification();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }

    private void showNotification() {
        Intent resultIntent = new Intent(this, MainActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);

        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent( 0,
                PendingIntent.FLAG_UPDATE_CURRENT);


        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getString(R.string.app_name))
                .setContentIntent(resultPendingIntent)
                .setDefaults(Notification.DEFAULT_LIGHTS|Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE)
                .setAutoCancel(true)
                .setContentText(getString(R.string.reminder_short_message))
                .setStyle(new Notification.BigTextStyle().bigText(getString(R.string.reminder_short_message) + "\n\n" + getString(R.string.reminder_message)));

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());

    }
}
