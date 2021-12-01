package com.solvd.hospital.service.impl;

import com.solvd.hospital.domain.Equipment;
import com.solvd.hospital.persistence.EquipmentRepository;
import com.solvd.hospital.persistence.impl.EquipmentRepositoryImpl;
import com.solvd.hospital.service.EquipmentService;
import com.solvd.hospital.service.EquipmentSuppliersService;

public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository = new EquipmentRepositoryImpl();
    private final EquipmentSuppliersService equipmentSuppliersService = new EquipmentSuppliersServiceImpl();

    @Override
    public Equipment createOrGet(Equipment equipment) {
        return equipmentRepository.getByName(equipment.getName())
                .orElseGet(() -> create(equipment));
    }

    private Equipment create(Equipment equipment) {
        equipment.setId(null);
        equipmentSuppliersService.create(equipment);
        return equipment;
    }
}
