package com.solvd.hospital.persistence;

import com.solvd.hospital.domain.Department;

import java.util.List;

public interface DepartmentRepository {

    void create(Department department,Long departmentHeadId, Long hospitalId);

    List<Department> getAll();

}
