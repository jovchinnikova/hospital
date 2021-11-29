package com.solvd.hospital.persistence;

import com.solvd.hospital.domain.Medication;

public interface DepartmentMedicationsRepository {

    void create(Medication medication, Long departmentId);

}
