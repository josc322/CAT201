package com.example.airlinereservationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner destinationFrom = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> destinationFromAdapter = new ArrayAdapter<String>(Search.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.destinationFromNames));
        destinationFromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        destinationFrom.setAdapter(destinationFromAdapter);

        Spinner destinationTo = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> destinationToAdapter = new ArrayAdapter<String>(Search.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.destinationToNames));
        destinationToAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        destinationTo.setAdapter(destinationToAdapter);
    }
}
