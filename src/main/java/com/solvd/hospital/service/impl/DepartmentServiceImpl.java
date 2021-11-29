package com.solvd.hospital.service.impl;

import com.solvd.hospital.domain.Department;
import com.solvd.hospital.persistence.DepartmentEquipmentsRepository;
import com.solvd.hospital.persistence.DepartmentMedicationsRepository;
import com.solvd.hospital.persistence.DepartmentRepository;
import com.solvd.hospital.service.*;

public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;
    private EquipmentService equipmentService;
    private MedicationService medicationService;
    private DepartmentEquipmentsRepository departmentEquipmentsRepository;
    private DepartmentMedicationsRepository departmentMedicationsRepository;
    private EmployeeService employeeService;
    private WardService wardService;

    @Override
    public void create(Department department, Long hospitalId) {
        department.setId(null);
        if(department.getDepartmentHead() != null){
            employeeService.createHead(department.getDepartmentHead());
        }
        if(department.getEquipments() != null){
            department.getEquipments().stream()
                    .peek(equipment -> equipmentService.create(equipment))
                    .forEach(equipment -> departmentEquipmentsRepository.create(equipment,department.getId()));
        }
        if(department.getMedications() != null){
            department.getMedications().stream()
                    .peek(medication -> medicationService.create(medication))
                    .forEach(medication -> departmentMedicationsRepository.create(medication,department.getId()));
        }
        if(department.getEmployees() != null){
            department.getEmployees().stream()
                    .forEach(employee -> employeeService.create(employee,department.getId()));
        }
        if(department.getWards() != null){
            department.getWards().stream()
                    .forEach(ward -> wardService.create(ward,department.getId()));
        }
        departmentRepository.create(department,department.getDepartmentHead().getId(), hospitalId);
    }
}
