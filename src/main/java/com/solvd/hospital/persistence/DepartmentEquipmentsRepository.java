package com.solvd.hospital.persistence;

import com.solvd.hospital.domain.Equipment;

public interface DepartmentEquipmentsRepository {

    void create(Equipment equipment, Long departmentId);

}
