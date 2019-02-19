package com.luan.demoservice.demoservice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

public class MyBoundService extends Service {
    MediaPlayer mediaPlayer;
    Notification notification;
    NotificationManager notificationManager;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("MyBoundService","onCreate");
        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    }

    public void startPlaying(){
        if(mediaPlayer==null){
            mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.file);
            mediaPlayer.start();
            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
            builder.setSmallIcon(R.drawable.ic_right_arrow).setContentTitle("Playing").setContentTitle("Playing Track");
            notification= builder.build();
            notificationManager.notify(100,notification);
        }
    }

    public void pause(){
        if(mediaPlayer!=null && mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            notificationManager.cancel(100);
        }
    }

    public void resume(){
        if(mediaPlayer!=null && !mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }
    }

    public class MyBinder extends Binder{
        public Service getService(){
            return MyBoundService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("MyBoundService","onBind");
        return new MyBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("MyBoundService","onUnBind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("MyBoundService","onDestroy");
    }
}
