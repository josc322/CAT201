package com.airline.myapplication;

public class CustomerDetail {
    public String customerEmail;
    public String customerPhone;
    public String customerName;
    public String customerAge;

    //Default constructor
    public CustomerDetail(){

    }

    public CustomerDetail(String email, String phone, String name,String age) {
        this.customerEmail = email;
        this.customerPhone = phone;
        this.customerName = name;
        this.customerAge = age;
    }
}
