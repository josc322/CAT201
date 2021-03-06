package com.airline.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfirmActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private EditText email;
    private EditText phoneNumber;
    private EditText nameCustomer;
    private EditText ageCustomer;
    private ProgressDialog progressDialog;
    private Button confirmBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        email = (EditText)findViewById(R.id.editTextEmail);
        phoneNumber = (EditText)findViewById(R.id.editTextPhoneNumber);
        nameCustomer = (EditText)findViewById(R.id.editTextName);
        ageCustomer = (EditText)findViewById(R.id.editTextAge);
        confirmBook = (Button)findViewById(R.id.btnBook);

        progressDialog = new ProgressDialog(this);
        confirmBook.setOnClickListener(this);
        databaseReference= FirebaseDatabase.getInstance().getReference();

        firebaseAuth= FirebaseAuth.getInstance();

        getSupportActionBar().setTitle("Contact Information");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    private void contactBook(){

        String customerEmail = email.getText().toString().trim();
        String customerPhone = phoneNumber.getText().toString().trim();
        String customerName = nameCustomer.getText().toString().trim();
        String customerAge = ageCustomer.getText().toString().trim();

        final String nameAirline=getIntent().getStringExtra("NAME_AIRLINE");
        final String dateAirline=getIntent().getStringExtra("DATE_AIRLINE");
        final String conditionAirline=getIntent().getStringExtra("CONDITION_AIRLINE");

        if(TextUtils.isEmpty(customerEmail)){
            //email is empty
            Toast.makeText(this,"Please enter the email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(customerPhone)){
            //password is empty
            Toast.makeText(this,"Please enter the phone number",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(customerName)){
            //customer name is empty
            Toast.makeText(this,"Please enter the customer name",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(customerAge)){
            //customer age is empty
            Toast.makeText(this,"Please enter the customer age",Toast.LENGTH_SHORT).show();
            return;
        }

        CustomerDetail customerDetail = new CustomerDetail(customerEmail,customerPhone,customerName,customerAge);

        FirebaseUser user=firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).child("CustomerDetails").setValue(customerDetail);
        progressDialog.setMessage("Updating Contact Detail Please Wait...");
        progressDialog.show();

        Intent intent = new Intent(ConfirmActivity.this,FinishActivity.class);
        startActivity(intent);
        progressDialog.dismiss();
    }

    @Override
    public void onClick(View view) {
        if (view == confirmBook) {
            contactBook();
        }
    }
}
