package com.solvd.hospital.persistence;

import com.solvd.hospital.domain.Ward;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WardRepository {

    void create(@Param("ward") Ward ward, @Param("departmentId") Long departmentId);

    List<Ward> getById(Long departmentId);

}
