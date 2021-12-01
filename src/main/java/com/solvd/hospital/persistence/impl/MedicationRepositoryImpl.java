package com.solvd.hospital.persistence.impl;

import com.solvd.hospital.domain.Medication;
import com.solvd.hospital.domain.exception.ProcessingException;
import com.solvd.hospital.persistence.ConnectionPool;
import com.solvd.hospital.persistence.MedicationRepository;

import java.sql.*;
import java.util.Optional;

public class MedicationRepositoryImpl implements MedicationRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Medication medication) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insert = "insert into Medications (name,form,dosage,price) values (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, medication.getName());
            preparedStatement.setString(2, medication.getForm());
            preparedStatement.setDouble(3, medication.getDosage());
            preparedStatement.setBigDecimal(4, medication.getPrice());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while ((resultSet.next())) {
                medication.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new ProcessingException("Can't create medication " + e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Medication> getByName(String name) {
        Medication result = null;

        Connection connection = CONNECTION_POOL.getConnection();
        String selectByName = "select * from medications where name = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectByName);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = new Medication();
                result.setId(resultSet.getLong("id"));
            }
        } catch (SQLException e) {
            throw new ProcessingException("Can't select medication by name " + e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.ofNullable(result);
    }
}
