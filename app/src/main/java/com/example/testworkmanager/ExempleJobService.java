package com.example.testworkmanager;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

public class ExempleJobService extends JobService {
    public static final String TAG = "ExempleJobService";
    public boolean jobCancelled = false;
    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "Job démarré");
        doBackgroundWork(params);
        return true;
    }
    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "Job annulé");
        jobCancelled = true;
        return true;
    }
    private void doBackgroundWork(JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++){
                    Log.d(TAG, "run : "+i);
                    if(jobCancelled)
                        return;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Log.d(TAG, "job terminé");
                jobFinished(params, false);
            }
        }).start();
    }
}



