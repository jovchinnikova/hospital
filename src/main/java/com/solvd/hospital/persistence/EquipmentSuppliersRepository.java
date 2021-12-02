package com.solvd.hospital.persistence;

public interface EquipmentSuppliersRepository {

    void create(Long equipmentId, Long supplierId);

}
