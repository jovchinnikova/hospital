package com.solvd.hospital.persistence;

import java.sql.ResultSet;

public interface EquipmentSuppliersRepository {

    void create(Long equipmentId, Long supplierId);
}
