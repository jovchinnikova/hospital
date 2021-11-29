package com.solvd.hospital.persistence;

import com.solvd.hospital.domain.Specialization;

public interface SpecializationRepository {

    void create(Specialization specialization);

    void selectAll();
}
