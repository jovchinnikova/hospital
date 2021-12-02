package com.solvd.hospital.persistence;

import com.solvd.hospital.domain.Equipment;

import java.util.Optional;

public interface EquipmentRepository {

    void create(Equipment equipment);

    Optional<Equipment> getByName(String name);

}
