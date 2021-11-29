package com.solvd.hospital;

import com.solvd.hospital.domain.Supplier;
import com.solvd.hospital.persistence.SupplierRepository;
import com.solvd.hospital.persistence.impl.SupplierRepositoryImpl;

public class Main {

    public static void main(String[] args) {
        Supplier supplier = new Supplier();
        supplier.setName("Trenker");
        supplier.setCountry("Belgium");
        SupplierRepository supplierRepository = new SupplierRepositoryImpl();
        supplierRepository.create(supplier);
        supplierRepository.delete();
        supplierRepository.selectAll();
    }
}
