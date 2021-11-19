package com.solvd.hospital;

import java.util.List;

public class Hospital {

    private long id;
    private String title;
    private String address;
    private ChiefDoctor chiefDoctor;
    private int phoneNumber;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ChiefDoctor getChiefDoctor() {
        return chiefDoctor;
    }

    public void setChiefDoctor(ChiefDoctor chiefDoctor) {
        this.chiefDoctor = chiefDoctor;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
