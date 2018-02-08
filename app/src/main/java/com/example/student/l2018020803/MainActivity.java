package com.example.student.l2018020803;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    JobScheduler jobScheduler;
    JobInfo jobInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        JobInfo.Builder builder = new JobInfo.Builder(1,
                new ComponentName(getPackageName(),
                        MyJobService.class.getName()));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            jobInfo = builder.setMinimumLatency(3000).build();  //7.0以上版本 至少要間隔15分以上
        } else {
            jobInfo = builder.setPeriodic(3000).build();
        }
    }

    public void click1(View v)
    {
        jobScheduler.schedule(jobInfo);
    }
    public void click2(View v)
    {
        jobScheduler.cancel(1);
    }
}
