package com.solvd.hospital.persistence;

import com.solvd.hospital.domain.Equipment;
import org.apache.ibatis.annotations.Param;

public interface DepartmentEquipmentsRepository {

    void create(@Param("equipment") Equipment equipment, @Param("departmentId") Long departmentId);

}
