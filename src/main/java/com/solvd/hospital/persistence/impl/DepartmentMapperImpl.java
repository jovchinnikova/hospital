package com.solvd.hospital.persistence.impl;

import com.solvd.hospital.domain.Department;
import com.solvd.hospital.persistence.DepartmentRepository;
import com.solvd.hospital.persistence.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class DepartmentMapperImpl implements DepartmentRepository {

    @Override
    public void create(Department department, Long departmentHeadId, Long hospitalId) {
        try(SqlSession session = MyBatisConfig.getSessionFactory().openSession(true);) {
            DepartmentRepository departmentRepository =
                    session.getMapper(DepartmentRepository.class);
            departmentRepository.create(department, departmentHeadId, hospitalId);
        }
    }

    @Override
    public List<Department> getAll() {
        try (SqlSession session = MyBatisConfig.getSessionFactory().openSession(true);) {
            DepartmentRepository departmentRepository =
                    session.getMapper(DepartmentRepository.class);
            return departmentRepository.getAll();
        }
    }
}
