package com.example.testservice;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.Service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.os.ResultReceiver;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.LogRecord;




public class NewService extends Service {

    // declaring object of MediaPlayer
    private MediaPlayer player;
    final String TAG = "atk.service";
    ArrayList<String> list_string_data;

    @Override

    // execution of service will start
    // on calling this method
    public int onStartCommand(Intent intent, int flags, int startId) {
        //action set by setAction() in activity
        String action = intent.getAction();

        Log.i(TAG, String.format("onStartCommand action = %s, flags = %d, startId = %d", action, flags, startId));
        // creating a media player which
        // will play the audio of Default
        // ringtone in android device
        if(action == null)
        {
            player = MediaPlayer.create( this, Settings.System.DEFAULT_RINGTONE_URI );

            // providing the boolean
            // value as true to play
            // the audio on loop
            player.setLooping( true );

            // starting the process
            player.start();
        }
        else if (action == "com.example.testservice.TUANKEN92")
        {
            String value = intent.getStringExtra("message"); //if it's a string you stored.
            Log.i(TAG, String.format("message = %s", value));
        }
        else  if (action == "com.example.testservice.TUANKEN922")
        {
            String value = intent.getStringExtra("URL"); //if it's a string you stored.
            Log.i(TAG, String.format("message = %s", value));


            sendBroadcastMessage();
        }



        // returns the status
        // of the program
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        list_string_data = new ArrayList<String>();
        for(int i = 0; i < 100; i++)
        {
            list_string_data.add(String.format("%d", i));
        }

        Log.i(TAG, String.format("...........onCreate function.....size of list_string_data = %d", list_string_data.size()));
    }

    @Override
    // execution of the service will
    // stop on calling this method
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, String.format("onDestroy"));
        // stopping the process
        player.stop();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, String.format("onBind"));
        return null;
    }


    private void sendBroadcastMessage() {
        Intent intent = new Intent();
        final int random = new Random().nextInt(10000);
        intent.setAction("SOME_ACTION");
        intent.putExtra("alo", random);
        intent.putExtra("mylist", list_string_data);
        sendBroadcast(intent);
    }

}


