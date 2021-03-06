package com.airline.myapplication;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PayableActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private Button buttonPay;
    TextView totalCost;
    TextView totalSeat;
    private TextView airlineName,airlineDate,airlineCabinClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payable);

        firebaseAuth= FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        getSupportActionBar().setTitle("You Can Pay");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        airlineName = (TextView)findViewById(R.id.airlineName2);
        airlineDate = (TextView)findViewById(R.id.airlineDate2);
        airlineCabinClass = (TextView)findViewById(R.id.airlineCabinClass2);

        totalCost = (TextView)findViewById(R.id.totalCostFinal);
        totalSeat = (TextView)findViewById(R.id.totalSeatsFinal);

        final String total=getIntent().getStringExtra("TOTALCOST");
        final String seats=getIntent().getStringExtra("TOTALSEAT");

        final String nameAirline=getIntent().getStringExtra("NAME_AIRLINE");
        final String dateAirline=getIntent().getStringExtra("DATE_AIRLINE");
        final String cabinClassAirline=getIntent().getStringExtra("CABINCLASS_AIRLINE");

        airlineName.setText(nameAirline);
        airlineDate.setText(dateAirline);
        airlineCabinClass.setText(cabinClassAirline);

        totalCost.setText("You Should Pay : RM"+total);
        totalSeat.setText("Number Of Seats : "+seats);

        buttonPay = (Button)findViewById(R.id.payButton);
        buttonPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String total_cost = totalCost.getText().toString().trim();
                String total_seats = totalSeat.getText().toString().trim();


                Intent intent=new Intent(PayableActivity.this,PayActivity.class);
                intent.putExtra("TOTALCOSTI",total_cost);

                intent.putExtra("NAME_AIRLINE",nameAirline);
                intent.putExtra("DATE_AIRLINE",dateAirline);
                intent.putExtra("CABINCLASS_AIRLINE",cabinClassAirline);
                startActivity(intent);
            }
        });
    }
}
