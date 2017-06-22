package com.shellcore.android.flashstudy.service;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.shellcore.android.flashstudy.jobs.RecordatoryJobService;

/**
 * Created by Cesar on 22/06/2017.
 */

public class ReminderService {

    // Constantes
    private static final int JOB_ID = 1010;

    public static void setupReminder(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);

        String periodicityString = sp.getString("frecuency", "120000");
        long periodicity = Long.parseLong(periodicityString);
        boolean remember = sp.getBoolean("remember", true);

        if (remember) {
            JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
            jobScheduler.cancelAll();

            ComponentName jobService = new ComponentName(context.getPackageName(), RecordatoryJobService.class.getName());
            JobInfo jobInfo = new JobInfo.Builder(JOB_ID, jobService)
                    .setPeriodic(periodicity)
                    .build();
            jobScheduler.schedule(jobInfo);
        }
    }

    public static void cancelReminder(Context context) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.cancelAll();
    }
}
