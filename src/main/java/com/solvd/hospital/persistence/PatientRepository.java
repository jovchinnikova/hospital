package com.solvd.hospital.persistence;

import com.solvd.hospital.domain.Patient;

public interface PatientRepository {

    void create(Patient patient, Long wardId);

}
