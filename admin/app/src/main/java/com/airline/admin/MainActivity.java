package com.airline.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(hasConnection(MainActivity.this)) {
            startActivity(new Intent(getApplicationContext(),Login.class));
        } else {
            showNetDisabledAlertToUser(MainActivity.this);
        }
    }

    public boolean hasConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetwork != null && wifiNetwork.isConnected()) {
            return true;
        }
        NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileNetwork != null && mobileNetwork.isConnected()) {
            return true;
        }
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            return true;
        }
        return false;
    }

    public static void showNetDisabledAlertToUser(final Context context) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context, AlertDialog.THEME_TRADITIONAL);
        alertDialogBuilder.setMessage("Would you like to enable it?")
                .setTitle("No Internet Connection")
                .setPositiveButton(" Enable Internet ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent dialogIntent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                        dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(dialogIntent);
                    }
                });

        alertDialogBuilder.setNegativeButton(" Cancel ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
}