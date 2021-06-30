package com.example.testservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class OtherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        Intent intent = getIntent();
        String value = intent.getStringExtra("key"); //if it's a string you stored.
        Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
    }
}