package com.brillicaservices.database2;

public class Studentmodel {

    String name;
    String collegeName;
    double fees;
    long phoneNumber;
    int id;

    public Studentmodel(int id, String name, String collegeName, double fees, long phoneNumber) {
        this.id = id;
        this.name = name;
        this.collegeName = collegeName;
        this.fees = fees;
        this.phoneNumber = phoneNumber;
    }

    public Studentmodel(String name, String collegeName, double fees, long phoneNumber) {
        this.id = id;
        this.name = name;
        this.collegeName = collegeName;
        this.fees= fees;
        this.phoneNumber = phoneNumber;
    }

    public Studentmodel() {

    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public Double getFees() {
        return fees;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setFees(Double fees) {
        this.fees = fees;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setId(int id) {
        this.id = id;
    }
}
