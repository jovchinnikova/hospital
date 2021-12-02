package com.solvd.hospital.service;

import com.solvd.hospital.domain.Specialization;

public interface SpecializationService {

    Specialization createOrGet(Specialization specialization);

}
