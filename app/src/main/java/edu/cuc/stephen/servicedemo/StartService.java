package edu.cuc.stephen.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class StartService extends Service{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("INFO", "StartService.onBind()");
        return null;
    }

    @Override
    public void onDestroy() {
        Log.e("INFO", "StartService.onDestroy()");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("INFO", "StartService.onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        Log.e("INFO", "StartService.onCreate()");
        super.onCreate();
    }
}
