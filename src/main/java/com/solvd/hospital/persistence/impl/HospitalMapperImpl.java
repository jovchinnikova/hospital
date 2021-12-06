package com.solvd.hospital.persistence.impl;

import com.solvd.hospital.domain.Hospital;
import com.solvd.hospital.persistence.HospitalRepository;
import com.solvd.hospital.persistence.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;

public class HospitalMapperImpl implements HospitalRepository {

    @Override
    public void create(Hospital hospital, Long chiefDoctorId, Long addressId) {
        try(SqlSession session = MyBatisConfig.getSessionFactory().openSession(true);) {
            HospitalRepository hospitalRepository = session.getMapper(HospitalRepository.class);
            hospitalRepository.create(hospital, chiefDoctorId, addressId);
        }
    }
}
