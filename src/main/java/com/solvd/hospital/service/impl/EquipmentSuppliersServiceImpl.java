package com.solvd.hospital.service.impl;

import com.solvd.hospital.domain.Equipment;
import com.solvd.hospital.domain.Supplier;
import com.solvd.hospital.persistence.EquipmentRepository;
import com.solvd.hospital.persistence.EquipmentSuppliersRepository;
import com.solvd.hospital.persistence.impl.EquipmentMapperImpl;
import com.solvd.hospital.persistence.impl.EquipmentRepositoryImpl;
import com.solvd.hospital.persistence.impl.EquipmentSuppliersMapperImpl;
import com.solvd.hospital.persistence.impl.EquipmentSuppliersRepositoryImpl;
import com.solvd.hospital.service.EquipmentSuppliersService;
import com.solvd.hospital.service.SupplierService;

import java.util.List;

public class EquipmentSuppliersServiceImpl implements EquipmentSuppliersService {

    private final SupplierService supplierService = new SupplierServiceImpl();
    private final EquipmentRepository equipmentRepository = new EquipmentMapperImpl();
    private final EquipmentSuppliersRepository equipmentSuppliersRepository = new EquipmentSuppliersMapperImpl();

    @Override
    public Equipment create(Equipment equipment) {
        equipment.setId(null);
        List<Supplier> eqSuppliers = equipment.getSuppliers();
        equipmentRepository.create(equipment);
        eqSuppliers.stream()
                .filter(supplier -> supplier != null)
                .map(supplier -> supplierService.createOrGet(supplier))
                .forEach(supplier -> equipmentSuppliersRepository.create(equipment.getId(), supplier.getId()));
        return equipment;
    }
}
