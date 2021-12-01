package com.solvd.hospital.persistence;

import com.solvd.hospital.domain.Supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierRepository {

    void create(Supplier supplier);

    List<Supplier> getAll();

    void update();

    void delete();

    Optional<Supplier> getByName(String name);

}
