package com.solvd.hospital.persistence;

import org.apache.ibatis.annotations.Param;

public interface EquipmentSuppliersRepository {

    void create(@Param("equipmentId") Long equipmentId, @Param("supplierId") Long supplierId);

}
