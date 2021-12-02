package com.solvd.hospital.service.impl;

import com.solvd.hospital.domain.Supplier;
import com.solvd.hospital.persistence.SupplierRepository;
import com.solvd.hospital.persistence.impl.SupplierRepositoryImpl;
import com.solvd.hospital.service.SupplierService;

public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository = new SupplierRepositoryImpl();

    @Override
    public Supplier createOrGet(Supplier supplier) {
        return supplierRepository.getByName(supplier.getName())
                .orElseGet(() -> create(supplier));
    }

    private Supplier create(Supplier supplier) {
        supplier.setId(null);
        supplierRepository.create(supplier);
        return supplier;
    }
}
