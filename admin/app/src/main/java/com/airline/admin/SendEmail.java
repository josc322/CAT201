package com.airline.admin;

import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SendEmail extends AppCompatActivity {
    EditText setTo,setSubject,setMessage;
    Button btSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);

        getSupportActionBar().setTitle("Send Email");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTo=(EditText)findViewById(R.id.et_to);
        setSubject=(EditText)findViewById(R.id.et_subject);
        setMessage=(EditText)findViewById(R.id.et_message);

        btSend=(Button)findViewById(R.id.btn_send);

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=  new Intent(Intent.ACTION_VIEW
                        , Uri.parse("mailto:"+setTo.getText().toString()));
                intent.putExtra(Intent.EXTRA_SUBJECT,setSubject.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT,setMessage.getText().toString());
                startActivity(intent);
            }
        });
    }
}

