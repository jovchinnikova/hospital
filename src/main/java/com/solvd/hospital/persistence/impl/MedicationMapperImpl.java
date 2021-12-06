package com.solvd.hospital.persistence.impl;

import com.solvd.hospital.domain.Medication;
import com.solvd.hospital.persistence.MedicationRepository;
import com.solvd.hospital.persistence.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.Optional;

public class MedicationMapperImpl implements MedicationRepository {

    @Override
    public void create(Medication medication) {
        try(SqlSession session = MyBatisConfig.getSessionFactory().openSession(true);) {
            MedicationRepository medicationRepository = session.getMapper(MedicationRepository.class);
            medicationRepository.create(medication);
        }
    }

    @Override
    public Optional<Medication> getByName(String name) {
        try(SqlSession session = MyBatisConfig.getSessionFactory().openSession(true);) {
            MedicationRepository medicationRepository = session.getMapper(MedicationRepository.class);
            return medicationRepository.getByName(name);
        }
    }
}
