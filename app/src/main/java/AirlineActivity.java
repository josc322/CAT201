package com.example.inthujan.airline;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class AirlineActivity extends AppCompatActivity implements ItemClickListener, View.OnClickListener{

        private RecyclerView recyclerView;
        private AirlineAdapter adapter;
        private List<Airline> AirlineList;
        private DatabaseReference dbBuses;
        private FirebaseAuth firebaseAuth;
        private ProgressDialog progressDialog;
        private DatabaseReference databaseReference;
        private Button btnSelect;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_airline);

                getSupportActionBar().setTitle("Select The Your Desired Airlines");
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);}

        @Override
        public void onClick(View view, int position) {
                Airline airline = AirlineList.get(position);

                String airlineId=airline.getAirlineId();
                String airlineName=airline.getAirlineName();
                String cabinClass=airline.getCabinClass();
                String date=airline.getDate();
                String from=airline.getFrom();
                String to=airline.getTo();

                //Airline AirlineDetail=new Airline(airlineId,airlineName,cabinClass,date,from,to);
                //FirebaseUser user1=firebaseAuth.getCurrentUser();
                //databaseReference.child(user1.getUid()).child("AirlineBookingDetails").setValue(AirlineDetail);

                Toast.makeText(getApplicationContext(),""+airlineName,Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(AirlineActivity.this,SeatActivity.class);
                intent.putExtra("NAME_AIRLINE",airlineName);
                intent.putExtra("DATE_AIRLINE",date);
                startActivity(intent);
        }

        @Override
        public void onClick(View view) {
                if (view == btnSelect) {
                        startActivity(new Intent(this,SeatActivity.class));
                }
        }

                /*recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                AirlineList = new ArrayList<>();
                adapter = new AirlineAdapter(this, AirlineList);
                recyclerView.setAdapter(adapter);
                adapter.setClickListener(this);

                firebaseAuth = FirebaseAuth.getInstance();
                databaseReference = FirebaseDatabase.getInstance().getReference();

                String fromAirline=getIntent().getStringExtra("FROM_AIRLINE");
                final String toAirline=getIntent().getStringExtra("TO_AIRLINE");
                final String dateAirline=getIntent().getStringExtra("DATE_AIRLINE");

                FirebaseDatabase.getInstance().getReference("AirlineDetail")
                        .orderByChild("from")
                        .equalTo(fromAirline)
                        .addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        AirlineList.clear();
                                        if (dataSnapshot.exists()) {
                                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                                        Airline airline = snapshot.getValue(Airline.class);
                                                        AirlineList.add(airline);
                                                }
                                                adapter.notifyDataSetChanged();
                                        }
                                        FirebaseDatabase.getInstance().getReference()
                                                .child("AirlineDetail")
                                                .orderByChild("to")
                                                .equalTo(toAirline)
                                                .addValueEventListener(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                                AirlineList.clear();
                                                                if (dataSnapshot.exists()) {
                                                                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                                                                Airline airline = snapshot.getValue(Airline.class);
                                                                                AirlineList.add(airline);
                                                                        }
                                                                        adapter.notifyDataSetChanged();
                                                                }
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError databaseError) {
                                                                Toast.makeText(AirlineActivity.this,"Firebase Database Error",Toast.LENGTH_LONG).show();
                                                        }
                                                });

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                        Toast.makeText(AirlineActivity.this,"Firebase Database Error",Toast.LENGTH_LONG).show();
                                }
                        });

        }



        ValueEventListener valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                        AirlineList.clear();
                        if (dataSnapshot.exists()) {
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                        Airline airline = snapshot.getValue(Airline.class);
                                        AirlineList.add(airline);
                                }
                                adapter.notifyDataSetChanged();
                        }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
        };

        @Override
        public void onClick(View view, int position) {
                Airline airline = AirlineList.get(position);

                String airlineId=airline.getAirlineId();
                String airlineName=airline.getAirlineName();
                String cabinClass=airline.getCabinClass();
                String date=airline.getDate();
                String from=airline.getFrom();
                String to=airline.getTo();

                Airline AirlineDetail=new Airline(airlineId,airlineName,cabinClass,date,from,to);
                FirebaseUser user1=firebaseAuth.getCurrentUser();
                databaseReference.child(user1.getUid()).child("AirlineBookingDetails").setValue(AirlineDetail);

                Toast.makeText(getApplicationContext(),""+airlineName,Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(AirlineActivity.this,SeatActivity.class);
                intent.putExtra("NAME_AIRLINE",airlineName);
                intent.putExtra("DATE_AIRLINE",date);
                startActivity(intent);


        }
*/}