package com.solvd.hospital.persistence;

import com.solvd.hospital.domain.Ward;

public interface WardRepository {

    void create(Ward ward, Long departmentId);
}
