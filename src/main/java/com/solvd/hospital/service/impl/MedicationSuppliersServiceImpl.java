package com.solvd.hospital.service.impl;

import com.solvd.hospital.domain.Medication;
import com.solvd.hospital.domain.Supplier;
import com.solvd.hospital.persistence.MedicationRepository;
import com.solvd.hospital.persistence.MedicationSuppliersRepository;
import com.solvd.hospital.persistence.impl.MedicationMapperImpl;
import com.solvd.hospital.persistence.impl.MedicationRepositoryImpl;
import com.solvd.hospital.persistence.impl.MedicationSuppliersMapperImpl;
import com.solvd.hospital.persistence.impl.MedicationSuppliersRepositoryImpl;
import com.solvd.hospital.service.MedicationSuppliersService;
import com.solvd.hospital.service.SupplierService;

import java.util.List;

public class MedicationSuppliersServiceImpl implements MedicationSuppliersService {

    private final MedicationRepository medicationRepository = new MedicationMapperImpl();
    private final SupplierService supplierService = new SupplierServiceImpl();
    private final MedicationSuppliersRepository medicationSuppliersRepository = new MedicationSuppliersMapperImpl();

    @Override
    public Medication create(Medication medication) {
        medication.setId(null);
        List<Supplier> medSuppliers = medication.getSuppliers();
        medicationRepository.create(medication);
        medSuppliers.stream()
                .filter(supplier -> supplier != null)
                .map(supplier -> supplierService.createOrGet(supplier))
                .forEach(supplier -> medicationSuppliersRepository.create(medication.getId(), supplier.getId()));
        return medication;
    }
}
