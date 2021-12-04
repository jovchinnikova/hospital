package com.solvd.hospital.service.impl;

import com.solvd.hospital.domain.Department;
import com.solvd.hospital.persistence.DepartmentEquipmentsRepository;
import com.solvd.hospital.persistence.DepartmentMedicationsRepository;
import com.solvd.hospital.persistence.DepartmentRepository;
import com.solvd.hospital.persistence.impl.*;
import com.solvd.hospital.service.*;

public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository = new DepartmentMapperImpl();
    private final EquipmentService equipmentService = new EquipmentServiceImpl();
    private final DepartmentEquipmentsRepository departmentEquipmentsRepository = new DepartmentEquipmentsMapperImpl();
    private final DepartmentMedicationsRepository departmentMedicationsRepository = new DepartmentMedicationsMapperImpl();
    private final EmployeeService employeeService = new EmployeeServiceImpl();
    private final WardService wardService = new WardServiceImpl();
    private final MedicationService medicationService = new MedicationServiceImpl();

    @Override
    public void create(Department department, Long hospitalId) {
        if (department.getDepartmentHead() != null) {
            employeeService.createHead(department.getDepartmentHead());
        }
        department.setId(null);
        departmentRepository.create(department, department.getDepartmentHead().getId(), hospitalId);

        if (department.getEquipments() != null) {
            department.getEquipments().stream()
                    .map(equipment -> equipmentService.createOrGet(equipment))
                    .forEach(equipment -> departmentEquipmentsRepository.create(equipment, department.getId()));
        }
        if (department.getMedications() != null) {
            department.getMedications().stream()
                    .map(medication -> medicationService.createOrGet(medication))
                    .forEach(medication -> departmentMedicationsRepository.create(medication, department.getId()));
        }
        if (department.getEmployees() != null) {
            department.getEmployees().stream()
                    .forEach(employee -> employeeService.create(employee, department.getId()));
        }
        if (department.getWards() != null) {
            department.getWards().stream()
                    .forEach(ward -> wardService.create(ward, department.getId()));
        }
    }
}
