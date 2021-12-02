package com.solvd.hospital.service;

import com.solvd.hospital.domain.Ward;

import java.util.List;

public interface WardService {

    void create(Ward ward, Long departmentId);

    List<Ward> getById(Long departmentId);

}
