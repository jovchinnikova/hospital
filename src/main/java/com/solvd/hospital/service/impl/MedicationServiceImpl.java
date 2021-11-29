package com.solvd.hospital.service.impl;

import com.solvd.hospital.domain.Medication;
import com.solvd.hospital.persistence.MedicationRepository;
import com.solvd.hospital.persistence.MedicationSuppliersRepository;
import com.solvd.hospital.persistence.SupplierRepository;
import com.solvd.hospital.service.MedicationService;

public class MedicationServiceImpl implements MedicationService {

    private MedicationRepository medicationRepository;
    private SupplierRepository supplierRepository;
    private MedicationSuppliersRepository medicationSuppliersRepository;

    @Override
    public void create(Medication medication) {
        medication.setId(null);
        if(medication.getSuppliers() != null){
            medication.getSuppliers().stream()
                    .peek(supplier -> supplierRepository.create(supplier))
                    .forEach(supplier -> medicationSuppliersRepository.create(medication.getId(),supplier.getId()));
            medicationRepository.create(medication);
        }
    }
}
