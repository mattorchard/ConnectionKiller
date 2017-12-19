package com.orchard.seg.connectionkiller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private JobManager mJobManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mJobManager = new JobManager();

        Button startButton = findViewById(R.id.btn_main_start_service);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mJobManager.startJob(MainActivity.this);
            }
        });

        Button stopButton = findViewById(R.id.btn_main_stop_service);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mJobManager.stopJob(MainActivity.this);
            }
        });
    }
}
