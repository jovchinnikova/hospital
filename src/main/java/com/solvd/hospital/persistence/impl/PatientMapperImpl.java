package com.solvd.hospital.persistence.impl;

import com.solvd.hospital.domain.Patient;
import com.solvd.hospital.persistence.MyBatisConfig;
import com.solvd.hospital.persistence.PatientRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class PatientMapperImpl implements PatientRepository {

    @Override
    public void create(Patient patient, Long wardId) {
        try(SqlSession session = MyBatisConfig.getSessionFactory().openSession(true);) {
            PatientRepository patientRepository = session.getMapper(PatientRepository.class);
            patientRepository.create(patient, wardId);
        }
    }

    @Override
    public List<Patient> getById(Long wardId) {
        return null;
    }
}
