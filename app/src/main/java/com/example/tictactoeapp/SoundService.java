package com.example.tictactoeapp;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class SoundService extends Service {
    MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {return null;}

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.caketown);
        mediaPlayer.setLooping(true); // Set looping
        mediaPlayer.setVolume(30, 30);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        mediaPlayer.start();
        //Toast.makeText(getApplicationContext(), "Playing Music Background", Toast.LENGTH_SHORT).show();
        return startId;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }



    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
