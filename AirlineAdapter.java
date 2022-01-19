package com.example.inthujan.airline;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AirlineAdapter extends RecyclerView.Adapter<AirlineAdapter.ArtistViewHolder> {

    private Context mCtx;
    private List<Airline> AirlineList;
    private ItemClickListener clickListener;

    public AirlineAdapter(Context mCtx, List<Airline> busList) {
        this.mCtx = mCtx;
        this.AirlineList = AirlineList;
    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_airlines, parent, false);
        return new ArtistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder holder, int position) {
        Airline airline = AirlineList.get(position);
        holder.textViewBusName.setText(airline.airlineName);
        holder.textViewBusNumber.setText("Airline ID : " + airline.airlineId);
        holder.textViewDate.setText("Journey Date : " + airline.date);
        holder.textViewFrom.setText("From : " + airline.from);
        holder.textViewTo.setText("To : " + airline.to);
    }

    @Override
    public int getItemCount() {
        return AirlineList.size();
    }
    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    class ArtistViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textViewBusName, textViewBusNumber, textViewDate, textViewFrom,textViewTo,textViewCondition;

        public ArtistViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewBusName = itemView.findViewById(R.id.text_view_airlineName);
            textViewBusNumber = itemView.findViewById(R.id.text_view_airlineId);
            textViewDate = itemView.findViewById(R.id.text_view_date);
            textViewFrom = itemView.findViewById(R.id.text_view_from);
            textViewTo = itemView.findViewById(R.id.text_view_to);

            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }
}

