package com.solvd.hospital;

import java.util.List;

public class Hospital {

    private Long id;
    private String title;
    private Employee chiefDoctor;
    private List<Department> departments;
    private Address address;
    private Integer phoneNumber;

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

    public Employee getChiefDoctor() {
        return chiefDoctor;
    }

    public void setChiefDoctor(Employee employee) {
        if (Employee.Position.CHIEF_DOCTOR.equals(employee.getPosition())) {
            chiefDoctor = employee;
        }
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
