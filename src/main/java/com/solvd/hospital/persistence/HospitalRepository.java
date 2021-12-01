package com.solvd.hospital.persistence;

import com.solvd.hospital.domain.Hospital;

public interface HospitalRepository {

    void create(Hospital hospital, Long chiefDoctorId, Long addressId);

}
