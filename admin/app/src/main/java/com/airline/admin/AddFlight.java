package com.airline.admin;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;


public class AddFlight extends AppCompatActivity implements View.OnClickListener {
    //implements View.OnClickListener
    private EditText flightName;
    private EditText flightNumber;
    private EditText dateFlight;
    private Spinner spinner1;
    private Spinner spinner2;
    private Button addFlight;
    private ProgressDialog progressDialog;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDatesetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flight);


        getSupportActionBar().setTitle("Add Flights");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDisplayDate = (TextView) findViewById(R.id.journeyDate);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(AddFlight.this
                        , android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        , mDatesetListener
                        , year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        
        mDatesetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String date = day + "-" + (month + 1) + "-" + year;
                mDisplayDate.setText(date);
            }
        };

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        flightName = (EditText) findViewById(R.id.flightName);
        flightNumber = (EditText) findViewById(R.id.flightNumber);
        dateFlight = (EditText) findViewById(R.id.journeyDate);
        addFlight = (Button) findViewById(R.id.addFlight);

        spinner1 = (Spinner) findViewById(R.id.flightFrom);
        spinner2 = (Spinner) findViewById(R.id.flightTo);


        //From
        Spinner spinner1 = findViewById(R.id.flightFrom);
        String[] items1 = new String[]{"From", "Penang", "Melaka", "Johor", "Kuala Lumpur"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        spinner1.setAdapter(adapter1);

        //To
        Spinner spinner2 = findViewById(R.id.flightTo);
        String[] items2 = new String[]{"To", "Penang", "Melaka", "Johor", "Kuala Lumpur"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        spinner2.setAdapter(adapter2);

        progressDialog = new ProgressDialog(this);
        addFlight.setOnClickListener(this);

    }


    private void addBuses() {
        String flightNameI = flightName.getText().toString().trim();
        String flightNumberI = flightNumber.getText().toString().trim();
        String date = dateFlight.getText().toString().trim();
        String from = spinner1.getSelectedItem().toString().trim();
        String to = spinner2.getSelectedItem().toString().trim();

        String airlineId = databaseReference.push().getKey();

        if (TextUtils.isEmpty(flightNameI)) {
            Toast.makeText(this, "Please Enter Travels Name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(flightNumberI)) {
            Toast.makeText(this, "Please Enter Bus Number", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(date)) {
            Toast.makeText(this, "Please Enter Journey Date", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.equals(from,"From")) {
            Toast.makeText(this, "Please Select Departure Place", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.equals(to,"To")) {
            Toast.makeText(this, "Please Select Destination Place", Toast.LENGTH_SHORT).show();
            return;
        }

        Airline airline = new Airline(airlineId, flightNameI, flightNumberI, date, from, to);

        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child("AirlineDetails").child(airlineId).setValue(airline);
        progressDialog.setMessage("Adding Flights Please Wait...");
        progressDialog.show();

        Intent intent = new Intent(getApplicationContext(),ViewAirlineActivity.class);

        startActivity(intent);
        progressDialog.dismiss();

    }


    @Override
    public void onClick(View view) {
        if (view == addFlight) {
            addBuses();
        }
    }
}
