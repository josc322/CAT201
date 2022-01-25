package com.airline.admin;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewAirlineActivity extends AppCompatActivity {
    private ListView listViewAirlines;
    private DatabaseReference databaseFlights;
    private List<Airline> airlineList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_airline);

        getSupportActionBar().setTitle("All Bus Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        airlineList = new ArrayList<>();


        listViewAirlines = (ListView) findViewById(R.id.listViewAirlineDetails);
        databaseFlights = FirebaseDatabase.getInstance().getReference();
        FirebaseDatabase.getInstance().getReference("FlightDetails")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        airlineList.clear();
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Airline airline = snapshot.getValue(Airline.class);
                                airlineList.add(airline);
                            }
                            AirlineList adapter = new AirlineList(ViewAirlineActivity.this, airlineList);
                            listViewAirlines.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



        listViewAirlines.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Airline airline =airlineList.get(i);
                showUpdateDeleteDialog(airline.getAirlineId() ,airline.getAirlineName(), airline.getCabinClass(), airline.getDate(), airline.getFrom(), airline.getTo());
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();

    }

    // update ticket fees details
    private void showUpdateDeleteDialog(final String airlineId, String airlineName, String airlineNumber, String date, String from, String to){
        AlertDialog.Builder dialogBuilder =new AlertDialog.Builder(this);

        LayoutInflater inflater =getLayoutInflater();

        final View dialogView =inflater.inflate(R.layout.update_flight,null);

        dialogBuilder.setView(dialogView);

        final EditText editAirlineName  = (EditText)dialogView.findViewById(R.id.editTextAirlineName);
        final EditText editAirlineNumber = (EditText)dialogView.findViewById(R.id.editTextAirlineNumber);
        final EditText editCabinClass = (EditText)dialogView.findViewById(R.id.editTextCabinClass);
        final EditText editDate = (EditText)dialogView.findViewById(R.id.editTextJourneyDate);
        final EditText editFromAirline = (EditText) findViewById(R.id.editTextAirlineFrom);
        final EditText editToAirline = (EditText) findViewById(R.id.editTextAirlineTo);

        final Button buttonUpdate = (Button)dialogView.findViewById(R.id.buttonUpdate);
        final Button buttonDelete = (Button)dialogView.findViewById(R.id.buttonDelete);

        dialogBuilder.setTitle("Updating "+ airlineName);

        final AlertDialog alertDialog =dialogBuilder.create();
        alertDialog.show();


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String airline_1 = editAirlineName.getText().toString().trim();
                String airline_2 = editAirlineNumber.getText().toString().trim();
                String airline_3 = editCabinClass.getText().toString().trim();
                String airline_4 = editDate.getText().toString().trim();
                String airline_5 = editFromAirline.getText().toString();
                String airline_6 = editToAirline.getText().toString();


                updateFlightDetail(airlineId,airline_1, airline_2, airline_3, airline_4, airline_5, airline_6);
                alertDialog.dismiss();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFlight(airlineId);
            }
        });



    }
    private void deleteFlight(String airlineId){
        DatabaseReference drTravellingPath =FirebaseDatabase.getInstance().getReference("AirlineDetails").child(airlineId);

        drTravellingPath.removeValue();

        Toast.makeText(this, "Flight Detail Deleted Successfully", Toast.LENGTH_LONG).show();

    }
    private boolean updateFlightDetail(String airlineId, String flightNameI, String flightNumberI, String cabinClassI, String date, String from, String to){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("AirlineDetails").child(airlineId);

        Airline airline = new Airline(airlineId, flightNameI, flightNumberI, cabinClassI, date, from, to);
        databaseReference.setValue(airline);

        Toast.makeText(this, "Flight Detail Updated Successfully ", Toast.LENGTH_LONG).show();
        return true;
    }
}
