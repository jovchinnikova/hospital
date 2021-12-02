package com.solvd.hospital.service;

import com.solvd.hospital.domain.Department;

public interface DepartmentService {

    void create(Department department, Long hospitalId);

}
