package com.solvd.hospital.persistence;

import com.solvd.hospital.domain.Department;

public interface DepartmentRepository {

    void create(Department department,Long departmentHeadId, Long hospitalId);

}
