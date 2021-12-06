package com.solvd.hospital.persistence.impl;

import com.solvd.hospital.domain.Specialization;
import com.solvd.hospital.persistence.MyBatisConfig;
import com.solvd.hospital.persistence.SpecializationRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.Optional;

public class SpecializationMapperImpl implements SpecializationRepository {

    @Override
    public void create(Specialization specialization) {
        try(SqlSession session = MyBatisConfig.getSessionFactory().openSession(true);) {
            SpecializationRepository specializationRepository = session.getMapper(SpecializationRepository.class);
            specializationRepository.create(specialization);
        }
    }

    @Override
    public Optional<Specialization> getByName(String name) {
        try(SqlSession session = MyBatisConfig.getSessionFactory().openSession(true);) {
            SpecializationRepository specializationRepository = session.getMapper(SpecializationRepository.class);
            return specializationRepository.getByName(name);
        }
    }
}
