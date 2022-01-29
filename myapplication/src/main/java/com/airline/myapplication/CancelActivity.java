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

public class CancelActivity extends AppCompatActivity {
    private TextView airlineDetailName1, airlineNumber1, airlineDetailDate1, airlineDetailFrom1, airlineDetailTo1, airlineCabinClass1;
    private TextView bookingDetailFrom1, bookingDetailTo1;
    private TextView ticketDetailNumber1, ticketDetailPrice1;
    private TextView customerDetailName1, customerDetailEmail1,customerPhone1;
    private DatabaseReference databaseReference1,databaseReference2,databaseReference3,databaseReference4;
    private FirebaseAuth firebaseAuth;
    private Button cancelBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel);

        getSupportActionBar().setTitle("Cancel Booking");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cancelBooking = (Button)findViewById(R.id.cancelBooking);

        airlineDetailName1 = (TextView)findViewById(R.id.airlineDetailName1);
        airlineNumber1 = (TextView)findViewById(R.id.airlineNumber);
        airlineCabinClass1 = (TextView)findViewById(R.id.airlineCabinClass1);
        airlineDetailDate1 = (TextView)findViewById(R.id.airlineDetailDate1);
        airlineDetailFrom1 = (TextView)findViewById(R.id.airlineDetailFrom1);
        airlineDetailTo1 = (TextView)findViewById(R.id.airlineDetailTo1);

        bookingDetailFrom1 = (TextView)findViewById(R.id.bookingDetailFrom1);
        bookingDetailTo1 = (TextView)findViewById(R.id.bookingDetailTo1);

        ticketDetailNumber1 = (TextView)findViewById(R.id.ticketDetailNumber1);
        ticketDetailPrice1 = (TextView)findViewById(R.id.ticketDetailPrice1);

        customerDetailName1 = (TextView)findViewById(R.id.customerDetailName1);
        customerDetailEmail1 = (TextView)findViewById(R.id.customerDetailEmail1);
        customerPhone1 = (TextView)findViewById(R.id.customerDetailPhone1);

        firebaseAuth = FirebaseAuth.getInstance();


        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference1= FirebaseDatabase.getInstance().getReference().child(user.getUid()).child("AirlineBookingDetails");
        databaseReference2= FirebaseDatabase.getInstance().getReference().child(user.getUid()).child("BookingDetails");
        databaseReference3= FirebaseDatabase.getInstance().getReference().child(user.getUid()).child("SeatDetails");
        databaseReference4= FirebaseDatabase.getInstance().getReference().child(user.getUid()).child("CustomerDetails");

        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String airlineDetailName = dataSnapshot.child("airlineName").getValue().toString();
                String airlineNumber = dataSnapshot.child("airlineNumber").getValue().toString();
                String airlineCabinClass = dataSnapshot.child("cabinClass").getValue().toString();
                String airlineDetailDate = dataSnapshot.child("date").getValue().toString();
                String airlineDetailFrom = dataSnapshot.child("from").getValue().toString();
                String airlineDetailTo = dataSnapshot.child("to").getValue().toString();


                airlineDetailName1.setText("" + airlineDetailName);
                airlineNumber1.setText(" Airline Number     : " + airlineNumber);
                airlineDetailDate1.setText(" Cabin Class     :  " + airlineCabinClass);
                airlineDetailFrom1.setText(" Date              :  " + airlineDetailDate);
                airlineDetailTo1.setText(" From             :  " + airlineDetailFrom);
                airlineCabinClass1.setText(" To                  :  " + airlineDetailTo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String bookingDetailFrom=dataSnapshot.child("from").getValue().toString();
                String bookingDetailTo=dataSnapshot.child("to").getValue().toString();

                bookingDetailFrom1.setText(" From            :  "+bookingDetailFrom);
                bookingDetailTo1.setText(" To                 :  "+bookingDetailTo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String ticketDetailNumber=dataSnapshot.child("total_seats").getValue().toString();
                String ticketDetailPrice=dataSnapshot.child("total_cost").getValue().toString();

                ticketDetailNumber1.setText(" Number of Seats    :  "+ticketDetailNumber);
                ticketDetailPrice1.setText(" Total Cost                :  "+ticketDetailPrice);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String customerDetailName = dataSnapshot.child("cus_name").getValue().toString();
                String customerDetailEmail = dataSnapshot.child("cus_email").getValue().toString();
                String customerDetailPhone = dataSnapshot.child("cus_phone").getValue().toString();

                customerDetailName1.setText(" Customer_Name      :  " + customerDetailName);
                customerDetailEmail1.setText(" Customer_Email       :  " + customerDetailEmail);
                customerPhone1.setText(" Customer_Phone     :  " + customerDetailPhone);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        cancelBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user1 = firebaseAuth.getCurrentUser();
                DatabaseReference databaseReferenceA= FirebaseDatabase.getInstance().getReference().child(user1.getUid());
                databaseReferenceA.removeValue();

                startActivity(new Intent(getApplicationContext(),NavigationActivity.class));
            }
        });
    }
}

