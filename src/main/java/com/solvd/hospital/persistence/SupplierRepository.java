package com.solvd.hospital.persistence;

import com.solvd.hospital.domain.Supplier;

import java.util.Optional;

public interface SupplierRepository {

    void create(Supplier supplier);

    void update(Supplier supplier, String country);

    void delete(String name);

    Optional<Supplier> getByName(String name);

}
