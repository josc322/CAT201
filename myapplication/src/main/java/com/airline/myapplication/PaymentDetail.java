package com.airline.myapplication;

public class PaymentDetail {
    public String totalCost;
    public String totalSeats;

    public PaymentDetail() {
    }
    
    public PaymentDetail(String totalCost, String totalSeats) {
        this.totalCost = totalCost;
        this.totalSeats = totalSeats;
    }
}
