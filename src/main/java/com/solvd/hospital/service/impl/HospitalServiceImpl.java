package com.solvd.hospital.service.impl;

import com.solvd.hospital.domain.Hospital;
import com.solvd.hospital.persistence.AddressRepository;
import com.solvd.hospital.persistence.HospitalRepository;
import com.solvd.hospital.persistence.impl.AddressMapperImpl;
import com.solvd.hospital.persistence.impl.AddressRepositoryImpl;
import com.solvd.hospital.persistence.impl.HospitalMapperImpl;
import com.solvd.hospital.persistence.impl.HospitalRepositoryImpl;
import com.solvd.hospital.service.DepartmentService;
import com.solvd.hospital.service.EmployeeService;
import com.solvd.hospital.service.HospitalService;

public class HospitalServiceImpl implements HospitalService {

    private final AddressRepository addressesRepository = new AddressMapperImpl();
    private final EmployeeService employeeService = new EmployeeServiceImpl();
    private final DepartmentService departmentService = new DepartmentServiceImpl();
    private final HospitalRepository hospitalRepository = new HospitalMapperImpl();


    @Override
    public void create(Hospital hospital) {
        if (hospital.getAddress() != null) {
            addressesRepository.create(hospital.getAddress());
        }
        if (hospital.getChiefDoctor() != null) {
            employeeService.createHead(hospital.getChiefDoctor());
        }

        hospital.setId(null);
        hospitalRepository.create(hospital, hospital.getChiefDoctor().getId(), hospital.getAddress().getId());

        if (hospital.getDepartments() != null) {
            hospital.getDepartments().stream()
                    .forEach(department -> departmentService.create(department, hospital.getId()));
        }
    }
}
