package com.solvd.hospital.persistence.impl;

import com.solvd.hospital.domain.Patient;
import com.solvd.hospital.domain.exception.CreateException;
import com.solvd.hospital.persistence.ConnectionPool;
import com.solvd.hospital.persistence.PatientRepository;

import java.sql.*;

public class PatientRepositoryImpl implements PatientRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Patient patient, Long wardId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insert = "insert into Patients (first_name, last_name, age, ward_id) values (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,patient.getFirstName());
            preparedStatement.setString(2, patient.getLastName());
            preparedStatement.setInt(3,patient.getAge());
            preparedStatement.setLong(4,wardId);
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()){
                patient.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new CreateException("Can't create patient");
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
