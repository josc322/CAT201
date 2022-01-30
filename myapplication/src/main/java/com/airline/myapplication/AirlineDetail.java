package com.airline.myapplication;

public class AirlineDetail{
    public String airlineId;
    public String airlineName;
    public String cabinClass;
    public String date;
    public String from_airline;
    public String to_airline;

    public AirlineDetail() {
    }
    
    public AirlineDetail(String airlineId, String airlineName, String cabinClass, String date, String from, String to) {
        this.airlineId = airlineId;
        this.airlineName = airlineName;
        this.cabinClass = cabinClass;
        this.date = date;
        this.from_airline = from;
        this.to_airline = to;
    }
}
