package com.solvd.hospital.service;

import com.solvd.hospital.domain.Equipment;

public interface EquipmentService {

    Equipment createOrGet(Equipment equipment);

}
