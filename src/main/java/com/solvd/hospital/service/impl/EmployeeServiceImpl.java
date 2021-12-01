package com.solvd.hospital.service.impl;

import com.solvd.hospital.domain.Employee;
import com.solvd.hospital.domain.Specialization;
import com.solvd.hospital.persistence.EmployeeRepository;
import com.solvd.hospital.persistence.impl.EmployeeRepositoryImpl;
import com.solvd.hospital.service.EmployeeService;
import com.solvd.hospital.service.SpecializationService;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
    private final SpecializationService specializationService = new SpecializationServiceImpl();

    @Override
    public void createHead(Employee employee) {
        employee.setId(null);
        Specialization empSpecialization = employee.getSpecialization();
        if (empSpecialization != null) {
            Specialization specialization = specializationService.createOrGet(empSpecialization);
            if (specialization != null) {
                empSpecialization.setId(specialization.getId());
            }
            employeeRepository.createHead(employee, empSpecialization.getId());
        }
    }

    @Override
    public void create(Employee employee, Long departmentId) {
        employee.setId(null);
        Specialization empSpecialization = employee.getSpecialization();
        if (empSpecialization != null) {
            Specialization specialization = specializationService.createOrGet(empSpecialization);
            if (specialization != null) {
                empSpecialization.setId(specialization.getId());
            }
            employeeRepository.create(employee, empSpecialization.getId(), departmentId);
        }
    }
}
