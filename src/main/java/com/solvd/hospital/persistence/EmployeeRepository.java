package com.solvd.hospital.persistence;

import com.solvd.hospital.domain.Employee;

public interface EmployeeRepository {

     void createHead(Employee employee, Long specializationId);

     void create(Employee employee, Long specializationId, Long departmentId);
}
