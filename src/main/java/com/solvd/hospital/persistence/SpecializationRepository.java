package com.solvd.hospital.persistence;

import com.solvd.hospital.domain.Specialization;

import java.util.Optional;

public interface SpecializationRepository {

    void create(Specialization specialization);

    Optional<Specialization> getByName(String name);

}
