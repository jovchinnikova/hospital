package com.solvd.hospital.service;

import com.solvd.hospital.domain.Employee;

public interface EmployeeService {

    void createHead(Employee employee);

    void create(Employee employee, Long departmentId);

}
