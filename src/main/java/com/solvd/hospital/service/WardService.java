package com.solvd.hospital.service;

import com.solvd.hospital.domain.Ward;

public interface WardService {

    void create(Ward ward, Long departmentId);
}
