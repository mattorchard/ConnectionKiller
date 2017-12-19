package com.orchard.seg.connectionkiller;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.util.Log;

public class BackgroundJob extends JobService{
    private final static String TAG = "BackgroundJob";

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.v(TAG, "Performing job");
        disableBluetooth();
        disableWifi();
        return false;
    }

    /** Disable bluetooth if it is enabled. */
    private void disableBluetooth() {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.disable();
            Log.i(TAG, "Bluetooth disabled");
        }
    }

    /** Disable wifi if it is enabled. */
    private void disableWifi() {
        WifiManager wifiManager =
                (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifiManager != null && wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(false);
            Log.i(TAG, "Wifi disabled");
        }
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
