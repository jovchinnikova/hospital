package com.solvd.hospital.persistence;

import com.solvd.hospital.domain.Employee;
import org.apache.ibatis.annotations.Param;

public interface EmployeeRepository {

    void createHead(@Param("employee") Employee employee, @Param("specializationId")Long specializationId);

    void create(@Param("employee")Employee employee, @Param("specializationId") Long specializationId,
                @Param("departmentId") Long departmentId);

}
