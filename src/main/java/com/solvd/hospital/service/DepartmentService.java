package com.solvd.hospital.service;

import com.solvd.hospital.domain.Department;

import java.util.List;

public interface DepartmentService {

    void create(Department department, Long hospitalId);

    List<Department> retrieveAll();

}
