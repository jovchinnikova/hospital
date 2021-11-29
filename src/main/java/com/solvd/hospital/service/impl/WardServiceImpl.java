package com.solvd.hospital.service.impl;

import com.solvd.hospital.domain.Ward;
import com.solvd.hospital.persistence.PatientRepository;
import com.solvd.hospital.persistence.WardRepository;
import com.solvd.hospital.service.WardService;

public class WardServiceImpl implements WardService {

    private WardRepository wardRepository;
    private PatientRepository patientRepository;

    @Override
    public void create(Ward ward, Long departmentId) {
        ward.setId(null);
        if(ward.getPatients() != null){
            ward.getPatients().stream()
                    .forEach(patient -> patientRepository.create(patient,ward.getId()));
            wardRepository.create(ward,departmentId);
        }
    }
}
