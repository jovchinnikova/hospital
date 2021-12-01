package com.solvd.hospital.service.impl;

import com.solvd.hospital.domain.Ward;
import com.solvd.hospital.persistence.PatientRepository;
import com.solvd.hospital.persistence.WardRepository;
import com.solvd.hospital.persistence.impl.PatientRepositoryImpl;
import com.solvd.hospital.persistence.impl.WardRepositoryImpl;
import com.solvd.hospital.service.WardService;

public class WardServiceImpl implements WardService {

    private final WardRepository wardRepository = new WardRepositoryImpl();
    private final PatientRepository patientRepository = new PatientRepositoryImpl();

    @Override
    public void create(Ward ward, Long departmentId) {
        ward.setId(null);
        wardRepository.create(ward, departmentId);
        if (ward.getPatients() != null) {
            ward.getPatients().stream()
                    .forEach(patient -> patientRepository.create(patient, ward.getId()));
        }
    }
}
