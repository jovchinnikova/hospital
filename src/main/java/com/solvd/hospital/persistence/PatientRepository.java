package com.solvd.hospital.persistence;

import com.solvd.hospital.domain.Patient;

import java.util.List;

public interface PatientRepository {

    void create(Patient patient, Long wardId);

    List<Patient> getById(Long wardId);

}
