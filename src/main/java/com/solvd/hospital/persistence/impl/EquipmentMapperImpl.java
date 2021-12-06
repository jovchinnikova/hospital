package com.solvd.hospital.persistence.impl;

import com.solvd.hospital.domain.Equipment;
import com.solvd.hospital.persistence.EquipmentRepository;
import com.solvd.hospital.persistence.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.Optional;

public class EquipmentMapperImpl implements EquipmentRepository {

    @Override
    public void create(Equipment equipment) {
        try(SqlSession session = MyBatisConfig.getSessionFactory().openSession(true);) {
            EquipmentRepository equipmentRepository =
                    session.getMapper(EquipmentRepository.class);
            equipmentRepository.create(equipment);
        }
    }

    @Override
    public Optional<Equipment> getByName(String name) {
        try(SqlSession session = MyBatisConfig.getSessionFactory().openSession(true);) {
            EquipmentRepository equipmentRepository =
                    session.getMapper(EquipmentRepository.class);
            return equipmentRepository.getByName(name);
        }
    }
}
