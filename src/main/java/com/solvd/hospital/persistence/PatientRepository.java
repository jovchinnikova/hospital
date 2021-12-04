package com.solvd.hospital.persistence;

import com.solvd.hospital.domain.Patient;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PatientRepository {

    void create(@Param("patient") Patient patient, @Param("wardId") Long wardId);

    List<Patient> getById(Long wardId);

}
