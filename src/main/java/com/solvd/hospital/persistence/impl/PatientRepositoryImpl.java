package com.solvd.hospital.persistence.impl;

import com.solvd.hospital.domain.Patient;
import com.solvd.hospital.domain.exception.ProcessingException;
import com.solvd.hospital.persistence.ConnectionPool;
import com.solvd.hospital.persistence.PatientRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientRepositoryImpl implements PatientRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Patient patient, Long wardId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insert = "insert into Patients (first_name, last_name, age, ward_id, diagnosis) values (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, patient.getFirstName());
            preparedStatement.setString(2, patient.getLastName());
            preparedStatement.setInt(3, patient.getAge());
            preparedStatement.setLong(4, wardId);
            preparedStatement.setString(5, patient.getDiagnosis());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                patient.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new ProcessingException("Can't create patient " + e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Patient> getById(Long wardId){
        List<Patient> result = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();
        String select = "select * from patients where ward_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setLong(1,wardId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Patient patient  = new Patient();
                patient.setId(resultSet.getLong("id"));
                patient.setFirstName(resultSet.getString("first_name"));
                patient.setLastName(resultSet.getString("last_name"));
                patient.setAge(resultSet.getInt("age"));
                patient.setDiagnosis(resultSet.getString("diagnosis"));
                result.add(patient);
            }
        } catch (SQLException e) {
            throw new ProcessingException("Can't select patients " + e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return result;
    }
}
