package com.solvd.hospital.persistence;

import com.solvd.hospital.domain.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentRepository {

    void create(@Param("department") Department department, @Param("departmentHeadId") Long departmentHeadId,
                @Param("hospitalId") Long hospitalId);

    List<Department> getAll();

}
