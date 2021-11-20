package com.solvd.hospital;

import java.util.List;

public class Hospital {

    private Long id;
    private String title;
    private ChiefDoctor chiefDoctor;
    private List<Department> departments;
    private String address;
    private int phoneNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ChiefDoctor getChiefDoctor() {
        return chiefDoctor;
    }

    public void setChiefDoctor(ChiefDoctor chiefDoctor) {
        this.chiefDoctor = chiefDoctor;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
