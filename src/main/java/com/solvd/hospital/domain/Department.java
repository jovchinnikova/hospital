package com.solvd.hospital.domain;

import java.util.List;

public class Department {

    private Long id;
    private String title;
    private Employee departmentHead;
    private List<Employee> employees;
    private List<Ward> wards;
    private List<Equipment> equipments;
    private List<Medication> medications;

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

    public Employee getDepartmentHead() {
        return departmentHead;
    }

    public void setDepartmentHead(Employee employee) {
        if (Employee.Position.DEPARTMENT_HEAD.equals(employee.getPosition())) {
            departmentHead = employee;
        }
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Ward> getWards() {
        return wards;
    }

    public void setWards(List<Ward> wards) {
        this.wards = wards;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Equipment> equipmentList) {
        this.equipments = equipmentList;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }
}
