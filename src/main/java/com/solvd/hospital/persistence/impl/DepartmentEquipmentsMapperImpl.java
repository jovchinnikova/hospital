package com.solvd.hospital.persistence.impl;

import com.solvd.hospital.domain.Equipment;
import com.solvd.hospital.persistence.DepartmentEquipmentsRepository;
import com.solvd.hospital.persistence.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;

public class DepartmentEquipmentsMapperImpl implements DepartmentEquipmentsRepository {

    @Override
    public void create(Equipment equipment, Long departmentId) {
        try(SqlSession session = MyBatisConfig.getSessionFactory().openSession(true);) {
            DepartmentEquipmentsRepository departmentEquipmentsRepository =
                    session.getMapper(DepartmentEquipmentsRepository.class);
            departmentEquipmentsRepository.create(equipment, departmentId);
        }
    }
}
