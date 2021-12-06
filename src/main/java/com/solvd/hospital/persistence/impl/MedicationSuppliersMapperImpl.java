package com.solvd.hospital.persistence.impl;

import com.solvd.hospital.persistence.MedicationSuppliersRepository;
import com.solvd.hospital.persistence.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;

public class MedicationSuppliersMapperImpl implements MedicationSuppliersRepository {

    @Override
    public void create(Long medicationId, Long supplierId) {
        try(SqlSession session = MyBatisConfig.getSessionFactory().openSession(true);) {
            MedicationSuppliersRepository medicationSuppliersRepository =
                    session.getMapper(MedicationSuppliersRepository.class);
            medicationSuppliersRepository.create(medicationId, supplierId);
        }
    }
}
