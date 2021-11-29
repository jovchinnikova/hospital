package com.solvd.hospital.service.impl;

import com.solvd.hospital.domain.Equipment;
import com.solvd.hospital.persistence.EquipmentRepository;
import com.solvd.hospital.persistence.EquipmentSuppliersRepository;
import com.solvd.hospital.persistence.SupplierRepository;
import com.solvd.hospital.service.EquipmentService;

public class EquipmentServiceImpl implements EquipmentService {

    private EquipmentRepository equipmentRepository;
    private SupplierRepository supplierRepository;
    private EquipmentSuppliersRepository equipmentSuppliersRepository;

    @Override
    public void create(Equipment equipment) {
        equipment.setId(null);

        if(equipment.getSuppliers() != null){
            equipment.getSuppliers().stream()
                    .peek(supplier -> supplierRepository.create(supplier))
                    .forEach(supplier -> equipmentSuppliersRepository.create(equipment.getId(),supplier.getId()));
            equipmentRepository.create(equipment);
        }
    }
}
