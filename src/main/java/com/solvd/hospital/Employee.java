package com.solvd.hospital;

public class Employee extends Person {

    private Position position;
    private Integer qualification;
    private Specialization specialization;

    public enum Position {

        CHIEF_DOCTOR("chief doctor"), DEPARTMENT_HEAD("head of department");

        private final String name;

        Position(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Integer getQualification() {
        return qualification;
    }

    public void setQualification(Integer qualification) {
        this.qualification = qualification;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }
}
