package com.example.packman.mylibrary.Services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class ScreenOrientationService extends Service {
    private static final String TAG = "ScreenOrientation";

    private static final String BCAST_CONFIGCHANGED = "android.intent.action.CONFIGURATION_CHANGED";
    private static Context mContext;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate()");

        mContext = this;

        IntentFilter filter = new IntentFilter();
        filter.addAction(BCAST_CONFIGCHANGED);
        this.registerReceiver(mBroadcastReceiver, filter);
    }

    @Override
    public void onDestroy() {

        mContext.unregisterReceiver(mBroadcastReceiver);
    }

    public BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent myIntent) {

            if ( myIntent.getAction().equals( BCAST_CONFIGCHANGED ) ) {
                if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                    Toast.makeText(getApplication(),"LANDSCAPE",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplication(),"PORTRAIT",Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
}