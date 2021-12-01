package com.solvd.hospital.service;

import com.solvd.hospital.domain.Medication;

public interface MedicationService {

    Medication createOrGet(Medication medication);
}
