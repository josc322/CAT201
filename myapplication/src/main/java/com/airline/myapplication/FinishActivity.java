package com.airline.myapplication;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener; 
 
public class FinishActivity extends AppCompatActivity {
    private Button buttonHome;
    private TextView airlineName,airlineDate,airlineCabinClass; 
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        getSupportActionBar().setTitle("Booking  Finished");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        airlineName = (TextView)findViewById(R.id.airlineName2);
        airlineDate = (TextView)findViewById(R.id.airlineDate2);
        airlineCabinClass = (TextView)findViewById(R.id.airlineCabinClass2);
        buttonHome = (Button)findViewById(R.id.btnHome);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference().child(user.getUid()).child("AirlineBookingDetails");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String airlineDetailName=dataSnapshot.child("airlineName").getValue().toString();
                String airlineDetailDate=dataSnapshot.child("date").getValue().toString();
                String airlineDetailCabinClass=dataSnapshot.child("cabinClass").getValue().toString();

                airlineName.setText(airlineDetailName);
                airlineDate.setText(airlineDetailDate);
                airlineCabinClass.setText(airlineDetailCabinClass);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = "You have successfully booked your ticket!";
                startActivity(new Intent(getApplicationContext(), NavigationActivity.class));
            }
        });
    }
}
