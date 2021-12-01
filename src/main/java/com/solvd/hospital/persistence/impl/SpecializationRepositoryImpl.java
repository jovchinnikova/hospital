package com.solvd.hospital.persistence.impl;

import com.solvd.hospital.domain.Specialization;
import com.solvd.hospital.domain.exception.ProcessingException;
import com.solvd.hospital.persistence.ConnectionPool;
import com.solvd.hospital.persistence.SpecializationRepository;

import java.sql.*;
import java.util.Optional;

public class SpecializationRepositoryImpl implements SpecializationRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Specialization specialization) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insert = "insert into Specializations (name,salary) values (?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, specialization.getName());
            preparedStatement.setBigDecimal(2, specialization.getSalary());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                specialization.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new ProcessingException("Can't create specialization " + e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Specialization> getByName(String name) {
        Specialization result = null;

        Connection connection = CONNECTION_POOL.getConnection();
        String selectByName = "select * from specializations where name = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectByName);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = new Specialization();
                result.setId(resultSet.getLong("id"));
            }
        } catch (SQLException e) {
            throw new ProcessingException("Can't select specialization by name " + e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.ofNullable(result);
    }
}
