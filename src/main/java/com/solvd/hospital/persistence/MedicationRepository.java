package com.solvd.hospital.persistence;

import com.solvd.hospital.domain.Medication;

public interface MedicationRepository {

    void create(Medication medication);
}
