package com.airline.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class AirlineActivity extends AppCompatActivity implements ItemClickListener{

    private RecyclerView recyclerView;
    private AirlineAdapter adapter;
    private List<Airline> airlinesList;
    private DatabaseReference dbAirline;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airline);

        getSupportActionBar().setTitle("Select The Your Desired Airline");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        airlinesList = new ArrayList<>();
        adapter = new AirlineAdapter(this, airlinesList);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        String fromAirline=getIntent().getStringExtra("FROM_AIRLINE");
        final String toAirline = getIntent().getStringExtra("TO_AIRLINE");
        final String dateAirline=getIntent().getStringExtra("DATE_AIRLINE");

        FirebaseDatabase.getInstance().getReference("AirlineDetails")
                .orderByChild("from")
                .equalTo(fromAirline)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        airlinesList.clear();
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Airline airline = snapshot.getValue(Airline.class);
                                airlinesList.add(airline);
                            }
                            adapter.notifyDataSetChanged();
                        }
                        FirebaseDatabase.getInstance().getReference()
                                .child("AirlineDetails")
                                .orderByChild("to")
                                .equalTo(toAirline)
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        airlinesList.clear();
                                        if (dataSnapshot.exists()) {
                                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                                Airline airline = snapshot.getValue(Airline.class);
                                                airlinesList.add(airline);
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
            airlinesList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Airline airline = snapshot.getValue(Airline.class);
                    airlinesList.add(airline);
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
        Airline airline = airlinesList.get(position);

        String airlineId=airline.getAirlineId();
        String airlineName=airline.getAirlineName();
        String cabinClass=airline.getCabinClass();
        String date=airline.getDate();
        String from=airline.getFrom();
        String to=airline.getTo();

        Airline airlineDetail = new Airline(airlineId,airlineName,cabinClass,date,from,to);
        FirebaseUser user1=firebaseAuth.getCurrentUser();
        databaseReference.child(user1.getUid()).child("AirlineBookingDetails").setValue(airlineDetail);

        Toast.makeText(getApplicationContext(),""+airlineName,Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(AirlineActivity.this,SeatActivity.class);
        intent.putExtra("NAME_AIRLINE",airlineName);
        intent.putExtra("DATE_AIRLINE",date);
        startActivity(intent);
    }
}
