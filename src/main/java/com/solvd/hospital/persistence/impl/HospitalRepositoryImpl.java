package com.solvd.hospital.persistence.impl;

import com.solvd.hospital.domain.Hospital;
import com.solvd.hospital.domain.exception.CreateException;
import com.solvd.hospital.persistence.ConnectionPool;
import com.solvd.hospital.persistence.HospitalRepository;

import java.sql.*;

public class HospitalRepositoryImpl implements HospitalRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Hospital hospital, Long chiefDoctorId, Long addressId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insert = "insert into Hospitals (title, chief_doctor_id, address_id, phone_number) values (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, hospital.getTitle());
            preparedStatement.setLong(2,chiefDoctorId);
            preparedStatement.setLong(3,addressId);
            preparedStatement.setInt(4,hospital.getPhoneNumber());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()){
                hospital.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new CreateException("Can't create hospital");
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
