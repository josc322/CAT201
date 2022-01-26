package com.airline.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.airline.myapplication.R;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        getSupportActionBar().setTitle("Help ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}