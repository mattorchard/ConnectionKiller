package com.orchard.seg.connectionkiller;


import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

class JobManager {
    private static final String TAG = "JobManager";

    /** Time to wait between jobs (milliseconds). */
    private static final int JOB_INTERVAL_MS = 10 * 1000;

    void startJob(Context context) {
        Log.i(TAG, "Starting job");
        JobScheduler jobScheduler = context.getSystemService(JobScheduler.class);
        if (null == jobScheduler) {
            Toast.makeText(context, R.string.error_service_scheduler, Toast.LENGTH_LONG).show();
        }else if (jobScheduler.getAllPendingJobs().size() != 0) {
            Toast.makeText(context, R.string.error_service_scheduler, Toast.LENGTH_LONG).show();
        } else {
            ComponentName serviceComponent = new ComponentName(context, BackgroundJob.class);
            JobInfo.Builder builder = new JobInfo.Builder(0, serviceComponent);
            builder.setPeriodic(JOB_INTERVAL_MS);
            JobInfo jobInfo = builder.build();
            jobScheduler.schedule(jobInfo);
            Toast.makeText(context, R.string.message_service_started, Toast.LENGTH_LONG).show();
        }
    }

    void stopJob(Context context) {
        Log.i(TAG, "Stopping jobs");
        JobScheduler jobScheduler = context.getSystemService(JobScheduler.class);
        if (null == jobScheduler) {
            Toast.makeText(context, R.string.error_service_scheduler, Toast.LENGTH_LONG).show();
        } else {
            jobScheduler.cancelAll();
            Toast.makeText(context, R.string.message_service_stopped, Toast.LENGTH_LONG).show();
        }
    }
}
