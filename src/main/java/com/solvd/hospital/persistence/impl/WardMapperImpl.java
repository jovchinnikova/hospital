package com.solvd.hospital.persistence.impl;

import com.solvd.hospital.domain.Ward;
import com.solvd.hospital.persistence.MyBatisConfig;
import com.solvd.hospital.persistence.WardRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class WardMapperImpl implements WardRepository {

    @Override
    public void create(Ward ward, Long departmentId) {
        try(SqlSession session = MyBatisConfig.getSessionFactory().openSession(true);) {
            WardRepository wardRepository = session.getMapper(WardRepository.class);
            wardRepository.create(ward, departmentId);
        }
    }

    @Override
    public List<Ward> getById(Long departmentId) {
        return null;
    }
}
