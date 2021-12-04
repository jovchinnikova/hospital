package com.solvd.hospital.persistence.impl;

import com.solvd.hospital.domain.Address;
import com.solvd.hospital.persistence.AddressRepository;
import com.solvd.hospital.persistence.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;

public class AddressMapperImpl implements AddressRepository {

    @Override
    public void create(Address address) {
        try(SqlSession session = MyBatisConfig.getSessionFactory().openSession(true);) {
            AddressRepository addressRepository = session.getMapper(AddressRepository.class);
            addressRepository.create(address);
        }
    }
}
