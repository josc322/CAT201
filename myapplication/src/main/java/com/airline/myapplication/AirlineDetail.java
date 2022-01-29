package com.airline.myapplication;

public class AirlineDetail{
    public String airlineId;
    public String airlineName;
    public String cabinClass;
    public String date;
    public String from_airline;
    public String to_airline;
    public String time_reach;
    public String time_from;

    public AirlineDetail(String airlineId, String airlineName, String cabinClass, String date, String from, String to, String time_from, String time_reach) {
        this.airlineId = airlineId;
        this.airlineName = airlineName;
        this.cabinClass = cabinClass;
        this.date = date;
        this.from_airline = from;
        this.to_airline = to;
        this.time_from = time_from;
        this.time_reach = time_reach;
    }

    public AirlineDetail() {
    }
}
