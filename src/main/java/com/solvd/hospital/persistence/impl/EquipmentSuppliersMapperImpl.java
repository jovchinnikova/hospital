package com.solvd.hospital.persistence.impl;

import com.solvd.hospital.persistence.EquipmentSuppliersRepository;
import com.solvd.hospital.persistence.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;

public class EquipmentSuppliersMapperImpl implements EquipmentSuppliersRepository {

    @Override
    public void create(Long equipmentId, Long supplierId) {
        try(SqlSession session = MyBatisConfig.getSessionFactory().openSession(true);) {
            EquipmentSuppliersRepository equipmentSuppliersRepository =
                    session.getMapper(EquipmentSuppliersRepository.class);
            equipmentSuppliersRepository.create(equipmentId, supplierId);
        }
    }
}
