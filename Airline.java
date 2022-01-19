package com.example.inthujan.airline;

public class Airline {
    public String airlineId;
    public String airlineName;
    public String cabinClass;
    public String date;
    public String from;
    public String to;

    public Airline(String airlineId, String airlineName, String cabinClass, String date, String from, String to) {
        this.airlineId = airlineId;
        this.airlineName = airlineName;
        this.cabinClass = cabinClass;
        this.date = date;
        this.from = from;
        this.to = to;
    }

    public String getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(String airlineId) {
        this.airlineId = airlineId;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getCabinClass() {
        return cabinClass;
    }

    public void setCabinClass(String cabinClass) {
        this.cabinClass = cabinClass;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
