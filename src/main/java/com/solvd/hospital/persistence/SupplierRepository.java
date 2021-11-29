package com.solvd.hospital.persistence;

import com.solvd.hospital.domain.Supplier;

public interface SupplierRepository {

    void create(Supplier supplier);

    void selectAll();

    void update();

    void delete();
}
