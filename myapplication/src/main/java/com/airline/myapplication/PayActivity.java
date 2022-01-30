package com.airline.myapplication;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PayActivity extends AppCompatActivity {
    private Button buttonOffer;
    private Button buttonCredit;
    private Button buttonDebit;
    private Button buttonOnlineBanking;
    private Button buttonEWallet;
    private TextView textViewTotal;
    private TextView airlineName,airlineDate,airlineCabinClass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        getSupportActionBar().setTitle("Pay Information");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buttonCredit = (Button) findViewById(R.id.buttonCredit);
        buttonDebit = (Button) findViewById(R.id.buttonDebit);
        buttonOnlineBanking = (Button) findViewById(R.id.buttonOnlineBanking);
        buttonEWallet = (Button) findViewById(R.id.buttonEWallet);

        textViewTotal=(TextView)findViewById(R.id.textViewTotal);  
        airlineName = (TextView)findViewById(R.id.textView1); 
        airlineDate = (TextView)findViewById(R.id.textView2);
        airlineCabinClass = (TextView)findViewById(R.id.textView3);

        final String nameAirline = getIntent().getStringExtra("NAME_AIRLINE");
        final String dateAirline = getIntent().getStringExtra("DATE_AIRLINE");
        final String cabinClassAirline = getIntent().getStringExtra("CABINCLASS_AIRLINE");

        airlineName.setText(nameAirline);
        airlineDate.setText(dateAirline);
        airlineCabinClass.setText(cabinClassAirline);

        String total=getIntent().getStringExtra("TOTALCOSTI");
        textViewTotal.setText(total);

        buttonCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PayActivity.this, CreditActivity.class);
                intent.putExtra("NAME_AIRLINE",nameAirline);
                intent.putExtra("DATE_AIRLINE",dateAirline);
                intent.putExtra("CABINCLASS_AIRLINE",cabinClassAirline);
                startActivity(intent);
            }
        });

        buttonDebit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PayActivity.this, CreditActivity.class);
                intent.putExtra("NAME_AIRLINE",nameAirline);
                intent.putExtra("DATE_AIRLINE",dateAirline);
                intent.putExtra("CABINCLASS_AIRLINE",cabinClassAirline);
                startActivity(intent);
            }
        });

        buttonOnlineBanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PayActivity.this, CreditActivity.class);
                intent.putExtra("NAME_AIRLINE",nameAirline);
                intent.putExtra("DATE_AIRLINE",dateAirline);
                intent.putExtra("CABINCLASS_AIRLINE",cabinClassAirline);
                startActivity(intent);
            }
        });

        buttonEWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PayActivity.this,CreditActivity.class);
                intent.putExtra("NAME_AIRLINE",nameAirline);
                intent.putExtra("DATE_AIRLINE",dateAirline);
                intent.putExtra("CABINCLASS_AIRLINE",cabinClassAirline);
                startActivity(intent);
            }
        });

    }
}
