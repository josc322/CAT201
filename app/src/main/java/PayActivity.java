package com.example.inthujan.airline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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
    private TextView a,b,c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        getSupportActionBar().setTitle("Pay Information");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buttonOffer = (Button) findViewById(R.id.buttonOffer);
        buttonCredit = (Button) findViewById(R.id.buttonCredit);
        buttonDebit = (Button) findViewById(R.id.buttonDebit);
        buttonOnlineBanking = (Button) findViewById(R.id.buttonOnlineBanking);
        buttonEWallet = (Button) findViewById(R.id.buttonEWallet);

        textViewTotal=(TextView)findViewById(R.id.textViewTotal);
        a=(TextView)findViewById(R.id.textView1);
        b=(TextView)findViewById(R.id.textView2);
        c=(TextView)findViewById(R.id.textView3);

        final String nameAirline=getIntent().getStringExtra("NAME_AIRLINE");
        final String dateAirline=getIntent().getStringExtra("DATE_AIRLINE");
        final String conditionAirline=getIntent().getStringExtra("CONDITION_AIRLINE");

        a.setText(nameAirline);
        b.setText(dateAirline);
        c.setText(conditionAirline);

        String total=getIntent().getStringExtra("TOTALCOSTI");
        textViewTotal.setText(total);

        buttonOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(PayActivity.this,CreditActivity.class);
                intent.putExtra("NAME_AIRLINE",nameAirline);
                intent.putExtra("DATE_AIRLINE",dateAirline);
                intent.putExtra("CONDITION_AIRLINE",conditionAirline);
                startActivity(intent);
            }
        });

        buttonCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PayActivity.this,CreditActivity.class);
                intent.putExtra("NAME_AIRLINE",nameAirline);
                intent.putExtra("DATE_AIRLINE",dateAirline);
                intent.putExtra("CONDITION_AIRLINE",conditionAirline);
                startActivity(intent);
            }
        });

        buttonDebit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PayActivity.this,CreditActivity.class);
                intent.putExtra("NAME_AIRLINE",nameAirline);
                intent.putExtra("DATE_AIRLINE",dateAirline);
                intent.putExtra("CONDITION_AIRLINE",conditionAirline);
                startActivity(intent);
            }
        });

        buttonOnlineBanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PayActivity.this,CreditActivity.class);
                intent.putExtra("NAME_AIRLINE",nameAirline);
                intent.putExtra("DATE_AIRLINE",dateAirline);
                intent.putExtra("CONDITION_AIRLINE",conditionAirline);
                startActivity(intent);
            }
        });

        buttonEWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PayActivity.this,CreditActivity.class);
                intent.putExtra("NAME_AIRLINE",nameAirline);
                intent.putExtra("DATE_AIRLINE",dateAirline);
                intent.putExtra("CONDITION_AIRLINE",conditionAirline);
                startActivity(intent);
            }
        });

    }
}
