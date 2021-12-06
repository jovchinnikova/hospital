package com.solvd.hospital.persistence;

import com.solvd.hospital.domain.Medication;
import org.apache.ibatis.annotations.Param;

public interface DepartmentMedicationsRepository {

    void create(@Param("medication") Medication medication, @Param("departmentId") Long departmentId);

}
