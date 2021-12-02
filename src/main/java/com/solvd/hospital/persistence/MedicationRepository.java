package com.solvd.hospital.persistence;

import com.solvd.hospital.domain.Medication;

import java.util.Optional;

public interface MedicationRepository {

    void create(Medication medication);

    Optional<Medication> getByName(String name);

}
