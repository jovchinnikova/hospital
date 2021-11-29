package com.solvd.hospital.persistence.impl;

import com.solvd.hospital.domain.Specialization;
import com.solvd.hospital.domain.exception.CreateException;
import com.solvd.hospital.domain.exception.SelectException;
import com.solvd.hospital.persistence.ConnectionPool;
import com.solvd.hospital.persistence.SpecializationRepository;

import java.sql.*;

public class SpecializationRepositoryImpl implements SpecializationRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Specialization specialization) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insert = "insert into Specializations (name,salary) values (?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, specialization.getName());
            preparedStatement.setBigDecimal(2,specialization.getSalary());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while(resultSet.next()){
                specialization.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new CreateException("Can't create specialization");
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    public void selectAll(){
        Connection connection = CONNECTION_POOL.getConnection();
        String select = "select * from specializations";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while(resultSet.next()){
                System.out.println(resultSet.getLong("id") + " " + resultSet.getString("name")
                 + resultSet.getBigDecimal("salary"));
            }
        } catch (SQLException e) {
            throw new SelectException("Can't select");
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
