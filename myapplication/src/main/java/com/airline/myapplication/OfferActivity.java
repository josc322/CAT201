package com.airline.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.airline.myapplication.R;

public class OfferActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);

        getSupportActionBar().setTitle("Offers Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
