package com.example.inthujan.airline;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SeatActivity extends AppCompatActivity {

    GridLayout mainGrid;
    Double seatPrice = 50.00;
    Double totalCost = 0.0;
    int totalSeats = 0;
    TextView totalPrice;
    TextView totalBookedSeats;
    private Button buttonBook;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat);

        getSupportActionBar().setTitle("Select Your Favorite Seats");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        mainGrid = (GridLayout) findViewById(R.id.mainGrid);
        totalBookedSeats = (TextView) findViewById(R.id.total_seats);
        totalPrice = (TextView) findViewById(R.id.total_cost);
        buttonBook = (Button) findViewById(R.id.btnBook);

        //Set Event
        setToggleEvent(mainGrid);
        final String nameAirline=getIntent().getStringExtra("NAME_AIRLINE");
        final String dateAirline=getIntent().getStringExtra("DATE_AIRLINE");
        final String conditionAirline=getIntent().getStringExtra("CONDITION_AIRLINE");

        buttonBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String totalPriceI=totalPrice.getText().toString().trim();
                String totalBookedSeatsI=totalBookedSeats.getText().toString().trim();

                PaymentDetail paymentDetail=new PaymentDetail(totalPriceI,totalBookedSeatsI);

                FirebaseUser user=firebaseAuth.getCurrentUser();
                databaseReference.child(user.getUid()).child("SeatDetails").setValue(paymentDetail);

                Intent intent=new Intent(SeatActivity.this,PaybleActivity.class);
                intent.putExtra("TOTALCOST",totalPriceI);
                intent.putExtra("TOTALSEAT",totalBookedSeatsI);

                intent.putExtra("NAME_AIRLINE",nameAirline);
                intent.putExtra("DATE_AIRLINE",dateAirline);
                intent.putExtra("CONDITION_AIRLINE",conditionAirline);

                startActivity(intent);

            }
        });

    }


    private void setToggleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#00FF00"));
                        totalCost += seatPrice;
                        ++totalSeats;
                        Toast.makeText(SeatActivity.this, "You Selected Seat Number :" + (finalI + 1), Toast.LENGTH_SHORT).show();

                    } else {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                        totalCost -= seatPrice;
                        --totalSeats;
                        Toast.makeText(SeatActivity.this, "You Unselected Seat Number :" + (finalI + 1), Toast.LENGTH_SHORT).show();
                    }
                    totalPrice.setText("" + totalCost+"0");
                    totalBookedSeats.setText("" + totalSeats);
                }
            });
        }
    }}



