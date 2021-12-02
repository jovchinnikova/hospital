package com.solvd.hospital.persistence;

public interface MedicationSuppliersRepository {

    void create(Long medicationId, Long supplierId);

}
