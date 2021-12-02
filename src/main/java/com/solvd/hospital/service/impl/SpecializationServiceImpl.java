package com.solvd.hospital.service.impl;

import com.solvd.hospital.domain.Specialization;
import com.solvd.hospital.persistence.SpecializationRepository;
import com.solvd.hospital.persistence.impl.SpecializationRepositoryImpl;
import com.solvd.hospital.service.SpecializationService;

public class SpecializationServiceImpl implements SpecializationService {

    private final SpecializationRepository specializationRepository = new SpecializationRepositoryImpl();

    @Override
    public Specialization createOrGet(Specialization specialization) {
        return specializationRepository.getByName(specialization.getName())
                .orElseGet(() -> create(specialization));
    }

    private Specialization create(Specialization specialization) {
        specialization.setId(null);
        specializationRepository.create(specialization);
        return specialization;
    }
}
