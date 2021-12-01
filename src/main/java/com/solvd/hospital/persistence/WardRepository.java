package com.solvd.hospital.persistence;

import com.solvd.hospital.domain.Ward;

import java.util.List;

public interface WardRepository {

    void create(Ward ward, Long departmentId);

    List<Ward> getById(Long departmentId);

}
