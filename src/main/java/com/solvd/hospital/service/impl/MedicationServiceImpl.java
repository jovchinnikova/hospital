package com.solvd.hospital.service.impl;

import com.solvd.hospital.domain.Medication;
import com.solvd.hospital.persistence.MedicationRepository;
import com.solvd.hospital.persistence.impl.MedicationMapperImpl;
import com.solvd.hospital.persistence.impl.MedicationRepositoryImpl;
import com.solvd.hospital.service.MedicationService;
import com.solvd.hospital.service.MedicationSuppliersService;

public class MedicationServiceImpl implements MedicationService {

    private final MedicationRepository medicationRepository = new MedicationMapperImpl();
    private final MedicationSuppliersService medicationSuppliersService = new MedicationSuppliersServiceImpl();

    @Override
    public Medication createOrGet(Medication medication) {
        return medicationRepository.getByName(medication.getName())
                .orElseGet(() -> create(medication));
    }

    private Medication create(Medication medication) {
        medication.setId(null);
        medicationSuppliersService.create(medication);
        return medication;
    }
}
