package edu.cuc.stephen.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class BindService extends Service{

    public boolean playing = false;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("INFO", "BindService.onBind()");
        return new MyBinder();
    }

    @Override
    public void onDestroy() {
        Log.e("INFO", "BindService.onDestroy()");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("INFO", "BindService.onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        Log.e("INFO", "BindService.onCreate()");
        super.onCreate();
    }

    public class MyBinder extends Binder {
        public BindService getService(){
            return BindService.this;
        }
    }

    public void play(){
        if (playing){
            Log.e("INFO", "暂停");
        }else {
            Log.e("INFO", "播放");
        }
        playing = !playing;
    }
    public void stop(){
        Log.e("INFO", "停止");
    }
    public void previous(){
        Log.e("INFO", "上一首");
    }
    public void next(){
        Log.e("INFO", "下一首");
    }
}
