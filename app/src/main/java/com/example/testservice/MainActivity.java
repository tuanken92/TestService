package com.example.testservice;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "atk.main";
    // declaring objects of Button class
    private Button start, stop, new_view, test_intent, test_intent_result;

    private BroadcastReceiver broadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // assigning ID of startButton
        // to the object start
        start = (Button) findViewById( R.id.startButton );

        // assigning ID of stopButton
        // to the object stop
        stop = (Button) findViewById( R.id.stopButton );

        new_view = (Button) findViewById(R.id.btnOpenView);

        test_intent = (Button)findViewById(R.id.btnTestIntent);

        test_intent_result = (Button) findViewById(R.id.btnTestIntentResult);
        // declaring listeners for the
        // buttons to make them respond
        // correctly according to the process
        start.setOnClickListener( this );
        stop.setOnClickListener( this );
        new_view.setOnClickListener(this);
        test_intent.setOnClickListener(this);
        test_intent_result.setOnClickListener(this);


        IntentFilter filter = new IntentFilter();
        filter.addAction("SOME_ACTION");

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //do something based on the intent's action
                int arg1 = intent.getIntExtra("alo",0);

                ArrayList<String> list_string_data = (ArrayList<String>) intent.getSerializableExtra("mylist");
                String str_data ="";
                for(int i = 0; i < list_string_data.size(); i++)
                {
                    str_data = str_data + list_string_data.get(i);
                }

                Toast.makeText(getApplicationContext(), str_data, Toast.LENGTH_SHORT).show();
            }
        };
        registerReceiver(broadcastReceiver, filter);


    }

    @Override
    protected void onDestroy() {
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
            broadcastReceiver = null;
        }
        super.onDestroy();
    }

    public static String random() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = generator.nextInt(30);
        char tempChar;
        for (int i = 0; i < randomLength; i++){
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }

    public void onClick(View view) {

        // process to be performed
        // if start button is clicked
        if(view == start){

            // starting the service
            Log.i(TAG, String.format("start service"));
            startService(new Intent( this, NewService.class ) );
        }

        // process to be performed
        // if stop button is clicked
        else if (view == stop){

            // stopping the service
            Log.i(TAG, String.format("Stop service"));
            stopService(new Intent( this, NewService.class ) );

        }
        else if (view == new_view)
        {
            String text = random();
            Log.i(TAG, String.format("Send key = %s", text));
            Intent myIntent = new Intent(this, OtherActivity.class);
            myIntent.putExtra("key", text); //Optional parameters
            this.startActivity(myIntent);
        }
        else if(view == test_intent)
        {
            String myAction = "com.example.testservice.TUANKEN92";
            String text = random();

            Intent myIntent = new Intent(this, NewService.class);
            myIntent.setAction(myAction);
            myIntent.putExtra("message", text); //Optional parameters
            startService(myIntent );
        }
        else if(view == test_intent_result)
        {
            String myAction = "com.example.testservice.TUANKEN922";
            Intent sfIntent = new Intent(this,NewService.class);


            //bundle
            Bundle bundle = new Bundle();
            bundle.putInt("Int", 1992);

            sfIntent.setAction(myAction);
            sfIntent.putExtras(bundle);
            sfIntent.putExtra("URL","https://github.com/");
            startService(sfIntent);
            Log.i(TAG, String.format("test_intent_result"));
        }
    }

}

