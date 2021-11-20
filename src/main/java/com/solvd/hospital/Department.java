package com.solvd.hospital;

import java.util.List;

public class Department {

    private Long id;
    private String title;
    private DepartmentHead departmentHead;
    private List<Worker> workers;
    private List<Patient> patients;
    private List<Equipment> equipmentList;
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

    public DepartmentHead getDepartmentHead() {
        return departmentHead;
    }

    public void setDepartmentHead(DepartmentHead departmentHead) {
        this.departmentHead = departmentHead;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }
}
