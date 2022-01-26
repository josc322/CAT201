package com.airline.admin;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.widget.TextView;
import java.util.List;


public class AirlineList extends ArrayAdapter<Airline> {

    private Activity context;
    private List<Airline> airlineList;

    public AirlineList(Activity context, List<Airline> airlineList) {
        super(context, R.layout.airline_list, airlineList);
        this.context = context;
        this.airlineList = airlineList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.airline_list, null, true);


        TextView textViewAirlineName = (TextView) listViewItem.findViewById(R.id.text_view_airlineName);
        TextView textViewAirlineNumber = (TextView) listViewItem.findViewById(R.id.text_view_airlineNumber);
        TextView textViewAirlineCabinClass = (TextView) listViewItem.findViewById(R.id.text_view_cabinClass);
        TextView textViewDate = (TextView) listViewItem.findViewById(R.id.text_view_Date);
        TextView textViewFrom = (TextView) listViewItem.findViewById(R.id.text_view_From);
        TextView textViewTo = (TextView) listViewItem.findViewById(R.id.text_view_To);

        Airline airline = airlineList.get(position);

        textViewAirlineName.setText(airline.getAirlineName());
        textViewAirlineNumber.setText("Airline Number       : "+airline.getAirlineNumber());
        textViewAirlineCabinClass.setText("Airline Cabin Class    : "+airline.getCabinClass());
        textViewDate.setText("Journey Date      : "+airline.getDate());
        textViewFrom.setText("Flight From            : "+airline.getFrom());
        textViewTo.setText("Flight To                : "+airline.getTo());

        return listViewItem;
    }
}
