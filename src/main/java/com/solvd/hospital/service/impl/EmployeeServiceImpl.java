package com.solvd.hospital.service.impl;

import com.solvd.hospital.domain.Employee;
import com.solvd.hospital.persistence.EmployeeRepository;
import com.solvd.hospital.persistence.SpecializationRepository;
import com.solvd.hospital.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private SpecializationRepository specializationRepository;

    @Override
    public void createHead(Employee employee) {
        employee.setId(null);
        if(employee.getSpecialization() != null){
            specializationRepository.create(employee.getSpecialization());
        }
        employeeRepository.createHead(employee,employee.getSpecialization().getId());
    }

    @Override
    public void create(Employee employee, Long departmentId){
        employee.setId(null);
        if(employee.getSpecialization() != null){
            specializationRepository.create(employee.getSpecialization());
        }
        employeeRepository.create(employee,employee.getSpecialization().getId(), departmentId);
    }
}
