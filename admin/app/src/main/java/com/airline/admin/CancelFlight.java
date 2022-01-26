package com.airline.admin;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class CancelFlight extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_flight);

        getSupportActionBar().setTitle("Cancel Flight");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}

