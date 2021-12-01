package com.solvd.hospital;

import com.solvd.hospital.domain.*;
import com.solvd.hospital.persistence.DepartmentRepository;
import com.solvd.hospital.persistence.SupplierRepository;
import com.solvd.hospital.persistence.impl.DepartmentRepositoryImpl;
import com.solvd.hospital.persistence.impl.SupplierRepositoryImpl;
import com.solvd.hospital.service.HospitalService;
import com.solvd.hospital.service.impl.HospitalServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Supplier supplier1 = new Supplier();
        supplier1.setName("Trenker");
        supplier1.setCountry("Belgium");

        Supplier supplier2 = new Supplier();
        supplier2.setName("DealMed");
        supplier2.setCountry("Russia");

        Supplier supplier3 = new Supplier();
        supplier3.setName("DealMed");
        supplier3.setCountry("Russia");

        Supplier supplier4 = new Supplier();
        supplier4.setName("Vermeiren");
        supplier4.setCountry("Belgium");

        List<Supplier> dropperSuppliers = new ArrayList<>();
        dropperSuppliers.add(supplier1);
        dropperSuppliers.add(supplier2);
        List<Supplier> feedingTubeSuppliers = new ArrayList<>();
        feedingTubeSuppliers.add(supplier3);
        feedingTubeSuppliers.add(supplier4);
        List<Supplier> ibyprophenSuppliers = new ArrayList<>();
        ibyprophenSuppliers.add(supplier1);
        ibyprophenSuppliers.add(supplier3);

        Equipment equipment1 = new Equipment();
        equipment1.setName("dropper");
        equipment1.setPrice(BigDecimal.valueOf(20.4));
        equipment1.setSuppliers(dropperSuppliers);
        equipment1.setQuantity(25);

        Equipment equipment2 = new Equipment();
        equipment2.setName("feeding tube");
        equipment2.setPrice(BigDecimal.valueOf(3.0));
        equipment2.setSuppliers(feedingTubeSuppliers);
        equipment2.setQuantity(76);

        List<Equipment> equipments = new ArrayList<>();
        equipments.add(equipment1);
        equipments.add(equipment2);

        Medication medication1 = new Medication();
        medication1.setName("Ibypophen");
        medication1.setForm("pills");
        medication1.setDosage(5.0);
        medication1.setPrice(BigDecimal.valueOf(4.2));
        medication1.setSuppliers(ibyprophenSuppliers);
        medication1.setQuantity(400);

        Medication medication2 = new Medication();
        medication2.setName("Cetorolac");
        medication2.setForm("pills");
        medication2.setDosage(3.4);
        medication2.setPrice(BigDecimal.valueOf(2.6));
        medication2.setSuppliers(feedingTubeSuppliers);
        medication2.setQuantity(245);

        List<Medication> medications = new ArrayList<>();
        medications.add(medication1);
        medications.add(medication2);

        Specialization specialization1 = new Specialization();
        specialization1.setName("cardiologist");
        specialization1.setSalary(BigDecimal.valueOf(1500.2));

        Specialization specialization2 = new Specialization();
        specialization2.setName("therapist");
        specialization2.setSalary(BigDecimal.valueOf(850.3));

        Employee employee1 = new Employee();
        employee1.setFirstName("Petr");
        employee1.setLastName("Petrov");
        employee1.setSpecialization(specialization2);
        employee1.setPosition(Employee.Position.DEPARTMENT_HEAD);

        Employee employee2 = new Employee();
        employee2.setFirstName("Ivan");
        employee2.setLastName("Ivanov");
        employee2.setSpecialization(specialization1);
        employee2.setPosition(Employee.Position.CHIEF_DOCTOR);

        Employee employee3 = new Employee();
        employee3.setFirstName("Zinaida");
        employee3.setLastName("Morozova");
        employee3.setSpecialization(specialization1);
        employee3.setQualification(2);
        Employee employee4 = new Employee();
        employee4.setFirstName("Pavel");
        employee4.setLastName("Pavlov");
        employee4.setSpecialization(specialization2);
        employee4.setQualification(2);
        List<Employee> employees = new ArrayList<>();
        employees.add(employee3);
        employees.add(employee4);

        Patient patient1 = new Patient();
        patient1.setFirstName("Diana");
        patient1.setLastName("Melnikova");
        patient1.setAge(48);
        patient1.setDiagnosis("asthma");

        Patient patient2 = new Patient();
        patient2.setFirstName("Vladislav");
        patient2.setLastName("Kolesnik");
        patient2.setAge(21);
        patient2.setDiagnosis("pneumonia");

        List<Patient> patients = new ArrayList<>();
        patients.add(patient1);
        patients.add(patient2);

        Ward ward = new Ward();
        ward.setNumber(230);
        ward.setFloor(2);
        ward.setPatients(patients);
        List<Ward> wards = new ArrayList<>();
        wards.add(ward);

        Department department = new Department();
        department.setTitle("therapeutic");
        department.setEquipments(equipments);
        department.setMedications(medications);
        department.setDepartmentHead(employee1);
        department.setEmployees(employees);
        department.setWards(wards);
        List<Department> departments = new ArrayList<>();
        departments.add(department);

        Address address = new Address();
        address.setCity("Minsk");
        address.setStreet("Uralskaya");
        address.setBuildingNumber("5/1");

        Hospital hospital = new Hospital();
        hospital.setTitle("City clinical hospital №6");
        hospital.setAddress(address);
        hospital.setDepartments(departments);
        hospital.setChiefDoctor(employee2);
        hospital.setPhoneNumber(12345);

        HospitalService hospitalService = new HospitalServiceImpl();
        hospitalService.create(hospital);

        SupplierRepository supplierRepository = new SupplierRepositoryImpl();
        supplierRepository.delete(supplier1.getName());
        supplierRepository.update(supplier2,"China");

        DepartmentRepository departmentRepository = new DepartmentRepositoryImpl();
        List<Department> result = departmentRepository.getAll();
        System.out.println(result);
    }
}
