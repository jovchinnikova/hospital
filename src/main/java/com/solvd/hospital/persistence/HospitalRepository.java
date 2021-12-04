package com.solvd.hospital.persistence;

import com.solvd.hospital.domain.Hospital;
import org.apache.ibatis.annotations.Param;

public interface HospitalRepository {

    void create(@Param("hospital") Hospital hospital, @Param("chiefDoctorId") Long chiefDoctorId, @Param("addressId") Long addressId);

}
