package com.solvd.hospital.service.impl;

import com.solvd.hospital.domain.Hospital;
import com.solvd.hospital.persistence.AddressRepository;
import com.solvd.hospital.persistence.HospitalRepository;
import com.solvd.hospital.service.DepartmentService;
import com.solvd.hospital.service.EmployeeService;
import com.solvd.hospital.service.HospitalService;

public class HospitalServiceImpl implements HospitalService {

    private AddressRepository addressesRepository;
    private EmployeeService employeeService;
    private DepartmentService departmentService;
    private HospitalRepository hospitalRepository;


    @Override
    public void create(Hospital hospital) {
        hospital.setId(null);
        if(hospital.getAddress() != null){
            addressesRepository.create(hospital.getAddress());
        }
        if(hospital.getChiefDoctor() != null){
            employeeService.createHead(hospital.getChiefDoctor());
        }
        if(hospital.getDepartments() != null){
            hospital.getDepartments().stream()
                    .forEach(department -> departmentService.create(department,hospital.getId()));
        }
        hospitalRepository.create(hospital,hospital.getChiefDoctor().getId(), hospital.getAddress().getId());
    }
}
