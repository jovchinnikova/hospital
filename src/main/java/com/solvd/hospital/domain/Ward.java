package com.solvd.hospital.domain;

import java.util.List;
import java.util.Objects;

public class Ward {

    private Long id;
    private Integer number;
    private Integer floor;
    private List<Patient> patients;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    @Override
    public String toString() {
        return "Ward{" +
                " id=" + id +
                ", number=" + number +
                ", floor=" + floor +
                ", patients=" + patients +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ward ward = (Ward) o;
        return id.equals(ward.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
